package br.edu.unisinos.instituicaoabc.listeners;

import br.edu.unisinos.instituicaoabc.dtos.MatriculaDto;
import br.edu.unisinos.instituicaoabc.services.SesService;
import br.edu.unisinos.instituicaoabc.services.SqsService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.model.Message;

import java.io.Serializable;
import java.time.Instant;

/**
 * Classe reponsavel pelas regras por ouvir o message Broker AWS SQS.
 */
@Log4j2
@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SendEmailListener implements Serializable {

    private static final long serialVersionUID = 1L;

    //Injeta SqsService
    private final SqsService sqsService;

    //Injeta SesService
    private final SesService sesService;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    /**
     * Metodo responsavel por processar mensagens encontradas na fila.
     * Delay inicial de 6seg e intervado de 5seg.
     */
    @Scheduled(initialDelay = 6000, fixedDelay = 5000)
    @Async
    public void listenerMatriculaToSendEmail() throws JsonProcessingException {
        log.info("Run listenerMatriculaToSendEmail - " + Instant.now());
        Message message = this.sqsService.receiveMessage();

        if (message != null) {
            String jsonBody = message.body();
            if (jsonBody != null) {
                MatriculaDto matricula = this.objectMapper.readValue(jsonBody, MatriculaDto.class);
                if (matricula != null) {
                    this.sesService.sendEmail(matricula);
                }
            }
            this.sqsService.deleteMessage(message);
        }
    }

}
