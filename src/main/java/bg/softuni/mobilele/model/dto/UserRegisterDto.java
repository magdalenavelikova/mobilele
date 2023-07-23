package bg.softuni.mobilele.model.dto;


import bg.softuni.mobilele.model.validations.FieldMatch;
import bg.softuni.mobilele.model.validations.UniqueUserEmail;
import javax.validation.constraints.*;




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

    public UserRegisterDto() {
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
