package com.diligentgroup.recipes.bootstrap;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.diligentgroup.recipes.domain.Category;
import com.diligentgroup.recipes.domain.Difficulty;
import com.diligentgroup.recipes.domain.Ingredient;
import com.diligentgroup.recipes.domain.Note;
import com.diligentgroup.recipes.domain.Recipe;
import com.diligentgroup.recipes.domain.UnitOfMeasure;
import com.diligentgroup.recipes.repositories.CategoryRepository;
import com.diligentgroup.recipes.repositories.RecipeRepository;
import com.diligentgroup.recipes.repositories.UnitOfMeasureRepository;

@Component
public class DataLoader implements CommandLineRunner {
	
	private final static String AMERICAN = "American";
	private final static String ITALIAN = "Italian";
	private final static String FAST_FOOD = "Fast Food";
	private final static String MEXICAN = "Mexican";
	private final static String TEASPOON = "Tsp";
	private final static String TEASPOONS = "Tsps";
	private final static String TABLESPOON = "Tbsp";
	private final static String TABLESPOONS = "Tbsps";
	private final static String CUP = "Cup";
	private final static String CUPS = "Cups";
	private final static String PINCH = "Pinch";
	private final static String PINCHES = "Pinches";
	private final static String OUNCE = "Ounce";
	private final static String OUNCES = "Ounces";
	private final static String EACH = "Each";
	private final static String DASH = "Dash";
	
	private final static String[] categoryDescriptions = {AMERICAN, ITALIAN, FAST_FOOD, MEXICAN};
	private final static String[] uomDescriptions = {TEASPOON, TEASPOONS, TABLESPOON, TABLESPOONS, CUP, CUPS, 
			PINCH, PINCHES, OUNCE, OUNCES, EACH, DASH};
	
//	private final static String GUACAMOLE_DESC = "Guacamole, a dip made from avocados, " 
//			+ "is originally from Mexico. The name is derived from two Aztec Nahuatl " 
//			+ "words—ahuacatl (avocado) and molli (sauce).";
//	private final static String GUACAMOLE_DIRECTIONS = "1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\r\n"
//			+ "\r\n"
//			+ "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\r\n"
//			+ "\r\n"
//			+ "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\r\n"
//			+ "\r\n"
//			+ "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\r\n"
//			+ "\r\n"
//			+ "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\r\n"
//			+ "\r\n"
//			+ "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\r\n"
//			+ "\r\n"
//			+ "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.";
//	private final static String GUACAMOLE_IMAGE_URL = "https://www.simplyrecipes.com/wp-content/uploads/2014/05/guacamole-horiz-a-1600.jpg";
//	private static final Object[][] GUACAMOLE_INGREDIENT_COMPONENTS = {
//			{new BigDecimal(2), "ripe avocados", EACH},
//			{new BigDecimal(0.5), "Kosher salt", TEASPOON},
//			{new BigDecimal(1), "fresh lime juice or lemon juice", TABLESPOON},
//			{new BigDecimal(0.25), "minced red onion or thinly sliced green onion", CUP},
//			{new BigDecimal(2), "serrano chiles, stems and seeds removed, minced", EACH},
//			{new BigDecimal(2), "cilantro (leaves and tender stems), finely chopped", TABLESPOONS},
//			{new BigDecimal(1), "freshly grated black pepper", DASH},
//			{new BigDecimal(0.5), "ripe tomato, seeds and pulp removed, chopped", EACH}
//	};
//	private static final String GUACAMOLE_NOTE = "Garnish with red radishes or jicama. Serve with tortilla chips.";
//	private static final String GUACAMOLE_SOURCE = "Simply Recipes";
//	private static final String GUACAMOLE_URL = "https://www.simplyrecipes.com/recipes/perfect_guacamole/";

	private CategoryRepository categoryRepository;
	private RecipeRepository recipeRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;

	public DataLoader(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		if (recipeRepository.count() == 0) {
			loadData();
		}
	}
	
	private Ingredient createIngredient (Recipe recipe, Object[] ingredientComponent) {
		Ingredient ingredient = new Ingredient();
		ingredient.setAmount((BigDecimal)ingredientComponent[0]);
		ingredient.setDescription((String)ingredientComponent[1]);
		ingredient.setUom(unitOfMeasureRepository.findByDescription((String)ingredientComponent[2]).get()); 
		ingredient.setRecipe(recipe);
		return ingredient;
	}
	
	private Recipe buildGuacamoleRecipe() {
		final String DESC = "Guacamole, a dip made from avocados, " 
				+ "is originally from Mexico. The name is derived from two Aztec Nahuatl " 
				+ "words—ahuacatl (avocado) and molli (sauce).";
		final String DIRECTIONS = "1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\r\n"
				+ "\r\n"
				+ "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\r\n"
				+ "\r\n"
				+ "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\r\n"
				+ "\r\n"
				+ "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\r\n"
				+ "\r\n"
				+ "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\r\n"
				+ "\r\n"
				+ "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\r\n"
				+ "\r\n"
				+ "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.";
		final String IMAGE_URL = "https://www.simplyrecipes.com/wp-content/uploads/2014/05/guacamole-horiz-a-1600.jpg";
		final Object[][] INGREDIENT_COMPONENTS = {
				{new BigDecimal(2), "ripe avocados", EACH},
				{new BigDecimal(0.5), "Kosher salt", TEASPOON},
				{new BigDecimal(1), "fresh lime juice or lemon juice", TABLESPOON},
				{new BigDecimal(0.25), "minced red onion or thinly sliced green onion", CUP},
				{new BigDecimal(2), "serrano chiles, stems and seeds removed, minced", EACH},
				{new BigDecimal(2), "cilantro (leaves and tender stems), finely chopped", TABLESPOONS},
				{new BigDecimal(1), "freshly grated black pepper", DASH},
				{new BigDecimal(0.5), "ripe tomato, seeds and pulp removed, chopped", EACH}
		};
		final String NOTE = "Garnish with red radishes or jicama. Serve with tortilla chips.";
		final Integer SERVINGS = 4;
		final String SOURCE = "Simply Recipes";
		final String URL = "https://www.simplyrecipes.com/recipes/perfect_guacamole/";
		return buildRecipe(MEXICAN, 10, 0, DESC, Difficulty.EASY, DIRECTIONS,
				IMAGE_URL, INGREDIENT_COMPONENTS, NOTE, SERVINGS, SOURCE, URL);
	}
	
	private Recipe buildRecipe(String category, Integer prepTime, Integer cookTime, 
			String description, Difficulty difficulty, String directions, String imageUrl, 
			Object[][] ingredients, String note, Integer servings, String source, String url) {
		Recipe recipe = new Recipe();
		recipe.addCategory(categoryRepository.findByDescription(category).get());
		recipe.setPrepTime(prepTime);
		recipe.setCookTime(cookTime);
		recipe.setDescription(description);
		recipe.setDifficulty(difficulty);
		recipe.setDirections(directions);
		recipe.setImage(fetchImageAsByteArray(imageUrl));
		for (Object[] ingredientComponents : ingredients) {
			recipe.addIngredient(createIngredient(recipe, ingredientComponents));
		}
		Note recipeNote = new Note();
		recipeNote.setRecipe(recipe);
		recipeNote.setRecipeNotes(note);
		recipe.setNotes(recipeNote);
		
		recipe.setServings(servings);
		recipe.setSource(source);
		recipe.setUrl(url);
		return recipe;
	}

	private void loadData() {
		
		createCategories(categoryDescriptions);
		
		createUnitOfMeasures(uomDescriptions);
		
		recipeRepository.save(buildGuacamoleRecipe());
		
//		Recipe recipe = new Recipe();
//		recipe.setDescription("Perfect Guacamole");
//		recipe.addCategory(categoryRepository.findByDescription(MEXICAN).get());
//		recipe.setPrepTime(10);
//		recipe.setCookTime(0);
//		recipe.setDescription(GUACAMOLE_DESC);
//		recipe.setDifficulty(Difficulty.EASY);
//		recipe.setDirections(GUACAMOLE_DIRECTIONS);
//		recipe.setImage(fetchImageAsByteArray(GUACAMOLE_IMAGE_URL));
//		
//		for (Object[] ingredientComponent : GUACAMOLE_INGREDIENT_COMPONENTS) {
//			recipe.addIngredient(createIngredient(recipe, ingredientComponent));
//		}
		
//		Ingredient ingredient = createIngredient(recipe, new BigDecimal(2), "ripe avocados", unitOfMeasureRepository.findByDescription(EACH).get());
//		recipe.addIngredient(ingredient);
//		
//		ingredient = createIngredient(recipe, new BigDecimal(0.5), "Kosher salt", unitOfMeasureRepository.findByDescription(TEASPOON).get());
//		recipe.addIngredient(ingredient);
//		
//		ingredient = createIngredient(recipe, new BigDecimal(1), "fresh lime juice or lemon juice", unitOfMeasureRepository.findByDescription(TABLESPOON).get());
//		recipe.addIngredient(ingredient);
		
//		ingredient = createIngredient(recipe, new BigDecimal(0.25), "minced red onion or thinly sliced green onion", unitOfMeasureRepository.findByDescription(CUP).get());
//		recipe.addIngredient(ingredient);
//		
//		ingredient = createIngredient(recipe, new BigDecimal(2), "serrano chiles, stems and seeds removed, minced", unitOfMeasureRepository.findByDescription(EACH).get());
//		recipe.addIngredient(ingredient);
//		
//		ingredient = createIngredient(recipe, new BigDecimal(2), "cilantro (leaves and tender stems), finely chopped", unitOfMeasureRepository.findByDescription(TABLESPOONS).get());
//		recipe.addIngredient(ingredient);
//		
//		ingredient = createIngredient(recipe, new BigDecimal(1), "freshly grated black pepper", unitOfMeasureRepository.findByDescription(DASH).get());
//		recipe.addIngredient(ingredient);
//		
//		ingredient = createIngredient(recipe, new BigDecimal(0.5), "ripe tomato, seeds and pulp removed, chopped", unitOfMeasureRepository.findByDescription(EACH).get());
//		recipe.addIngredient(ingredient);
		
		//recipeRepository.save(recipe);
	}
	
//	private Ingredient createIngredient(Recipe recipe, BigDecimal amount, String description, UnitOfMeasure uom) {
//		Ingredient ingredient = new Ingredient();
//		ingredient.setAmount(amount);
//		ingredient.setDescription(description);
//		ingredient.setUom(uom); 
//		ingredient.setRecipe(recipe);
//		return ingredient;
//	}
//	
//	private void addIngredientsToRecipe(Recipe recipe, Object[][] GUACAMOLE_INGREDIENT_COMPONENTS) {
//		for (int i = 0; i < GUACAMOLE_INGREDIENT_COMPONENTS.length; i++) {
//			Ingredient ingredient = new Ingredient();
//			ingredient.setAmount((BigDecimal)GUACAMOLE_INGREDIENT_COMPONENTS[i][0]);
//			ingredient.setDescription((String)GUACAMOLE_INGREDIENT_COMPONENTS[i][1]);
//			ingredient.setUom(unitOfMeasureRepository.findByDescription((String)GUACAMOLE_INGREDIENT_COMPONENTS[i][2]).get()); 
//			ingredient.setRecipe(recipe);
//			recipe
//		}
//	}
	
	void createCategories(String[] descriptions) {
		
		Set<Category> categories = new HashSet<>();
		for (int i = 0; i < descriptions.length; i++) {
			Category category = new Category();
			category.setDescription(descriptions[i]);
			categories.add(category);

		}
		categoryRepository.saveAll(categories);		
	}
	
	void createUnitOfMeasures(String[] descriptions) {
		
		Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
		for (int i = 0; i < descriptions.length; i++) {
			UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
			unitOfMeasure.setDescription(descriptions[i]);
			unitOfMeasures.add(unitOfMeasure);

		}
		unitOfMeasureRepository.saveAll(unitOfMeasures);		
	}
	
	

	private Byte[] fetchImageAsByteArray(String location) {

		Byte[] imageByteArray = null;
		try {
			URL url = new URL(location);
			BufferedImage bufferedImage = ImageIO.read(url.openStream());

			// get DataBufferBytes from Raster
			WritableRaster raster = bufferedImage.getRaster();
			DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
			byte[] bytes = data.getData();
			imageByteArray = new Byte[bytes.length];
			int i = 0;
			// Associating Byte array values with bytes. (byte[] to Byte[])
			for (byte b : bytes)
				imageByteArray[i++] = b; // Autoboxing.
			return imageByteArray;

		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
//	private Recipe createGuacamoleRecipe () {
//		Recipe recipe = new Recipe();
//		recipe.setDescription("Perfect Guacamole");
//		recipe.addCategory(categoryRepository.findByDescription(MEXICAN).get());
//		recipe.setPrepTime(10);
//		recipe.setCookTime(0);
//		recipe.setDescription(GUACAMOLE_DESC);
//		recipe.setDifficulty(Difficulty.EASY);
//		recipe.setDirections(GUACAMOLE_DIRECTIONS);
//		recipe.setImage(fetchImageAsByteArray(GUACAMOLE_IMAGE_URL));
//		
//		Ingredient ingredient = createIngredient(recipe, new BigDecimal(2), "ripe avocados", unitOfMeasureRepository.findByDescription(EACH).get());
//		recipe.addIngredient(ingredient);
//		
//		ingredient = createIngredient(recipe, new BigDecimal(0.5), "Kosher salt", unitOfMeasureRepository.findByDescription(TEASPOON).get());
//		recipe.addIngredient(ingredient);
//		
//		ingredient = createIngredient(recipe, new BigDecimal(1), "fresh lime juice or lemon juice", unitOfMeasureRepository.findByDescription(TABLESPOON).get());
//		recipe.addIngredient(ingredient);
//		
//		ingredient = createIngredient(recipe, new BigDecimal(0.25), "minced red onion or thinly sliced green onion", unitOfMeasureRepository.findByDescription(CUP).get());
//		recipe.addIngredient(ingredient);
//		
//		ingredient = createIngredient(recipe, new BigDecimal(2), "serrano chiles, stems and seeds removed, minced", unitOfMeasureRepository.findByDescription(EACH).get());
//		recipe.addIngredient(ingredient);
//		
//		ingredient = createIngredient(recipe, new BigDecimal(2), "cilantro (leaves and tender stems), finely chopped", unitOfMeasureRepository.findByDescription(TABLESPOONS).get());
//		recipe.addIngredient(ingredient);
//		
//		ingredient = createIngredient(recipe, new BigDecimal(1), "freshly grated black pepper", unitOfMeasureRepository.findByDescription(DASH).get());
//		recipe.addIngredient(ingredient);
//		
//		ingredient = createIngredient(recipe, new BigDecimal(0.5), "ripe tomato, seeds and pulp removed, chopped", unitOfMeasureRepository.findByDescription(EACH).get());
//		recipe.addIngredient(ingredient);
//		
//		Note note = new Note();
//		note.setRecipe(recipe);
//		note.setRecipeNotes("Garnish with red radishes or jicama. Serve with tortilla chips.");
//		recipe.setNotes(note);
//		
//		recipe.setServings(4);
//		recipe.setSource("Simply Recipes");
//		recipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
//		return recipe;
//	}
//	
//	static <T> void fromArrayToCollection(String[] a, Set<T> c) {
//	    for (String o : a) {
//	    	T t = new T();
//	    	t.setDescription(o);
//	    }
//	        c.add(o); // Correct
//	    }
//	}
//
}
