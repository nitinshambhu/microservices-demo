package com.example.users.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private int id;

    @NotNull(message = "User name cannot be null")
    @NotEmpty(message = "User name cannot be Empty")
    @Pattern(regexp = "[a-z A-z]{6,}", message = "User name should be at least 6 alphabets long & not have any numbers or special characters")
    private String name;
}
