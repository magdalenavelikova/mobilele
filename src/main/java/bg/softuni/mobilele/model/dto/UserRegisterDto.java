package bg.softuni.mobilele.model.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDto {
    private String email;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;

}
