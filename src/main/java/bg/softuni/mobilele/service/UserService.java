package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.UserLoginDto;
import bg.softuni.mobilele.model.dto.UserRegisterDto;

public interface UserService {
    boolean login(UserLoginDto userLoginDto);

    void registerAndLogin(UserRegisterDto userRegisterDto);

    void logout();

}
