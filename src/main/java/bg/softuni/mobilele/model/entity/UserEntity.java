package bg.softuni.mobilele.model.entity;





import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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

    public UserEntity() {
    }

    public String getEmail() {
        return email;
    }



    public String getPassword() {
        return password;
    }



    public String getFirstName() {
        return firstName;
    }



    public String getLastName() {
        return lastName;
    }



    public Boolean getIsActive() {
        return isActive;
    }



    public List<UserRoleEntity> getRoles() {
        return roles;
    }


    public void addRole(UserRoleEntity role) {
        this.roles.add(role);
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Boolean getActive() {
        return isActive;
    }

    public UserEntity setIsActive(Boolean active) {
        isActive = active;
        return this;
    }

    public UserEntity setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public UserEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
