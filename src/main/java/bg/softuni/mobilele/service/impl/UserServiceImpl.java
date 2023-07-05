package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.UserLoginDto;
import bg.softuni.mobilele.model.dto.UserRegisterDto;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.mapper.UserMapper;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.service.UserService;
import bg.softuni.mobilele.user.CurrentUser;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;
    private final CurrentUser currentUser;
    private final PasswordEncoder passwordEncoder;
    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, CurrentUser currentUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean login(UserLoginDto userLoginDto) {

        Optional<UserEntity> userOpt = userRepository.findByEmail(userLoginDto.getUsername());
        if (userOpt.isEmpty()) {
            LOGGER.info("User with email [{}] not found.", userLoginDto.getUsername());
            return false;
        }
        var rowPassword = userLoginDto.getPassword();
        var hashPassword = userOpt.get().getPassword();
        boolean success = passwordEncoder.matches(rowPassword, hashPassword);

        if (success) {
            login(userOpt.get());
        } else {
            logout();
        }
        return success;
    }

    @Override
    public void registerAndLogin(UserRegisterDto userRegisterDto) {
        UserEntity user = userMapper.userDtoToUserEntity(userRegisterDto);
        var rowPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(rowPassword));
        user.setCreated(LocalDateTime.now());
        userRepository.save(user);
        login(user);
    }

    public void login(UserEntity userEntity) {
        currentUser.setLoggedIn(true);
        currentUser.setName(userEntity.getFirstName() + " " + userEntity.getLastName());

    }

    @Override
    public void logout() {
        currentUser.clear();
    }


}
