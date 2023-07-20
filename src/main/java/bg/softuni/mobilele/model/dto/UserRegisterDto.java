package bg.softuni.mobilele.model.dto;


import bg.softuni.mobilele.model.validations.FieldMatch;
import bg.softuni.mobilele.model.validations.UniqueUserEmail;
import javax.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor

@FieldMatch(first = "password",
second = "confirmPassword",
message = "Passwords do not match")
public class UserRegisterDto {
    @NotEmpty(message="User email should be provided.")
    @Email(message="User email should be valid.")
    @UniqueUserEmail(message="User email should be unique.")
    private String email;
    @NotEmpty
    @Size(min = 5, max = 10)
    private String password;
    @NotEmpty
    @Size(min = 5, max = 10)
    private String confirmPassword;
    @NotEmpty
    @Size(min = 2, max = 20)
    private String firstName;
    @NotEmpty
    @Size(min = 2, max = 20)
    private String lastName;


}
