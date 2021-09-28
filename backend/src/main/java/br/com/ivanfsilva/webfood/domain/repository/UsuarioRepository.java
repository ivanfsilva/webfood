package br.com.ivanfsilva.webfood.domain.repository;

import br.com.ivanfsilva.webfood.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
