package br.com.dacatividade3.dacatividade3.controllers;

import br.com.dacatividade3.dacatividade3.dtos.AlunoDTO;
import br.com.dacatividade3.dacatividade3.services.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/aluno")
public class AlunoController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<Page<AlunoDTO>> findAlunos(Pageable pageable) {
        Page<AlunoDTO> list = alunoService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AlunoDTO> findAlunoById(@PathVariable Long id) {
        AlunoDTO dto = alunoService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<Long> createAluno(@Valid @RequestBody AlunoDTO dto) {
        Long id = alunoService.insert(dto);
        return ResponseEntity.ok().body(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateAluno(@PathVariable Long id, @Valid @RequestBody AlunoDTO dto) {
        alunoService.update(id, dto);
        return ResponseEntity.ok("Aluno atualizado com sucesso.");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAluno(@PathVariable Long id) {
        alunoService.delete(id);
        return ResponseEntity.ok("Aluno deletado com sucesso.");
    }
}
