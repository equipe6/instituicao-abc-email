package br.edu.unisinos.instituicaoabc.controllers;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@Log4j2
@RestController
@RequestMapping("/status")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class StatusController implements Serializable {

    private static final long serialVersionUID = 1L;

    @GetMapping("/")
    public ResponseEntity<String> status() {
        log.info("status");
        return ResponseEntity.ok("RUNNING");
    }

}
