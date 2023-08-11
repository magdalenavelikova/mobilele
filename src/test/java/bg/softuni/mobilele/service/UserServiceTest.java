package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.UserRegisterDto;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.entity.UserRoleEntity;
import bg.softuni.mobilele.model.enums.Role;
import bg.softuni.mobilele.model.mapper.UserMapper;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.repository.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {
    @Mock
    private UserRepository mockUserRepo;
    @Mock
    private UserMapper mockUserMapper;
    @Mock
    private UserRoleRepository mockUserRoleRepository;
    @Mock
    private EmailService emailService;
    @Mock
    private PasswordEncoder mockPasswordEncoder;
    private UserRegisterDto testUserRegisterDto;
    @Captor
    private ArgumentCaptor<UserEntity> userEntityArgumentCaptor;


    private UserService toTest;
    private Locale prefferedLocale;

    public UserServiceTest() {
    }

    @BeforeEach
    void setUp() {
        UserDetailsService testUserDetailsService = new AppUserDetailsService(mockUserRepo);
        toTest = new UserService(mockUserRepo, testUserDetailsService, mockUserMapper,
                mockUserRoleRepository, emailService, mockPasswordEncoder);
        UserEntity testUserEntity = new UserEntity() {
            {
                setEmail("newUser@example.com");
                setPassword("456123");
                setFirstName("Maggie");
                setLastName("Velikova");

            }

        };
        testUserRegisterDto = new UserRegisterDto() {
            {
                setEmail("newUser@example.com");
                setPassword("456123");
                setFirstName("Maggie");
                setLastName("Velikova");
            }

        };
        UserRoleEntity testUserRoleEntity = new UserRoleEntity();
        testUserRoleEntity.setRole(Role.USER);
        when(mockUserRepo.findByEmail(testUserRegisterDto.getEmail())).thenReturn(Optional.of(testUserEntity));
        when(mockPasswordEncoder.encode(testUserRegisterDto.getPassword())).thenReturn(testUserRegisterDto.getPassword());
        when(mockUserMapper.userDtoToUserEntity(testUserRegisterDto)).thenReturn(testUserEntity);
        when(mockUserRoleRepository.findById(2L)).thenReturn(Optional.of(testUserRoleEntity));
    }

    @Test
    void testRegisterAndLogin_SaveInvoked() {
        toTest.registerAndLogin(testUserRegisterDto, prefferedLocale);
        Mockito.verify(mockUserRepo).save(any());
    }

    @Test
    void testRegisterAndLogin_withCaptor() {
        toTest.registerAndLogin(testUserRegisterDto, prefferedLocale);
        Mockito.verify(mockUserRepo).save(userEntityArgumentCaptor.capture());
        UserEntity savedUserEntity = userEntityArgumentCaptor.getValue();
        assertEquals(testUserRegisterDto.getEmail(), savedUserEntity.getEmail());
        assertEquals(testUserRegisterDto.getFirstName(), savedUserEntity.getFirstName());
        assertEquals(testUserRegisterDto.getLastName(), savedUserEntity.getLastName());
        assertEquals(testUserRegisterDto.getPassword(), savedUserEntity.getPassword());
    }

    @Test
    void createUserIfNotExistTest() {
        when(mockUserRepo.findByEmail(testUserRegisterDto.getEmail())).thenReturn(null);
        toTest.createUserIfNotExist("newEmail@example.com");
        Mockito.verify(mockUserRepo).save(any());
    }

    @Test
    void createUserIfNotExistTestWith() {
        when(mockUserRepo.findByEmail(testUserRegisterDto.getEmail())).thenReturn(null);
        toTest.createUserIfNotExist("newEmail@example.com");
        Mockito.verify(mockUserRepo).save(userEntityArgumentCaptor.capture());
        UserEntity savedUserEntity = userEntityArgumentCaptor.getValue();
        assertEquals("newEmail@example.com", savedUserEntity.getEmail());
        assertNull(savedUserEntity.getPassword());

    }
}
