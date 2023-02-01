package com.example.recipesapp.model;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Recipe {

    @NotBlank
    private String name;
    @Positive
    private int time;
    @NotEmpty
    List<Ingredient> list;
    @NotEmpty
    List<String> steps;
}


