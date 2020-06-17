package ru.scadarnull.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.scadarnull.domain.Meals;
import ru.scadarnull.domain.User;
import ru.scadarnull.service.MealsService;

@Controller
public class MealsController {

    private static final Logger LOG = LoggerFactory.getLogger(MealsController.class);

    @Autowired
    private MealsService mealsService;

    @GetMapping("/meals")
    public String meals(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("meals", mealsService.getUserMeals(user));
        model.addAttribute("meal", new Meals());
        return "meals";
    }

    @PostMapping("/meals")
    public String addMeals(@AuthenticationPrincipal User user, @ModelAttribute Meals meal){
        meal.setUser(user);
        mealsService.addEditMeals(meal);
        return "redirect:/meals";
    }

    @GetMapping("/meals/delete/{id}")
    public String deleteMeals(@AuthenticationPrincipal User user, @PathVariable Long id){
        mealsService.delete(user, id);
        return "redirect:/meals";
    }

    @GetMapping("/meals/edit/{meals}")
    public String editFormMeals(@AuthenticationPrincipal User user, @PathVariable Meals meals, Model model){
        if(meals == null){
            return "redirect:/meals";
        }
        if(meals.getUser().getId().equals(user.getId())){
            model.addAttribute("meal", meals);
            return "editMeals";
        }
        return "redirect:/meals";
    }

    @PostMapping("/meals/edit")
    public String editMeals(@AuthenticationPrincipal User user, @ModelAttribute Meals meals){
        meals.setUser(user);
        mealsService.addEditMeals(meals);
        return "redirect:/meals";
    }

    @GetMapping("/meals/edit")
    public String redirectToMeals(){
        return "redirect:/meals";
    }
}
