package bg.softuni.mobilele.models.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserEntityDto extends BaseEntityDto {


    private String username;

    private String firstName;

    private String lastName;

    private Boolean isActive;

    private UserRoleEntityDto role;

    private String imageUrl;

}
