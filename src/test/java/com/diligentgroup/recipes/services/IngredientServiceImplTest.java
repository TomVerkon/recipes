package com.diligentgroup.recipes.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.diligentgroup.recipes.command.IngredientCommand;
import com.diligentgroup.recipes.converters.IngredientCommandToIngredient;
import com.diligentgroup.recipes.converters.IngredientToIngredientCommand;
import com.diligentgroup.recipes.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.diligentgroup.recipes.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.diligentgroup.recipes.domain.Ingredient;
import com.diligentgroup.recipes.domain.Recipe;
import com.diligentgroup.recipes.repositories.IngredientRepository;
import com.diligentgroup.recipes.repositories.RecipeRepository;
import com.diligentgroup.recipes.repositories.UnitOfMeasureRepository;

public class IngredientServiceImplTest {

	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;

	@Mock
	RecipeRepository recipeRepository;

	@Mock
	UnitOfMeasureRepository uomRepository;

	@Mock
	IngredientRepository ingredientRepository;

	IngredientService ingredientService;

	// init converters
	public IngredientServiceImplTest() {
		this.ingredientToIngredientCommand = new IngredientToIngredientCommand(
				new UnitOfMeasureToUnitOfMeasureCommand());
		this.ingredientCommandToIngredient = new IngredientCommandToIngredient(
				new UnitOfMeasureCommandToUnitOfMeasure());
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		ingredientService = new IngredientServiceImpl(recipeRepository, uomRepository, ingredientRepository,
				ingredientToIngredientCommand, ingredientCommandToIngredient);
	}

	@Test
	public void findByRecipeIdAndId() throws Exception {
	}

	@Test
	public void findByRecipeIdAndReceipeIdHappyPath() throws Exception {
		// given
		Recipe recipe = new Recipe();
		recipe.setId("1");

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId("1");

		Ingredient ingredient2 = new Ingredient();
		ingredient2.setId("1");

		Ingredient ingredient3 = new Ingredient();
		ingredient3.setId("3");

		recipe.addIngredient(ingredient1);
		recipe.addIngredient(ingredient2);
		recipe.addIngredient(ingredient3);
		Optional<Recipe> recipeOptional = Optional.of(recipe);

		when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

		// then
		IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId("1", "3");

		// when
		assertEquals(String.valueOf(3L), ingredientCommand.getId());
		assertEquals(String.valueOf(1L), ingredientCommand.getRecipeId());
		verify(recipeRepository, times(1)).findById(anyString());
	}

	@Test
	public void testSaveRecipeCommand() throws Exception {
		// given
		IngredientCommand command = new IngredientCommand();
		command.setId("3");
		command.setRecipeId("2");

		Optional<Recipe> recipeOptional = Optional.of(new Recipe());

		Recipe savedRecipe = new Recipe();
		savedRecipe.addIngredient(new Ingredient());
		savedRecipe.getIngredients().iterator().next().setId("3");

		when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);
		when(recipeRepository.save(any())).thenReturn(savedRecipe);

		// when
		IngredientCommand savedCommand = ingredientService.saveIngredient(command);

		// then
		assertEquals(String.valueOf(3L), savedCommand.getId());
		verify(recipeRepository, times(1)).findById(anyString());
		verify(recipeRepository, times(1)).save(any(Recipe.class));

	}

	@Test
	public void testDeleteIngredientById() throws Exception {

	}
}