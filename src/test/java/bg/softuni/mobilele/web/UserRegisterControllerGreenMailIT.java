package bg.softuni.mobilele.web;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegisterControllerGreenMailIT {
    @Value("${mail.host}")
    private String mailHost;
    @Value("${mail.port}")
    private Integer mailPort;
    @Value("${mail.username}")
    private String mailUsername;
    @Value("${mail.password}")
    private String mailPassword;
    @Autowired
    private MockMvc mockMvc;
    private GreenMail greenMail;

    @BeforeEach
    void setup() {
        greenMail = new GreenMail(new ServerSetup(mailPort, mailHost, "smtp"));
        greenMail.start();
        greenMail.setUser(mailUsername, mailPassword);
    }

    @AfterEach
    void tearDown() {
        greenMail.stop();
    }

    @Test
    void testRegistrationWithSuccess() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("email", "angela@example.com")
                        .param("firstName", "Angela")
                        .param("lastName", "Angelova")
                        .param("password", "password")
                        .param("confirmPassword", "password")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
        assertEquals(1, receivedMessages.length);
        MimeMessage welcomeMessage = receivedMessages[0];
        assertTrue(welcomeMessage.getContent().toString().contains("Angela Angelova"));
    }
    @Test
    void testRegistrationFailDuplicateEmail() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("email", "angela@example.com")
                        .param("firstName", "Angela")
                        .param("lastName", "Angelova")
                        .param("password", "password")
                        .param("confirmPassword", "password")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"));

        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
        assertEquals(0, receivedMessages.length);

    }
}
