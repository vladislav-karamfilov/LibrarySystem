package com.librarysystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class LibrarySystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibrarySystemApplication.class, args);
    }
}
