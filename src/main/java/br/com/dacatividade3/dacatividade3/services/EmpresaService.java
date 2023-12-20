package br.com.dacatividade3.dacatividade3.services;

import br.com.dacatividade3.dacatividade3.dtos.EmpresaDTO;
import br.com.dacatividade3.dacatividade3.entities.Empresa;
import br.com.dacatividade3.dacatividade3.mappers.EmpresaMapper;
import br.com.dacatividade3.dacatividade3.repositories.EmpresaRepository;
import br.com.dacatividade3.dacatividade3.services.exceptions.DatabaseException;
import br.com.dacatividade3.dacatividade3.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;

@Service
public class EmpresaService implements Serializable {

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	private EmpresaRepository empresaRepository;

	@Transactional(readOnly = true)
	public Page<EmpresaDTO> findAllPaged(Pageable pageable) {
		Page<Empresa> list = empresaRepository.findAll(pageable);
		return list.map(EmpresaMapper::toEmpresaDTO);
	}

	@Transactional(readOnly = true)
	public EmpresaDTO findById(Long id) {
		Optional<Empresa> obj = empresaRepository.findById(id);
		Empresa entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return EmpresaMapper.toEmpresaDTO(entity);
	}

	@Transactional
	public Long insert(EmpresaDTO dto) {
		Empresa entity = EmpresaMapper.toEmpresa(dto);
		return empresaRepository.save(entity).getId();
	}

	@Transactional
	public void update(Long id, EmpresaDTO dto) {
		Optional<Empresa> obj = empresaRepository.findById(id);
		Empresa entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
		entity.setNome(dto.getNome());
		entity.setEndereco(dto.getEndereco());
		entity.setCnpj(dto.getCnpj());
		empresaRepository.save(entity);
	}

	public void delete(Long id) {
		try {
			empresaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
}