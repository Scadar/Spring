package ru.scadarnull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.scadarnull.domain.RegistrationForm;
import ru.scadarnull.service.UserService;

@Controller
public class RegistrationController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String showFormRegistration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationUser(RegistrationForm form, Model model){

        if(!userService.addUser(form)){
            model.addAttribute("error", "ERROR");
            return "registration";
        }

        model.addAttribute("regInfo", "Успешная регистрация");
        return "/login";
    }
}
