package br.edu.unisinos.instituicaoabc.services;

import br.edu.unisinos.instituicaoabc.dtos.MatriculaDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

import java.io.Serializable;

@Log4j2
@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SesService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final SesClient sesClient;

    public void sendEmail(String from,
                          String to,
                          String subject,
                          String bodyHtml) {

        Destination destination = Destination.builder()
                .toAddresses(to)
                .build();

        Content content = Content.builder()
                .data(bodyHtml)
                .build();

        Content sub = Content.builder()
                .data(subject)
                .build();

        Body body = Body.builder()
                .html(content)
                .build();

        Message msg = Message.builder()
                .subject(sub)
                .body(body)
                .build();

        SendEmailRequest emailRequest = SendEmailRequest.builder()
                .destination(destination)
                .message(msg)
                .source(from)
                .build();

        SendEmailResponse emailResponse = this.sesClient.sendEmail(emailRequest);
        log.info("emailResponse: " + emailResponse);
    }

    public void sendEmail(MatriculaDto matricula) {
        String from = "demo-instituicao-abc@frekele.org";
        String to = matricula.getEmail();
        String subject = "Matricula #" + matricula.getId() + " - Curso: " + matricula.getNomeCurso();

        StringBuilder sbBodyHtml = new StringBuilder();
        sbBodyHtml.append("Parabéns ") //
                .append(matricula.getNome()) //
                .append(",<BR>") //
                .append("Você foi matriculado no curso: ") //
                .append(matricula.getNomeCurso()) //
                .append(" no turno: ") //
                .append(matricula.getTurno()) //
                .append(" - Matricula #")//
                .append(matricula.getId());

        String bodyHtml = sbBodyHtml.toString();
        log.info(bodyHtml);

        this.sendEmail(from, to, subject, bodyHtml);
    }
}
