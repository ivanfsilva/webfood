package br.com.ivanfsilva.webfood.domain.repository;

import br.com.ivanfsilva.webfood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    List<Cozinha> findByNome(String nome);

}
