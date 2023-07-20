package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.entity.UserRoleEntity;
import bg.softuni.mobilele.model.enums.Role;
import bg.softuni.mobilele.model.user.MobileleUserDetails;
import bg.softuni.mobilele.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AppUserDetailsServiceTest {
    @Mock
    private UserRepository mockUserRepo;
    private AppUserDetailsService toTest;
    private UserEntity testUserEntity;

    @BeforeEach
    void setUp() {
        toTest = new AppUserDetailsService(mockUserRepo);
        testUserEntity = new UserEntity() {
            {
                setEmail("test@gmail.com");
                setPassword("456123");
                setFirstName("Maggie");
                setLastName("Velikova");
                setIsActive(true);
                setRoles(List.of(
                        new UserRoleEntity() {{
                            setRole(Role.ADMIN);
                        }},
                        new UserRoleEntity() {{
                            setRole(Role.USER);
                        }}));
                setImageUrl("http://test.html");
            }

        };
        when(mockUserRepo.findByEmail(testUserEntity.getEmail()))
                .thenReturn(Optional.of(testUserEntity));
    }

    @Test
    void testLoadUserByUsernameWhenUserExist() {

        MobileleUserDetails userDetails = (MobileleUserDetails) toTest.loadUserByUsername(testUserEntity.getEmail());
        assertEquals(testUserEntity.getEmail(), userDetails.getUsername());
        assertEquals(testUserEntity.getFirstName(), userDetails.getFirstName());
        assertEquals(testUserEntity.getLastName(), userDetails.getLastName());
        assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        assertEquals(testUserEntity.getFirstName() + " " + testUserEntity.getLastName(), userDetails.getFullName());
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        assertEquals(2, authorities.size());
        Iterator<? extends GrantedAuthority> authoritiesIterator = authorities.iterator();

        assertEquals("ROLE_" + Role.ADMIN.name(), authoritiesIterator.next().getAuthority());
        assertEquals("ROLE_" + Role.USER.name(), authoritiesIterator.next().getAuthority());

    }

    @Test
    void testLoadUserByUsernameWhenUserDoesNotExist() {
        assertThrows(UsernameNotFoundException.class, (Executable) toTest.loadUserByUsername("nonexist@email.com"));
    }
}