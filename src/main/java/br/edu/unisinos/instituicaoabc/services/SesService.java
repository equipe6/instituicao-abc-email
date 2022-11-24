package br.edu.unisinos.instituicaoabc.services;

import br.edu.unisinos.instituicaoabc.dtos.MatriculaDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

import java.io.Serializable;

/**
 * Classe reponsavel pelas regras de negócio para AWS SES.
 */
@Log4j2
@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SesService implements Serializable {

    private static final long serialVersionUID = 1L;

    //Injeta SesClient
    private final SesClient sesClient;

    private static final String DEFAULT_EMAIL_FROM = "demo-instituicao-abc@frekele.org";

    /**
     * Metodo responsável pelo envio do email para o SMTP da AWS SES.
     * Recebe MatriculaDto e pega dados para setar no template de emai.
     */
    public void sendEmail(MatriculaDto matricula) {
        String from = DEFAULT_EMAIL_FROM;
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

    /**
     * Metodo privado responsável pelo envio do email para o SMTP da AWS SES.
     */
    private void sendEmail(String from, String to, String subject, String bodyHtml) {

        Destination destination = Destination.builder().toAddresses(to).build();

        Content content = Content.builder().data(bodyHtml).build();

        Content sub = Content.builder().data(subject).build();

        Body body = Body.builder().html(content).build();

        Message msg = Message.builder().subject(sub).body(body).build();

        SendEmailRequest emailRequest = SendEmailRequest.builder().destination(destination).message(msg).source(from).build();

        //Envia email para AWS SES.
        SendEmailResponse emailResponse = this.sesClient.sendEmail(emailRequest);
        log.info("emailResponse: " + emailResponse);
    }

}
