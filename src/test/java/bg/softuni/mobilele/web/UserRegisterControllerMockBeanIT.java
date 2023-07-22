package bg.softuni.mobilele.web;

import bg.softuni.mobilele.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;
import java.util.Locale;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegisterControllerMockBeanIT {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmailService mockEmailService;

    @Test
    void testRegistrationPageShown() throws Exception {
        mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth-register"));
    }

    @Test
    void testRegistrationWithSuccess() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("email", "angel@example.com")
                        .param("firstName", "Angel")
                        .param("lastName", "Angelov")
                        .param("password", "password")
                        .param("confirmPassword", "password")
                        .cookie(new Cookie("lang", Locale.GERMAN.getLanguage()))
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        verify(mockEmailService).sendRegistrationEmail("angel@example.com", "Angel Angelov", Locale.GERMAN);
    }

    @Test
    void testRegistrationFail() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("email", "angel@example.com")
                        .param("firstName", " ")
                        .param("lastName", "Angelov")
                        .param("password", "password")
                        .param("confirmPassword", "password")
                        .cookie(new Cookie("lang", Locale.GERMAN.getLanguage()))
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"));
        verify(mockEmailService, never()).sendRegistrationEmail("angel@example.com", " Angelov", Locale.GERMAN);
    }



}
