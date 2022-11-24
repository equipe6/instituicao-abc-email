package br.edu.unisinos.instituicaoabc.config;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.io.Serializable;

/**
 * Classe reponsável pelas configurações para AWS SQS.
 */
@Log4j2
@Configuration
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SqsConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Injeção manual para o Bean SqsClient com autenticação Default.
     * <p>
     * A autenticação usa as configurações nas seguinte ordem:
     * Java System Properties - aws.accessKeyId and aws.secretAccessKey
     * Environment Variables - AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY
     * Web Identity Token credentials from system properties or environment variables
     * Credential profiles file at the default location (~/.aws/credentials) shared by all AWS SDKs and the AWS CLI
     * Credentials delivered through the Amazon EC2 container service if AWS_CONTAINER_CREDENTIALS_RELATIVE_URI" environment variable is set and security manager has permission to access the variable,
     * Instance profile credentials delivered through the Amazon EC2 metadata service
     */
    @Bean
    public SqsClient buildSqsClient() {
        SqsClient sqsClient = SqsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        return sqsClient;
    }

}
