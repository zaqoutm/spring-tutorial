package com.zaqout.spring_tutorial.exceptions;

import jakarta.validation.constraints.NotBlank;

public record RequestUserDTO(
        @NotBlank(message = "Name is mandatory") String email
) {
}
