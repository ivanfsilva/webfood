package br.com.ivanfsilva.webfood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.stereotype.Component;

import br.com.ivanfsilva.webfood.domain.event.PedidoConfirmadoEvent;
import br.com.ivanfsilva.webfood.domain.model.Pedido;
import br.com.ivanfsilva.webfood.domain.service.EnvioEmailService;
import br.com.ivanfsilva.webfood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoConfirmadoListener {

    @Autowired
    private EnvioEmailService envioEmail;

    // decisao do negocio: o envio do email eh importante? se nao for, retira a phase
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
        Pedido pedido = event.getPedido();

        var mensagem = Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
                .corpo("pedido-confirmado.html")
                .variavel("pedido", pedido)
                .destinatario(pedido.getCliente().getEmail())
                .build();

        envioEmail.enviar(mensagem);
    }

}
