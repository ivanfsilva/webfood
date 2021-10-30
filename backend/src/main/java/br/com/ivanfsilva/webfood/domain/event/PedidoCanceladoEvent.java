package br.com.ivanfsilva.webfood.domain.event;

import br.com.ivanfsilva.webfood.domain.model.Pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoCanceladoEvent {

    private Pedido pedido;

}