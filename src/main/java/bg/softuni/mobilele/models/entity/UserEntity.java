package bg.softuni.mobilele.models.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false,
            unique = true)
    private String email;

    private String password;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;

    private Boolean isActive;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRoleEntity> userRoles = new ArrayList<>();
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

}
