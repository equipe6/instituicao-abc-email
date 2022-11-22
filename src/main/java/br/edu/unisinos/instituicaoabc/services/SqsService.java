package br.edu.unisinos.instituicaoabc.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.io.Serializable;

@Log4j2
@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SqsService implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String QUEUE_URL = "https://sqs.us-east-1.amazonaws.com/362770054718/instituicao-abc-matriculado";

    private final SqsClient sqsClient;

    @Async
    public void sendMessage(String messageBody) {
        SendMessageRequest messageRequest = SendMessageRequest.builder()
                .queueUrl(QUEUE_URL)
                .messageBody(messageBody)
                .build();
        SendMessageResponse messageResponse = sqsClient.sendMessage(messageRequest);
        log.info("messageResponse: " + messageResponse);
    }


}
