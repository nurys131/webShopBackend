package pl.project.shop.common.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSimpleService {

    private final JavaMailSender mailSender;

    public void send(String to, String subject, String msg) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Shop <shop@webshop.pl>");
        message.setReplyTo("Shop <shop@webshop.pl>");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        mailSender.send(message);
    }
}
