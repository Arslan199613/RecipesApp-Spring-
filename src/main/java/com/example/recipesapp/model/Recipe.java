package com.example.recipesapp.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class Recipe {

    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 10, message = "Имя должно быть в диапазоне от 2 до 10")
    private String name;
    @Min(value = 0, message = "Количество времени должно быть больше,чем 0")
    private int time;
    @NotEmpty(message = "Лист не может быть пустым")
    List<Ingredient> list;
    @NotEmpty(message = "Шаги не могут быть пустым")
    List<String> steps;
}


