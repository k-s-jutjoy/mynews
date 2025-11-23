package com.jutjoy.controller.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProfileCreateForm {

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    private String gender;

    @Size(max = 100)
    private String hobby;

    @Size(max = 30)
    private String introduction;
}
