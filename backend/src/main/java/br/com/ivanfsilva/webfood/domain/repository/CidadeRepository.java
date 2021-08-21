package br.com.ivanfsilva.webfood.domain.repository;


import br.com.ivanfsilva.webfood.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
