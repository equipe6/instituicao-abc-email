package br.edu.unisinos.instituicaoabc.config;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.io.Serializable;

@Log4j2
@Configuration
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SqsConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Bean
    public SqsClient buildSqsClient() {
        SqsClient sqsClient = SqsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        return sqsClient;
    }

}
