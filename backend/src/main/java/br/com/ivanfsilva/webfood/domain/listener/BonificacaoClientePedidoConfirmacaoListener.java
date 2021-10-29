package br.com.ivanfsilva.webfood.domain.listener;

import br.com.ivanfsilva.webfood.domain.event.PedidoConfirmadoEvent;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class BonificacaoClientePedidoConfirmacaoListener {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
        System.out.println("Calculando pontos para cliente " + event.getPedido().getCliente().getNome());
    }
}
