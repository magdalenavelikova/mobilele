package bg.softuni.mobilele.service;

import bg.softuni.mobilele.exeption.ObjectNotFoundException;
import bg.softuni.mobilele.model.dto.UserRegisterDto;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.entity.UserRoleEntity;
import bg.softuni.mobilele.model.enums.Role;
import bg.softuni.mobilele.model.mapper.UserMapper;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.repository.UserRoleRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final UserMapper userMapper;
    private final UserRoleRepository userRoleRepository;

    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository,
                       UserDetailsService userDetailsService,
                       UserMapper userMapper,
                       UserRoleRepository userRoleRepository,
                       EmailService emailService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.userMapper = userMapper;
        this.userRoleRepository = userRoleRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUserIfNotExist(String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            UserEntity newUser = new UserEntity();
            newUser.setEmail(email);
            newUser.setPassword(null);
            newUser.setIsActive(true);
            newUser.setCreated(LocalDateTime.now());
            newUser.setFirstName("New");
            newUser.setLastName("User");
            newUser.setRoles(userRoleRepository.findAll()
                    .stream()
                    .filter(r -> r.getRole() != (Role.ADMIN))
                    .collect(Collectors.toList()));
            userRepository.save(newUser);
        }

    }

    public void registerAndLogin(UserRegisterDto userRegisterDto, Locale preferredLocale) {
        UserEntity user = userMapper.userDtoToUserEntity(userRegisterDto);
        var rowPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(rowPassword));
        user.setCreated(LocalDateTime.now());

        user.setRoles(userRoleRepository.findAll()
                .stream()
                .filter(r -> r.getRole() != (Role.ADMIN))
                .collect(Collectors.toList()));
        user.setIsActive(true);
        userRepository.save(user);
        login(user.getEmail());
        emailService.sendRegistrationEmail(
                userRegisterDto.getEmail(),
                userRegisterDto.getFirstName() + " " + userRegisterDto.getLastName(),
                preferredLocale);


    }



    public void login(String userName) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(userName);

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }

}
