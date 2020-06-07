package ru.scadarnull.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.scadarnull.domain.User;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(@AuthenticationPrincipal User user, Model model){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PreAuthorize(value = "hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String forAdmin(){
        return "admin";
    }
}
