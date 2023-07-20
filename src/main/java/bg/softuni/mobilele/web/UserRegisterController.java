package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.UserRegisterDto;
import bg.softuni.mobilele.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("users")
public class UserRegisterController {
    private final UserService userService;
    private final LocaleResolver localeResolver;

    public UserRegisterController(UserService userService, LocaleResolver localeResolver) {
        this.userService = userService;
        this.localeResolver = localeResolver;
    }

    @ModelAttribute("userModel")
    public UserRegisterDto initUserModel() {
        return new UserRegisterDto();
    }

    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDto userModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);
            return "redirect:/users/register";
        }
        userService.registerAndLogin(userModel, localeResolver.resolveLocale(request));
        return "redirect:/";
    }
}
