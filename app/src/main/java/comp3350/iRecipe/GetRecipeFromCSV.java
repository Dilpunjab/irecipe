package comp3350.iRecipe;


import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

public class GetRecipeFromCSV extends AppCompatActivity implements RecipeListInterface{

    //Column number in Recipe.csv
    static final int RECIPENAME = 0;
    static final int CATEGORY = 1;
    static final int COOKINGLEVEL = 2;
    static final int PREPTIME = 3;
    static final int COOKINGTIME = 4;
    static final int SERVING = 5;

    //Column number in Ingredients.csv
    static final int INGREDIENTS = 1;

    //Column number in keyIngredients.csv
    static final int KEYINGREDIENTS = 1;

    //Column number in Instructions.csv
    static final int INSTRUCTION = 1;

    // A list of all recipes
    private ArrayList<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipeList = getRecipe();
    }

    public ArrayList<Recipe> getRecipe(){
        ArrayList<Recipe> allRecipe = new ArrayList<>();
        try{

            InputStream recipeIn = getAssets().open("Recipe.csv");
            BufferedReader recipeReader = new BufferedReader(new InputStreamReader(recipeIn));

            //Read Recipe data line by line
            String line = recipeReader.readLine();  //Skip the first line, which is the header, not the data
            while( (line = recipeReader.readLine()) != null ){
                //Log.i("Read recipe.csv")

                String[] data = line.split(",");
                String name = data[RECIPENAME];
                String insturctions = "";
                ArrayList<String> ingred = new ArrayList<>();
                ArrayList<String> keyIngred = new ArrayList<>();

                InputStream instrucIn = getAssets().open("Instructions.csv");
                BufferedReader readInstruct = new BufferedReader(new InputStreamReader(instrucIn));
                String instructLine = readInstruct.readLine();      //Skip first header line
                while( (instructLine = readInstruct.readLine()) != null){

                    String[] instructData = instructLine.split(",");
                    //If the name matches, add to the Recipe object
                    if(instructData[RECIPENAME].equals(name) ){
                        insturctions += instructData[INSTRUCTION] + "\n";
                    }
                }
                readInstruct.close();
                instrucIn.close();

                Recipe recipe = new Recipe(data[RECIPENAME], data[CATEGORY], data[COOKINGLEVEL],
                        Integer.parseInt(data[PREPTIME]), Integer.parseInt(data[COOKINGTIME]),
                        Integer.parseInt(data[SERVING]), ingred, keyIngred, insturctions);

                //Read ingredients and add to recipe
                InputStream ingredIn = getAssets().open("Ingredients.csv");
                BufferedReader readIngred = new BufferedReader(new InputStreamReader(ingredIn));
                String ingredLine = readIngred.readLine();      //Skip first header line
                while( (ingredLine = readIngred.readLine()) != null){

                    String[] ingredData = ingredLine.split(",");
                    //If the name matches, add to the Recipe object
                    if(ingredData[RECIPENAME].equals(name) ){
                        recipe.addToIngredients(ingredData[INGREDIENTS]);
                    }
                }
                readIngred.close();
                ingredIn.close();

                //Read Key ingredients and add to recipe
                InputStream keyIngredIn = getAssets().open("KeyIngredients.csv");
                BufferedReader readKeyIngred = new BufferedReader(new InputStreamReader(keyIngredIn));
                String keyIngredLine = readKeyIngred.readLine();    //Skip first header line
                while( (keyIngredLine = readKeyIngred.readLine()) != null){

                    String[] keyIngredData = keyIngredLine.split(",");
                    //If the name matches, add to the Recipe object
                    if(keyIngredData[RECIPENAME].equals(name)){
                        recipe.addToKeyIngredients(keyIngredData[KEYINGREDIENTS]);
                    }
                }
                readKeyIngred.close();
                keyIngredIn.close();

                allRecipe.add(recipe);

            }//End of reading Recipe.csv
            recipeIn.close();
            recipeReader.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return allRecipe;
    }

    @Override
    public boolean addRecipe(Recipe newRecipe)
    {
        return recipeList.add(newRecipe);
    } // will add a new recipe to the end of the list.

    @Override
    public boolean removeRecipe(Recipe toRemove)
    {
        return recipeList.remove(toRemove);
    } // remove the recipe specified in the parameter

    @Override
    public Recipe searchByName(String nameOfRecipe)
    {

        for (Recipe retrieved_recipe : recipeList) {
            if (nameOfRecipe.equals(retrieved_recipe.getName())) {
                return retrieved_recipe;
            }
        }

        return null;
    } // search the recipe of the given name

    @Override
    public ArrayList<Recipe> getAllRecipes() {
        return new ArrayList<>(recipeList);
    } // return a deep copy of the recipe list.

    @Override
    public ArrayList<Recipe> getRecipesByCategory(String category) {

        ArrayList<Recipe> recipeListByCategory = new ArrayList<>();

        for (Recipe retrieved_recipe : recipeList) {
            if (category.equals(retrieved_recipe.getCategory())) {
                recipeListByCategory.add(retrieved_recipe);
            }
        }

        return recipeListByCategory;
    } // return a list of all recipe from the same category
}