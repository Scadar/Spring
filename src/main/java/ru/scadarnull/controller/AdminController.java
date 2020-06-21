package ru.scadarnull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.scadarnull.domain.User;
import ru.scadarnull.service.AdminService;
import ru.scadarnull.service.UserService;

import javax.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin")
    public String forAdmin(Model model){
        model.addAttribute("users", adminService.getAll());
        return "admin";
    }

    @GetMapping("/admin/delete/{user}")
    public String deleteUser(RedirectAttributes redirectAttributes, @PathVariable User user){
        if (!adminService.deleteUser(user)) {
            redirectAttributes.addFlashAttribute("errorDeleteUser", "Удаление admin и user запрещено");
        }
        return "redirect:/admin";
    }

}
