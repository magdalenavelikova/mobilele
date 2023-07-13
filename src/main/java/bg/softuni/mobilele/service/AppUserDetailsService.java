package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.UserRegisterDto;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.entity.UserRoleEntity;
import bg.softuni.mobilele.model.mapper.UserMapper;
import bg.softuni.mobilele.model.user.MobileleUserDetails;
import bg.softuni.mobilele.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class AppUserDetailsService implements UserDetailsService {
//    private final UserRepository userRepository;
//
//
//    public AppUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<UserEntity> byEmail = userRepository.findByEmail(username);
//
//        return userRepository.findByEmail(username)
//                .map(this::map)
//                .orElseThrow(() -> new UsernameNotFoundException("User with " + username + "not found"));
//
//    }
//
//    private MobileleUserDetails map(UserEntity userEntity) {
//        return new MobileleUserDetails(
//                userEntity.getId(),
//                userEntity.getPassword(),
//                userEntity.getEmail(),
//                userEntity.getFirstName(),
//                userEntity.getLastName(),
//                userEntity.getRoles()
//                        .stream()
//                        .map(this::mapByGrantedAuthority)
//                        .toList());
//
////        return User.builder()
////                .username(userEntity.getEmail())
////                .password(userEntity.getPassword())
////                .authorities(userEntity
////                        .getRoles()
////                        .stream()
////                        .map(this::mapByGrantedAuthority)
////                        .collect(Collectors.toList()))
////                .build();
//
//
//    }
//
//    private GrantedAuthority mapByGrantedAuthority(UserRoleEntity userRoleEntity) {
//        return new SimpleGrantedAuthority("ROLE_" +
//                userRoleEntity
//                        .getRole()
//                        .name());
//    }

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userRepository.
                findByEmail(username).
                map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found!"));
    }

    private UserDetails map(UserEntity userEntity) {

        return new MobileleUserDetails(
                userEntity.getId(),
                userEntity.getPassword(),
                userEntity.getEmail(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.
                        getRoles().
                        stream().
                        map(this::map).
                        toList()
        );
    }

    private GrantedAuthority map(UserRoleEntity userRole) {
        return new SimpleGrantedAuthority("ROLE_" +
                userRole.
                        getRole().name());
    }
}