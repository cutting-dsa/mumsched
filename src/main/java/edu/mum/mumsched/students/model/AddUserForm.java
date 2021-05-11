package edu.mum.mumsched.students.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class AddUserForm {
    @NonNull
    @NotBlank(message = "Sorry, first name cannot be blank")
    private String firstName;

    @NonNull
    @NotBlank(message = "Sorry, last name cannot be blank")
    private String lastName;

    @NonNull
    @NotBlank(message = "Sorry, email cannot be blank")
    @Email
    private String email;
}
