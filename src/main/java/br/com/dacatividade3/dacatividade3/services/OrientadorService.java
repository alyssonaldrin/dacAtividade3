package br.com.dacatividade3.dacatividade3.services;

import br.com.dacatividade3.dacatividade3.dtos.OrientadorDTO;
import br.com.dacatividade3.dacatividade3.entities.Orientador;
import br.com.dacatividade3.dacatividade3.mappers.OrientadorMapper;
import br.com.dacatividade3.dacatividade3.repositories.OrientadorRepository;
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
public class OrientadorService implements Serializable {

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	private OrientadorRepository orientadorRepository;

	@Transactional(readOnly = true)
	public Page<OrientadorDTO> findAllPaged(Pageable pageable) {
		Page<Orientador> list = orientadorRepository.findAll(pageable);
		return list.map(OrientadorMapper::toOrientadorDTO);
	}

	@Transactional(readOnly = true)
	public OrientadorDTO findById(Long id) {
		Optional<Orientador> obj = orientadorRepository.findById(id);
		Orientador entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return OrientadorMapper.toOrientadorDTO(entity);
	}

	@Transactional
	public Long insert(OrientadorDTO dto) {
		Orientador entity = OrientadorMapper.toOrientador(dto);
		return orientadorRepository.save(entity).getId();
	}

	@Transactional
	public void update(Long id, OrientadorDTO dto) {
		Optional<Orientador> obj = orientadorRepository.findById(id);
		Orientador entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
		entity.setNome(dto.getNome());
		entity.setDepartamento(dto.getDepartamento());
		orientadorRepository.save(entity);
	}

	public void delete(Long id) {
		try {
			orientadorRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
}