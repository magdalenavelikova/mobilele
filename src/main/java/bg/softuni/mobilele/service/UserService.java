package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.UserRegisterDto;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.mapper.UserMapper;
import bg.softuni.mobilele.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final UserMapper userMapper;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, UserDetailsService userDetailsService, UserMapper userMapper, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.userMapper = userMapper;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }


    public void registerAndLogin(UserRegisterDto userRegisterDto, Locale preferredLocale) {
        UserEntity user = userMapper.userDtoToUserEntity(userRegisterDto);
        var rowPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(rowPassword));
        user.setCreated(LocalDateTime.now());
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
