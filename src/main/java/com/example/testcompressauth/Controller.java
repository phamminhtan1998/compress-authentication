package com.example.testcompressauth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@RestController
public class Controller {
    @Autowired
    AuthenticationDataRepo authenticationDataRepo;

    @GetMapping("/testCompress")
    public Authentication testDecompress() throws IOException, ClassNotFoundException {

        AuthenticationData authen = authenticationDataRepo.getById(1L);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(authen.getAuthenticationData());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        Authentication obj = (Authentication) in.readObject();
        in.close();
        return obj;
    }
}

