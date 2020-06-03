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
        if(user != null){
            model.addAttribute("user", user.getUsername());
        }else{
            model.addAttribute("user", "Guest");
        }
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PreAuthorize(value = "hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String forAdmin(){
        return "admin";
    }
}
