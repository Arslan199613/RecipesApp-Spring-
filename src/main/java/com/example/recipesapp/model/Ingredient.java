package com.example.recipesapp.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class Ingredient {

    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 10, message = "Имя должно быть в диапазоне от 2 до 10")
    private String name;
    @Min(value = 0, message = "Количество должно быть больше,чем 0")
    private int sum;
    @NotEmpty(message = "Единица измерения не может быть пустым")
    private String unit;
}


