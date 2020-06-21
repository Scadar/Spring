package ru.scadarnull.domain.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class DailyCalories {
    private Date time;
    private Long calories;

    public DailyCalories(Date time, Long calories) {
        this.time = time;
        this.calories = calories;
    }

    public DailyCalories(){}
}
