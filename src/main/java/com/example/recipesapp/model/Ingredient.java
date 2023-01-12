package com.example.recipesapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class Ingredient {
    private String name;
    private int sum;
    private String unit;
}

