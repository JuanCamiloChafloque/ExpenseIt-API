package com.example.expenseitapi.entities;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserModel {

    @NotBlank(message = "User name must not be BLANK")
    private String name;

    @NotNull(message = "User email must not be NULL")
    @Email(message = "Enter a valid email")
    private String email;

    @NotNull(message = "User password must not be NULL")
    @Size(min = 5, message = "Password should be at least 5 characters")
    private String password;

    private Long age;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Long getAge() {
        return age;
    }
    public void setAge(Long age) {
        this.age = age;
    }

    
}
