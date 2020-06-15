package ru.scadarnull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.scadarnull.domain.Meals;
import ru.scadarnull.repo.MealsRepo;

import java.util.List;

@Service
public class MealsService {

    @Autowired
    private MealsRepo mealsRepo;

    public void addMeals(Meals meals){
        mealsRepo.save(meals);
    }

    public List<Meals> getAll(){
        return mealsRepo.findAll();
    }
}
