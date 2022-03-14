# ARCHITECTURE.md

## SearchRecipe.java
This file inplements the all different types of search, like search by Ingredients or by category or by name.

## Recipe.java
Recipe.java is the object file which contains all the basic information of Recipe.

## GetRecipeFromCVS.java
This file load all the information about recipes and ingredients of recipes form CSV files.

## Recipelist
This file manages the recipe objects in list by simply adding or removing recipes from csv and fake database.

## RecipeListInterface.java
RecipeListInterface.java is the interface of recipelist class and have signature of all the required methods

## AdapterMainPage.java
This file implements the home page GUI of this application.

## AdapterRecipe.java
This file implements second page GUI according to category, and s all the recipes according to the category. 

## DummyActivity.java
DummyActivity.java is used for testing in AndroidTest.

## ListRecipeActivity.java
ListRecipeActivity.java is used to list all recipes according to category on second page.

## MainActivity.java
MainActivity.java is used for all the widgets on main page.

## RecipeDetailActivity.java
RecipeDetailActivity is for recipe Ingredients and instrucitons. It will be implemented in Iteration2.



![flow chart]( img_source/chart.jpg)

## GUI templates

[templates](https://code.cs.umanitoba.ca/winter-2022-a02/group-10/irecipe/-/issues/28) Used for designing pages