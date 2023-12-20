package br.com.dacatividade3.dacatividade3.services;

import br.com.dacatividade3.dacatividade3.dtos.AvaliacaoDaEmpresaDTO;
import br.com.dacatividade3.dacatividade3.dtos.AvaliacaoDoProfessorDTO;
import br.com.dacatividade3.dacatividade3.dtos.EstagioDTO;
import br.com.dacatividade3.dacatividade3.entities.*;
import br.com.dacatividade3.dacatividade3.mappers.AvaliacaoDaEmpresaMapper;
import br.com.dacatividade3.dacatividade3.mappers.AvaliacaoDoProfessorMapper;
import br.com.dacatividade3.dacatividade3.mappers.EstagioMapper;
import br.com.dacatividade3.dacatividade3.repositories.*;
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
public class EstagioService implements Serializable {

    private EstagioRepository estagioRepository;
    private AlunoRepository alunoRepository;
    private EmpresaRepository empresaRepository;
    private OrientadorRepository orientadorRepository;
    private AvaliacaoDaEmpresaRepository avaliacaoDaEmpresaRepository;
    private AvaliacaoDoProfessorRepository avaliacaoDoProfessorRepository;

    @Autowired
    public EstagioService(EstagioRepository estagioRepository, AlunoRepository alunoRepository,
            EmpresaRepository empresaRepository, OrientadorRepository orientadorRepository,
            AvaliacaoDaEmpresaRepository avaliacaoDaEmpresaRepository,
            AvaliacaoDoProfessorRepository avaliacaoDoProfessorRepository) {
        this.estagioRepository = estagioRepository;
        this.alunoRepository = alunoRepository;
        this.empresaRepository = empresaRepository;
        this.orientadorRepository = orientadorRepository;
        this.avaliacaoDoProfessorRepository = avaliacaoDoProfessorRepository;
        this.avaliacaoDaEmpresaRepository = avaliacaoDaEmpresaRepository;
    }

    @Transactional(readOnly = true)
    public Page<EstagioDTO> findAllPaged(Pageable pageable) {
        Page<Estagio> list = estagioRepository.findAll(pageable);
        return list.map(EstagioMapper::toEstagioDTO);
    }

    @Transactional(readOnly = true)
    public EstagioDTO findById(Long id) {
        Optional<Estagio> obj = estagioRepository.findById(id);
        Estagio entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return EstagioMapper.toEstagioDTO(entity);
    }

    @Transactional
    public Long insert(EstagioDTO dto) {
        Optional<Aluno> objAluno = alunoRepository.findById(dto.getAlunoId());
        Aluno alunoEntity = objAluno.orElseThrow(() -> new ResourceNotFoundException("Aluno not found"));
        Optional<Empresa> objEmpresa = empresaRepository.findById(dto.getEmpresaId());
        Empresa empresaEntity = objEmpresa.orElseThrow(() -> new ResourceNotFoundException("Empresa not found"));
        Optional<Orientador> objOrientador = orientadorRepository.findById(dto.getOrientadorId());
        Orientador orientadorEntity = objOrientador
                .orElseThrow(() -> new ResourceNotFoundException("Orientador not found"));
        Estagio entity = EstagioMapper.toEstagio(dto, alunoEntity, empresaEntity, orientadorEntity);
        return estagioRepository.save(entity).getId();
    }

    @Transactional
    public void update(Long id, EstagioDTO dto) {
        Optional<Estagio> obj = estagioRepository.findById(id);
        Estagio entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
        entity.setInicioEstagio(dto.getInicioEstagio());
        entity.setFimEstagio(dto.getFimEstagio());
        entity.setCargaHoraria(dto.getCargaHoraria());
        entity.setStatus(dto.getStatus());

        if (dto.getAvaliacaoDaEmpresa() != null) {
            atualizarOuInserirAvaliacaoDaEmpresa(entity,
                    AvaliacaoDaEmpresaMapper.toAvaliacaoDaEmpresa(dto.getAvaliacaoDaEmpresa(), entity));
        }

        if (dto.getAvaliacaoDoProfessor() != null) {
            atualizarOuInserirAvaliacaoDoProfessor(entity,
                    AvaliacaoDoProfessorMapper.toAvaliacaoDoProfessor(dto.getAvaliacaoDoProfessor(), entity));
        }

        estagioRepository.save(entity);
    }

    public void delete(Long id) {
        try {
            estagioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    @Transactional
    public Long insertAvaliacaoDaEmpresa(Long estagioId, AvaliacaoDaEmpresaDTO dto) {
        Optional<Estagio> obj = estagioRepository.findById(estagioId);
        Estagio estagioEntity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
        AvaliacaoDaEmpresa entity = AvaliacaoDaEmpresaMapper.toAvaliacaoDaEmpresa(dto, estagioEntity);
        return avaliacaoDaEmpresaRepository.save(entity).getId();
    }

    @Transactional
    public Long insertAvaliacaoDoProfessor(Long estagioId, AvaliacaoDoProfessorDTO dto) {
        Optional<Estagio> obj = estagioRepository.findById(estagioId);
        Estagio estagioEntity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
        AvaliacaoDoProfessor entity = AvaliacaoDoProfessorMapper.toAvaliacaoDoProfessor(dto, estagioEntity);
        return avaliacaoDoProfessorRepository.save(entity).getId();
    }

    @Transactional
    public void deleteAvaliacaoDaEmpresa(Long estagioId) {
        Optional<Estagio> obj = estagioRepository.findById(estagioId);
        Estagio estagioEntity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
        AvaliacaoDaEmpresa avaliacaoDaEmpresa = estagioEntity.getAvaliacaoDaEmpresa();
        if (avaliacaoDaEmpresa != null) {
            estagioEntity.setAvaliacaoDaEmpresa(null);
            estagioRepository.save(estagioEntity);
            avaliacaoDaEmpresaRepository.deleteById(avaliacaoDaEmpresa.getId());
        } else {
            throw new ResourceNotFoundException("AvaliacaoDaEmpresa not found for Estagio id: " + estagioId);
        }
    }

    @Transactional
    public void deleteAvaliacaoDoProfessor(Long estagioId) {
        Optional<Estagio> obj = estagioRepository.findById(estagioId);
        Estagio estagioEntity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
        AvaliacaoDoProfessor avaliacaoDoProfessor = estagioEntity.getAvaliacaoDoProfessor();
        if (avaliacaoDoProfessor != null) {
            estagioEntity.setAvaliacaoDoProfessor(null);
            estagioRepository.save(estagioEntity);
            avaliacaoDoProfessorRepository.deleteById(avaliacaoDoProfessor.getId());
        } else {
            throw new ResourceNotFoundException("AvaliacaoDoProfessor not found for Estagio id: " + estagioId);
        }
    }

    private void atualizarOuInserirAvaliacaoDaEmpresa(Estagio estagio, AvaliacaoDaEmpresa novaAvaliacao) {
        AvaliacaoDaEmpresa avaliacaoExistente = estagio.getAvaliacaoDaEmpresa();

        if (avaliacaoExistente != null) {
            atualizarAvaliacaoExistente(avaliacaoExistente, novaAvaliacao);
        } else {
            novaAvaliacao.setEstagio(estagio);
            estagio.setAvaliacaoDaEmpresa(novaAvaliacao);
        }

        estagioRepository.save(estagio);
    }

    private void atualizarAvaliacaoExistente(AvaliacaoDaEmpresa avaliacaoExistente, AvaliacaoDaEmpresa novaAvaliacao) {
        avaliacaoExistente.setRendimentoDeTrabalho(novaAvaliacao.getRendimentoDeTrabalho());
        avaliacaoExistente.setConhecimentos(novaAvaliacao.getConhecimentos());
        avaliacaoExistente.setAprendizagem(novaAvaliacao.getAprendizagem());
        avaliacaoExistente.setCumprimentoDasTarefas(novaAvaliacao.getCumprimentoDasTarefas());
        avaliacaoExistente.setDesempenho(novaAvaliacao.getDesempenho());

        avaliacaoDaEmpresaRepository.save(avaliacaoExistente);
    }

    private void atualizarOuInserirAvaliacaoDoProfessor(Estagio estagio, AvaliacaoDoProfessor novaAvaliacao) {
        AvaliacaoDoProfessor avaliacaoExistente = estagio.getAvaliacaoDoProfessor();

        if (avaliacaoExistente != null) {
            atualizarAvaliacaoExistente(avaliacaoExistente, novaAvaliacao);
        } else {
            novaAvaliacao.setEstagio(estagio);
            estagio.setAvaliacaoDoProfessor(novaAvaliacao);
        }

        estagioRepository.save(estagio);
    }

    private void atualizarAvaliacaoExistente(AvaliacaoDoProfessor avaliacaoExistente,
            AvaliacaoDoProfessor novaAvaliacao) {
        avaliacaoExistente.setAssiduidade(novaAvaliacao.getAssiduidade());
        avaliacaoExistente.setDisciplina(novaAvaliacao.getDisciplina());
        avaliacaoExistente.setIniciativa(novaAvaliacao.getIniciativa());
        avaliacaoExistente.setResponsabilidade(novaAvaliacao.getResponsabilidade());
        avaliacaoExistente.setSociabilidade(novaAvaliacao.getSociabilidade());

        avaliacaoDoProfessorRepository.save(avaliacaoExistente);
    }
}