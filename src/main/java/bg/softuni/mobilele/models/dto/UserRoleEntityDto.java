package bg.softuni.mobilele.models.dto;

import bg.softuni.mobilele.models.entity.enums.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserRoleEntityDto {

    private Long id;

    private Role role;
}
