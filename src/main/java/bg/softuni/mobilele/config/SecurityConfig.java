package bg.softuni.mobilele.config;


import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.service.AppUserDetailsService;
import bg.softuni.mobilele.service.OAuthSuccessHandler;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true,
//        securedEnabled = true,
//        jsr250Enabled = true)
public class SecurityConfig {




    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   OAuthSuccessHandler oAuthSuccessHandler
                                                   ) throws Exception {
        http.
                // define which requests are allowed and which not
                        authorizeRequests().
                // everyone can download static resources (css, js, images)
                        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                // everyone can log and register
                        antMatchers("/", "/users/login", "/users/register").permitAll().
                antMatchers("/offers/add").authenticated().
                antMatchers("/offers/**").permitAll().
                // all other pages are available for logger in users
                        anyRequest().
                authenticated().
                and().
                // configuration of form login
                        formLogin().
                // the custom login form
                        loginPage("/users/login").
                // the name of the username form field
                        usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                // the name of the password form field
                        passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                // where to go in case that the login is successful
                        defaultSuccessUrl("/").
                // where to go in case that the login failed
                        failureForwardUrl("/users/login-error").
                and().
                // configure logout
                        logout().
                // which is the logout url, must be POST request
                        logoutUrl("/users/logout").
                // on logout go to the home page
                        logoutSuccessUrl("/").
                // invalidate the session and delete the cookies
                        invalidateHttpSession(true).
                deleteCookies("JSESSIONID").
                and().
                // allow oauth login
                oauth2Login().
                loginPage("/users/login").
                successHandler(oAuthSuccessHandler);


        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new AppUserDetailsService(userRepository);

    }
}
