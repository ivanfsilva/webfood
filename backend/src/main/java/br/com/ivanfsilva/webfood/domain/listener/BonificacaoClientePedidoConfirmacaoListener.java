package br.com.ivanfsilva.webfood.domain.listener;

import br.com.ivanfsilva.webfood.domain.event.PedidoConfirmadoEvent;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BonificacaoClientePedidoConfirmacaoListener {

    @EventListener
    public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
        System.out.println("Calculando pontos para cliente " + event.getPedido().getCliente().getNome());
    }
}
