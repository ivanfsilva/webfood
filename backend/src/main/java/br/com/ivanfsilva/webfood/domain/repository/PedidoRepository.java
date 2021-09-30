package br.com.ivanfsilva.webfood.domain.repository;

import br.com.ivanfsilva.webfood.domain.model.Pedido;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long> {

}
