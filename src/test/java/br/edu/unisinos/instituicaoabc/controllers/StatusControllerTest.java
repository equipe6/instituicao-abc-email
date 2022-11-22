package br.edu.unisinos.instituicaoabc.controllers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StatusControllerTest {


    private StatusController statusController;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        this.statusController = BDDMockito.spy(new StatusController());
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }


}