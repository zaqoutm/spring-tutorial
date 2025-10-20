package com.zaqout.spring_tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller // we can do this
public class SpringTutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTutorialApplication.class, args);
    }

    @RequestMapping("/home")
    public ResponseEntity<String> hello() {
        return ResponseEntity.status(200).body("Hello / from " + this.getClass().getName());
    }
}