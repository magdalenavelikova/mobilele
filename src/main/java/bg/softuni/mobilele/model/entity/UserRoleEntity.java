package bg.softuni.mobilele.model.entity;

import bg.softuni.mobilele.model.enums.Role;
import javax.persistence.*;




@Entity
@Table(name = "roles")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "name")
    private Role role;

    public UserRoleEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
