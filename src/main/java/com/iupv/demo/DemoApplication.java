package com.iupv.demo;

import lombok.RequiredArgsConstructor;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Security;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication{

    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        BouncyCastleProvider provider = new BouncyCastleProvider();
        Security.addProvider(provider);
        SpringApplication.run(DemoApplication.class, args);
    }

}
