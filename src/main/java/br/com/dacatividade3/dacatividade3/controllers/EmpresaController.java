package br.com.dacatividade3.dacatividade3.controllers;

import br.com.dacatividade3.dacatividade3.dtos.EmpresaDTO;
import br.com.dacatividade3.dacatividade3.services.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/empresa")
public class EmpresaController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<Page<EmpresaDTO>> findEmpresas(Pageable pageable) {
        Page<EmpresaDTO> list = empresaService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmpresaDTO> findEmpresaById(@PathVariable Long id) {
        EmpresaDTO dto = empresaService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<Long> createEmpresa(@Valid @RequestBody EmpresaDTO dto) {
        Long id = empresaService.insert(dto);
        return ResponseEntity.ok().body(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateEmpresa(@PathVariable Long id, @Valid @RequestBody EmpresaDTO dto) {
        empresaService.update(id, dto);
        return ResponseEntity.ok("Empresa atualizada com sucesso.");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteEmpresa(@PathVariable Long id) {
        empresaService.delete(id);
        return ResponseEntity.ok("Empresa deletada com sucesso.");
    }
}
