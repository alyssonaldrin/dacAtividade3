package br.com.dacatividade3.dacatividade3.controllers;

import br.com.dacatividade3.dacatividade3.dtos.OrientadorDTO;
import br.com.dacatividade3.dacatividade3.services.OrientadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orientador")
public class OrientadorController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private OrientadorService orientadorService;

    @GetMapping
    public ResponseEntity<Page<OrientadorDTO>> findOrientadores(Pageable pageable) {
        Page<OrientadorDTO> list = orientadorService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrientadorDTO> findOrientadorById(@PathVariable Long id) {
        OrientadorDTO dto = orientadorService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<Long> createOrientador(@Valid @RequestBody OrientadorDTO dto) {
        Long id = orientadorService.insert(dto);
        return ResponseEntity.ok().body(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateOrientador(@PathVariable Long id, @Valid @RequestBody OrientadorDTO dto) {
        orientadorService.update(id, dto);
        return ResponseEntity.ok("Orientador atualizado com sucesso.");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteOrientador(@PathVariable Long id) {
        orientadorService.delete(id);
        return ResponseEntity.ok("Orientador deletado com sucesso.");
    }
}
