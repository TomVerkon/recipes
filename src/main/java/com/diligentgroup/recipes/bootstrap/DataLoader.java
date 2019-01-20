package com.diligentgroup.recipes.bootstrap;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;

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

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	private final static String PINT = "Pint";

//	private final static String[] categoryDescriptions = {AMERICAN, ITALIAN, FAST_FOOD, MEXICAN};
//	private final static String[] uomDescriptions = {TEASPOON, TEASPOONS, TABLESPOON, TABLESPOONS, CUP, CUPS, 
//			PINCH, PINCHES, OUNCE, OUNCES, EACH, DASH, PINT};

	private CategoryRepository categoryRepository;
	private RecipeRepository recipeRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;

	public DataLoader(CategoryRepository categoryRepository,
			RecipeRepository recipeRepository,
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

	private Ingredient createIngredient(Object[] ingredientComponent) {
		Ingredient ingredient = new Ingredient((String) ingredientComponent[1],
				(BigDecimal) ingredientComponent[0],
				unitOfMeasureRepository
						.findByDescription((String) ingredientComponent[2])
						.get());
		return ingredient;
	}

	private Recipe buildGuacamoleRecipe() {

		final String DESC = "Perfect Guacamole";
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
				{ new BigDecimal(2), "ripe avocados", EACH },
				{ new BigDecimal(0.5), "Kosher salt", TEASPOON },
				{ new BigDecimal(1), "fresh lime juice or lemon juice",
						TABLESPOON },
				{ new BigDecimal(0.25),
						"minced red onion or thinly sliced green onion", CUP },
				{ new BigDecimal(2),
						"serrano chiles, stems and seeds removed, minced",
						EACH },
				{ new BigDecimal(2),
						"cilantro (leaves and tender stems), finely chopped",
						TABLESPOONS },
				{ new BigDecimal(1), "freshly grated black pepper", DASH },
				{ new BigDecimal(0.5),
						"ripe tomato, seeds and pulp removed, chopped",
						EACH } };
		final String NOTE = "Garnish with red radishes or jicama. Serve with tortilla chips.";
		final Integer SERVINGS = 4;
		final String SOURCE = "Simply Recipes";
		final String URL = "https://www.simplyrecipes.com/recipes/perfect_guacamole/";

		return buildRecipe(MEXICAN, 10, 0, DESC, Difficulty.EASY, DIRECTIONS,
				IMAGE_URL, INGREDIENT_COMPONENTS, NOTE, SERVINGS, SOURCE, URL);
	}

	private Recipe buildChickenRecipe() {

		final String DESC = "Spicy Grilled Chicken Tacos";
		final String DIRECTIONS = "1 Prepare a gas or charcoal grill for medium-high, direct heat.\r\n"
				+ "\r\n"
				+ "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\r\n"
				+ "\r\n"
				+ "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\r\n"
				+ "\r\n"
				+ "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\r\n"
				+ "\r\n"
				+ "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\r\n"
				+ "\r\n"
				+ "Wrap warmed tortillas in a tea towel to keep them warm until serving.\r\n"
				+ "\r\n"
				+ "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.";
		final String IMAGE_URL = "https://www.simplyrecipes.com/wp-content/uploads/2017/05/2017-05-29-GrilledChickenTacos-3.jpg";
		final Object[][] INGREDIENT_COMPONENTS = {
				{ new BigDecimal(2), "ancho chili powder", TABLESPOONS },
				{ new BigDecimal(1), "dried oregano", TEASPOON },
				{ new BigDecimal(1), "dried cumin", TEASPOON },
				{ new BigDecimal(1), "sugar", TEASPOON },
				{ new BigDecimal(0.5), "salt", TEASPOON },
				{ new BigDecimal(1), "garlic clove, finely chopped", EACH },
				{ new BigDecimal(1), "finely grated orange zest", TABLESPOON },
				{ new BigDecimal(3), "fresh-squeezed orange juice",
						TABLESPOONS },
				{ new BigDecimal(2), "olive oil", TABLESPOONS },
				{ new BigDecimal(6),
						"skinless, boneless chicken thighs (1 1/4 pounds)",
						EACH },
				{ new BigDecimal(8), "small corn tortillas", EACH },
				{ new BigDecimal(3), "packed baby arugula (3 ounces)", CUPS },
				{ new BigDecimal(2), "medium ripe avocados, sliced", EACH },
				{ new BigDecimal(4), "radishes, thinly sliced", EACH },
				{ new BigDecimal(0.5), "pint cherry tomatoes, halved", PINT },
				{ new BigDecimal(0.25), "red onion, thinly sliced", EACH },
				{ new BigDecimal(0.5), "Roughly chopped cilantro", CUP },
				{ new BigDecimal(0.5), "sour cream thinned with 1/4 cup milk",
						CUP },
				{ new BigDecimal(1), "lime, cut into wedges", EACH } };
		final String NOTE = "Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)";
		final Integer SERVINGS = 6;
		final String SOURCE = "Simply Recipes";
		final String URL = "https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/";

		return buildRecipe(MEXICAN, 20, 15, DESC, Difficulty.EASY, DIRECTIONS,
				IMAGE_URL, INGREDIENT_COMPONENTS, NOTE, SERVINGS, SOURCE, URL);
	}

	private Recipe buildRecipe(String category, Integer prepTime,
			Integer cookTime, String description, Difficulty difficulty,
			String directions, String imageUrl, Object[][] ingredients,
			String note, Integer servings, String source, String url) {
		Recipe recipe = new Recipe();
		recipe.addCategory(
				categoryRepository.findByDescription(category).get());
		recipe.setPrepTime(prepTime);
		recipe.setCookTime(cookTime);
		recipe.setDescription(description);
		recipe.setDifficulty(difficulty);
		recipe.setDirections(directions);
		recipe.setImage(fetchImageAsByteArray(imageUrl));
		for (Object[] ingredientComponents : ingredients) {
			recipe.addIngredient(createIngredient(ingredientComponents));
		}
		Note recipeNote = new Note();
		recipeNote.setRecipeNotes(note);
		recipe.setNotes(recipeNote);
		System.out.println(recipeNote);

		recipe.setServings(servings);
		recipe.setSource(source);
		recipe.setUrl(url);
		// System.out.println(recipe);
		return recipe;

	}

	@Transactional
	private void loadData() {
		log.info("Starting to load Data");

		createCategories();
		createUnitOfMeasures();
		Recipe savedGuacaRecipe = recipeRepository.save(buildGuacamoleRecipe());
		System.out.println(savedGuacaRecipe.getNotes());
		recipeRepository.save(buildChickenRecipe());
		log.info("Finished loading Data");

	}

	void createCategories() {

		final String[] categoryDescriptions = { AMERICAN, ITALIAN, FAST_FOOD,
				MEXICAN };
		Set<Category> categories = new HashSet<>();
		Arrays.stream(categoryDescriptions).forEach(description -> {
			Category category = new Category();
			category.setDescription(description);
			categories.add(category);
		});
		// loaded by data.sql for testing
		// categoryRepository.saveAll(categories);
	}

	void createUnitOfMeasures() {

		final String[] uomDescriptions = { TEASPOON, TEASPOONS, TABLESPOON,
				TABLESPOONS, CUP, CUPS, PINCH, PINCHES, OUNCE, OUNCES, EACH,
				DASH, PINT };
		Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
		Arrays.stream(uomDescriptions).forEach(description -> {
			UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
			unitOfMeasure.setDescription(description);
			unitOfMeasures.add(unitOfMeasure);
		});
		// loaded by data.sql for testing
		// unitOfMeasureRepository.saveAll(unitOfMeasures);
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

}
