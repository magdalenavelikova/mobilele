package bg.softuni.mobilele.model.entity;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false,
            unique = true)
    private String email;

    private String password;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column
    private Boolean isActive;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles = new ArrayList<>();
    @Column(name = "image_url")
    private String imageUrl;


}
