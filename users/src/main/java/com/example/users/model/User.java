package com.example.users.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    private String name;
}
