package com.example.recipesapp.model;
import lombok.*;
import javax.validation.constraints.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Recipe {

    @NotBlank(message = "Имя не может быть пустым")
    private String name;
    @Positive
    private int time;
    @NotEmpty
    List<Ingredient> list;
    @NotEmpty
    List<String> steps;

}



