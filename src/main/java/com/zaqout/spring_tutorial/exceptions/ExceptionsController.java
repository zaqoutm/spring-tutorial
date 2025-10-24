package com.zaqout.spring_tutorial.exceptions;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exceptions")
public class ExceptionsController {

    @GetMapping("/price")
    ResponseEntity<Integer> testIllegalArgumentException(@RequestParam int price) throws IllegalArgumentException {
        if (price < 20) throw new IllegalArgumentException("too Cheap");
        if (price > 50) throw new IllegalArgumentException("too Expensive");
        return ResponseEntity.ok().body(price);
    }

    @PostMapping("/valid")
    ResponseEntity<RequestUserDTO> testValidation(@Valid @RequestBody RequestUserDTO requestUserDTO) {
        return ResponseEntity.ok().body(new RequestUserDTO(requestUserDTO.email() + "OK123"));
    }

}
