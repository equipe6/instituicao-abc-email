package br.edu.unisinos.instituicaoabc.config;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class SwaggerConfigTest {

    private SwaggerConfig swaggerConfig;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        this.swaggerConfig = BDDMockito.spy(new SwaggerConfig());
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @Test
    void testCustomOpenAPI() throws Exception {
        var description = "teste";
        var appVersion = "1.0.1";
        var result = this.swaggerConfig.customOpenAPI(description, appVersion);
        assertNotNull(result);
    }

}