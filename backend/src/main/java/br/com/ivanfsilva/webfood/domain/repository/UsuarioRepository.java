package br.com.ivanfsilva.webfood.domain.repository;

import br.com.ivanfsilva.webfood.domain.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail( String email );
}
