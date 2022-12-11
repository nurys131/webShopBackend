package pl.project.shop.common.mail;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

//@Configuration
public class EmailConfig {

    @Bean
    @ConditionalOnProperty(name = "app.email.sender", matchIfMissing = true, havingValue = "emailSimpleService")
    public EmailSender emailSimpleService (JavaMailSender javaMailSender) {
        return new EmailSimpleService(javaMailSender);
    }

    @Bean
    @ConditionalOnProperty(name = "app.email.sender", havingValue = "fakeEmailService")
    public EmailSender fakeEmailService () {
        return new FakeEmailService();
    }
}
