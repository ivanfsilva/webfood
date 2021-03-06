package br.com.ivanfsilva.webfood.domain.repository;

import br.com.ivanfsilva.webfood.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}
