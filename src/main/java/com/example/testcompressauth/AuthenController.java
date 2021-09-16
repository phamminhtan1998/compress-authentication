package com.example.testcompressauth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

@RestController

public class AuthenController {

    @Autowired
    private AuthenticationDataRepo authenticationDataRepo;

    @GetMapping("/api")
    public AuthenticationData testCompress() throws IOException {
        Authentication auth = new UsernamePasswordAuthenticationToken("minh tan", "minh tan",
                Arrays.asList(new SimpleGrantedAuthority("ADMIN")));


        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
        objOut.writeObject(auth);
        objOut.close();
        byteOut.close();
        byte[] bytes = byteOut.toByteArray();

        AuthenticationData authenData = new AuthenticationData();
        authenData.setAuthenticationData(bytes);
        authenticationDataRepo.save(authenData);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return authenData;

    }

}
