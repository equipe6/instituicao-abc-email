package br.edu.unisinos.instituicaoabc.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.io.Serializable;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SqsService implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String QUEUE_URL = "https://sqs.us-east-1.amazonaws.com/362770054718/instituicao-abc-matriculado";

    private final SqsClient sqsClient;

    public Message receiveMessage() {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(QUEUE_URL)
                .maxNumberOfMessages(1)
                .build();
        List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();
        if (messages == null || messages.isEmpty()) {
            return null;
        }

        Message message = messages.get(0);
        log.info("Received messageResponse: " + message);

        return message;
    }

    public void deleteMessage(Message message) {
        DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder()
                .queueUrl(QUEUE_URL)
                .receiptHandle(message.receiptHandle())
                .build();
        sqsClient.deleteMessage(deleteMessageRequest);
        log.info("Deleted messageId: " + message.messageId() + " - receiptHandle: " + message.receiptHandle());
    }

}
