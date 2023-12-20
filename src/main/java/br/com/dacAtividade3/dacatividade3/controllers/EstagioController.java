package br.com.dacatividade3.dacatividade3.controllers;

import br.com.dacatividade3.dacatividade3.dtos.AvaliacaoDaEmpresaDTO;
import br.com.dacatividade3.dacatividade3.dtos.AvaliacaoDoProfessorDTO;
import br.com.dacatividade3.dacatividade3.dtos.EstagioDTO;
import br.com.dacatividade3.dacatividade3.services.EstagioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/estagio")
public class EstagioController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private EstagioService estagioService;

    @GetMapping
    public ResponseEntity<Page<EstagioDTO>> findEstagios(Pageable pageable) {
        Page<EstagioDTO> list = estagioService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EstagioDTO> findEstagioById(@PathVariable Long id) {
        EstagioDTO dto = estagioService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<Long> createEstagio(@Valid @RequestBody EstagioDTO dto) {
        Long id = estagioService.insert(dto);
        return ResponseEntity.ok().body(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateEstagio(@PathVariable Long id, @Valid @RequestBody EstagioDTO dto) {
        estagioService.update(id, dto);
        return ResponseEntity.ok("Estagio atualizado com sucesso.");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteEstagio(@PathVariable Long id) {
        estagioService.delete(id);
        return ResponseEntity.ok("Estagio deletado com sucesso.");
    }

    @PostMapping(value = "/avaliacao-da-empresa/{estagioId}")
    public ResponseEntity<Long> createAvaliacaoDaEmpresa(@PathVariable Long estagioId,
            @Valid @RequestBody AvaliacaoDaEmpresaDTO dto) {
        Long id = estagioService.insertAvaliacaoDaEmpresa(estagioId, dto);
        return ResponseEntity.ok().body(id);
    }

    @PostMapping(value = "/avaliacao-do-professor/{estagioId}")
    public ResponseEntity<Long> createAvaliacaoDoProfessor(@PathVariable Long estagioId,
            @Valid @RequestBody AvaliacaoDoProfessorDTO dto) {
        Long id = estagioService.insertAvaliacaoDoProfessor(estagioId, dto);
        return ResponseEntity.ok().body(id);
    }

    @DeleteMapping(value = "/avaliacao-da-empresa/{estagioId}")
    public ResponseEntity<String> deleteAvaliacaoDaEmpresa(@PathVariable Long estagioId) {
        estagioService.deleteAvaliacaoDaEmpresa(estagioId);
        return ResponseEntity.ok().body("Avaliacao da empresa deletada com sucesso.");
    }

    @DeleteMapping(value = "/avaliacao-do-professor/{estagioId}")
    public ResponseEntity<String> deleteAvaliacaoDoProfessor(@PathVariable Long estagioId) {
        estagioService.deleteAvaliacaoDoProfessor(estagioId);
        return ResponseEntity.ok().body("Avaliacao do professor deletada com sucesso.");
    }
}
