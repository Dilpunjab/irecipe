package comp3350.iRecipe;

import org.junit.Before;
import org.junit.Test;

import comp3350.iRecipe.Objects.Recipe;

public class RecipeTest {

    Recipe recipe;

    @Before
    public void setUp()
    {
        recipe =  new Recipe("Banana" , "snack");
    }

    @Test
    public void addIngredientTest()
    {

    }

}
