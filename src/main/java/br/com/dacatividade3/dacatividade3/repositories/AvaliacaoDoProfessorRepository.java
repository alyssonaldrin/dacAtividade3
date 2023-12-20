package br.com.dacatividade3.dacatividade3.repositories;

import br.com.dacatividade3.dacatividade3.entities.AvaliacaoDoProfessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoDoProfessorRepository extends JpaRepository<AvaliacaoDoProfessor, Long> {

}
