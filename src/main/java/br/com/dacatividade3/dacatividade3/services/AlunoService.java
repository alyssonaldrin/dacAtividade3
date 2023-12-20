package br.com.dacatividade3.dacatividade3.services;

import br.com.dacatividade3.dacatividade3.dtos.AlunoDTO;
import br.com.dacatividade3.dacatividade3.entities.Aluno;
import br.com.dacatividade3.dacatividade3.mappers.AlunoMapper;
import br.com.dacatividade3.dacatividade3.repositories.AlunoRepository;
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
public class AlunoService implements Serializable {

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	private AlunoRepository alunoRepository;

	@Transactional(readOnly = true)
	public Page<AlunoDTO> findAllPaged(Pageable pageable) {
		Page<Aluno> list = alunoRepository.findAll(pageable);
		return list.map(AlunoMapper::toAlunoDTO);
	}

	@Transactional(readOnly = true)
	public AlunoDTO findById(Long id) {
		Optional<Aluno> obj = alunoRepository.findById(id);
		Aluno entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return AlunoMapper.toAlunoDTO(entity);
	}

	@Transactional
	public Long insert(AlunoDTO dto) {
		Aluno entity = AlunoMapper.toAluno(dto);
		return alunoRepository.save(entity).getId();
	}

	@Transactional
	public void update(Long id, AlunoDTO dto) {
		Optional<Aluno> obj = alunoRepository.findById(id);
		Aluno entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
		entity.setNome(dto.getNome());
		entity.setMatricula(dto.getMatricula());
		entity.setCurso(dto.getCurso());
		alunoRepository.save(entity);
	}

	public void delete(Long id) {
		try {
			alunoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
}