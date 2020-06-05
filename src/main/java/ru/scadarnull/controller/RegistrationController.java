package ru.scadarnull.controller;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.scadarnull.domain.User;
import ru.scadarnull.service.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String showFormRegistration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationUser(@Valid User user,
                                   BindingResult bindingResult,
                                   Model model)
    {
        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            return "registration";
        }

        if(userService.checkEmail(user.getEmail())){
            model.addAttribute("invalidEmail", "Почта занята");
            model.addAttribute("user", user);
            return "registration";
        }

        if(!userService.addUser(user)){
            model.addAttribute("userExists", "Такой пользователь уже есть");
            model.addAttribute("user", user);
            return "registration";
        }

        model.addAttribute("regInfo", "Успешная регистрация");
        return "/login";
    }
}
