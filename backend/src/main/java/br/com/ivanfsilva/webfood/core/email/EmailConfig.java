package br.com.ivanfsilva.webfood.core.email;

import br.com.ivanfsilva.webfood.domain.service.EnvioEmailService;
import br.com.ivanfsilva.webfood.infrastructure.service.email.FakeEnvioEmailService;
import br.com.ivanfsilva.webfood.infrastructure.service.email.SmtpEnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EnvioEmailService envioEmailService() {
        // Acho melhor usar switch aqui do que if/else if
        switch (emailProperties.getImpl()) {
            case FAKE:
                return new FakeEnvioEmailService();
            case SMTP:
                return new SmtpEnvioEmailService();
            default:
                return null;
        }
    }

}
