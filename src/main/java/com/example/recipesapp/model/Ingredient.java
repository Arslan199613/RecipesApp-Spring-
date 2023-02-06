package com.example.recipesapp.model;
import lombok.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Ingredient {

    @NotBlank(message = "Имя не может быть пустым")
    private String name;
    @Positive
    private int sum;
    private String unit;

    @Override
    public String toString() {
        return name + " " + " " + sum + " " + unit;
    }
}


