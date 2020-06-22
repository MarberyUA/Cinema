package com.dev.cinema.model.dto.request;

import com.dev.cinema.lib.EmailConstraint;
import com.dev.cinema.lib.FieldMatchConstraint;
import javax.validation.constraints.NotNull;

@FieldMatchConstraint(field = "password", fieldMatch = "repeatPassword",
        message = "Passwords do not match!")
public class AuthenticationRequestDto {
    @EmailConstraint(message = "incorrect email")
    private String email;
    @NotNull(message = "name must not be null")
    private String name;
    private String password;
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
