package bg.softuni.mobilele.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false)
    private String username;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;

    private Boolean isActive;
    @ManyToOne
    private UserRoleEntity role;
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

}
