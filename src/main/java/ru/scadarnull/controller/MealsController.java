package ru.scadarnull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.scadarnull.domain.Meals;
import ru.scadarnull.service.MealsService;

@Controller
public class MealsController {

    @Autowired
    private MealsService mealsService;

    @GetMapping("/meals")
    public String meals(Model model){
        model.addAttribute("meals", mealsService.getAll());
        model.addAttribute("meal", new Meals());
        return "meals";
    }

    @PostMapping("/meals")
    public String addMeals(@ModelAttribute Meals meal){
        mealsService.addMeals(meal);
        return "redirect:/meals";
    }
}
