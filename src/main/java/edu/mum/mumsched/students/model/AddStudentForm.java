package edu.mum.mumsched.students.model;

import edu.mum.mumsched.entries.entity.Entry;
import edu.mum.mumsched.users.model.AppUser;
import edu.mum.mumsched.users.model.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class AddStudentForm {
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

    @NonNull
    private Long entryId;

    @NonNull
    private Track track;

    public AppUser toUser() {
        AppUser newUser = new AppUser();
        newUser.setActive(true);
        newUser.setEmail(this.getEmail());
        newUser.setPassword("secret");
        newUser.setFirstName(this.getFirstName());
        newUser.setLastName(this.getLastName());
        newUser.setRole(Role.STUDENT.name());
        return newUser;
    }
}
