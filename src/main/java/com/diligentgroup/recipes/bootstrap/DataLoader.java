package com.diligentgroup.recipes.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
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
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private final CategoryRepository categoryRepository;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private static final String EACH = "Each";
	private static final String TABLESPOON = "Tablespoon";
	private static final String TEASPOON = "Teaspoon";
	private static final String DASH = "Dash";
	private static final String PINT = "Pint";
	private static final String CUP = "Cup";
	private static final String AMERICAN = "American";
	private static final String MEXICAN = "Mexican";

	public DataLoader(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		categoryRepository.deleteAll();
		unitOfMeasureRepository.deleteAll();
		recipeRepository.deleteAll();
		loadCategories();
		loadUom();
		recipeRepository.saveAll(getRecipes());
		log.debug("Loading Bootstrap Data");
	}
	
    private void loadCategories(){
        Category cat1 = new Category();
        cat1.setDescription("American");
        categoryRepository.save(cat1);

        Category cat2 = new Category();
        cat2.setDescription("Italian");
        categoryRepository.save(cat2);

        Category cat3 = new Category();
        cat3.setDescription("Mexican");
        categoryRepository.save(cat3);

        Category cat4 = new Category();
        cat4.setDescription("Fast Food");
        categoryRepository.save(cat4);
    }

    private void loadUom(){
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setDescription("Teaspoon");
        unitOfMeasureRepository.save(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setDescription("Tablespoon");
        unitOfMeasureRepository.save(uom2);

        UnitOfMeasure uom3 = new UnitOfMeasure();
        uom3.setDescription("Cup");
        unitOfMeasureRepository.save(uom3);

        UnitOfMeasure uom4 = new UnitOfMeasure();
        uom4.setDescription("Pinch");
        unitOfMeasureRepository.save(uom4);

        UnitOfMeasure uom5 = new UnitOfMeasure();
        uom5.setDescription("Ounce");
        unitOfMeasureRepository.save(uom5);

        UnitOfMeasure uom6 = new UnitOfMeasure();
        uom6.setDescription("Each");
        unitOfMeasureRepository.save(uom6);

        UnitOfMeasure uom7 = new UnitOfMeasure();
        uom7.setDescription("Pint");
        unitOfMeasureRepository.save(uom7);

        UnitOfMeasure uom8 = new UnitOfMeasure();
        uom8.setDescription("Dash");
        unitOfMeasureRepository.save(uom8);
    }

	private List<Recipe> getRecipes() {
		
		//INSERT INTO categories (description) VALUES ('American');
		//INSERT INTO categories (description) VALUES ('Italian');
		//INSERT INTO categories (description) VALUES ('Mexican');
		//INSERT INTO categories (description) VALUES ('Fast Food');
		//INSERT INTO uoms (description) VALUES ('Teaspoon');
		//INSERT INTO uoms (description) VALUES ('Tablespoon');
		//INSERT INTO uoms (description) VALUES ('Cup');
		//INSERT INTO uoms (description) VALUES ('Pinch');
		//INSERT INTO uoms (description) VALUES ('Ounce');
		//INSERT INTO uoms (description) VALUES ('Each');
		//INSERT INTO uoms (description) VALUES ('Dash');
		//INSERT INTO uoms (description) VALUES ('Pint');
		

		List<Recipe> recipes = new ArrayList<>(2);

		// get UOMs
		Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription(EACH);

		if (!eachUomOptional.isPresent()) {
			throw new RuntimeException(UnitOfMeasure.class.getSimpleName() + EACH);
		}

		Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription(TABLESPOON);

		if (!tableSpoonUomOptional.isPresent()) {
			throw new RuntimeException(UnitOfMeasure.class.getSimpleName() + TABLESPOON);
		}

		Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription(TEASPOON);

		if (!teaSpoonUomOptional.isPresent()) {
			throw new RuntimeException(UnitOfMeasure.class.getSimpleName() + TEASPOON);
		}

		Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription(DASH);

		if (!dashUomOptional.isPresent()) {
			throw new RuntimeException(UnitOfMeasure.class.getSimpleName() + DASH);
		}

		Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription(PINT);

		if (!pintUomOptional.isPresent()) {
			throw new RuntimeException(UnitOfMeasure.class.getSimpleName() + PINT);
		}

		Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription(CUP);

		if (!cupsUomOptional.isPresent()) {
			throw new RuntimeException(UnitOfMeasure.class.getSimpleName() + CUP);
		}

		// get optionals
		UnitOfMeasure eachUom = eachUomOptional.get();
		UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
		UnitOfMeasure teapoonUom = tableSpoonUomOptional.get();
		UnitOfMeasure dashUom = dashUomOptional.get();
		UnitOfMeasure pintUom = dashUomOptional.get();
		UnitOfMeasure cupsUom = cupsUomOptional.get();

		// get Categories
		Optional<Category> americanCategoryOptional = categoryRepository.findByDescription(AMERICAN);

		if (!americanCategoryOptional.isPresent()) {
			throw new RuntimeException(Category.class.getSimpleName() + AMERICAN);
		}

		Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription(MEXICAN);

		if (!mexicanCategoryOptional.isPresent()) {
			throw new RuntimeException(Category.class.getSimpleName() + MEXICAN);
		}

		Category americanCategory = americanCategoryOptional.get();
		Category mexicanCategory = mexicanCategoryOptional.get();

		// Yummy Guac
		Recipe guacRecipe = new Recipe();
		guacRecipe.setDescription("Perfect Guacamole");
		guacRecipe.setPrepTime(10);
		guacRecipe.setCookTime(0);
		guacRecipe.setServings(4);
		guacRecipe.setDifficulty(Difficulty.EASY);
		guacRecipe.setSource("Google");
		guacRecipe.setUrl("www.google.com");
		guacRecipe.setDirections(
				"1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon"
						+ "\n"
						+ "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)"
						+ "\n"
						+ "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n"
						+ "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n"
						+ "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n"
						+ "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n"
						+ "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n"
						+ "\n" + "\n"
						+ "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");

		Note guacNotes = new Note();
		guacNotes.setRecipeNotes(
				"For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n"
						+ "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n"
						+ "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n"
						+ "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n"
						+ "\n" + "\n"
						+ "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");

		guacRecipe.setNotes(guacNotes);

		guacRecipe.addIngredient(
				Ingredient.builder().description("ripe avocados").amount(new BigDecimal(2)).uom(eachUom).build());
		guacRecipe.addIngredient(
				Ingredient.builder().description("Kosher salt").amount(new BigDecimal(".5")).uom(teapoonUom).build());
		guacRecipe.addIngredient(Ingredient.builder().description("fresh lime juice or lemon juice")
				.amount(new BigDecimal(2)).uom(tableSpoonUom).build());
		guacRecipe.addIngredient(Ingredient.builder().description("minced red onion or thinly sliced green onion")
				.amount(new BigDecimal(2)).uom(tableSpoonUom).build());
		guacRecipe.addIngredient(Ingredient.builder().description("serrano chiles, stems and seeds removed, minced")
				.amount(new BigDecimal(2)).uom(eachUom).build());
		guacRecipe.addIngredient(
				Ingredient.builder().description("Cilantro").amount(new BigDecimal(2)).uom(tableSpoonUom).build());
		guacRecipe.addIngredient(Ingredient.builder().description("freshly grated black pepper")
				.amount(new BigDecimal(2)).uom(dashUom).build());
		guacRecipe.addIngredient(Ingredient.builder().description("ripe tomato, seeds and pulp removed, chopped")
				.amount(new BigDecimal(".5")).uom(eachUom).build());

		guacRecipe.getCategories().add(americanCategory);
		guacRecipe.getCategories().add(mexicanCategory);

		// add to return list
		recipes.add(guacRecipe);

		// Yummy Tacos
		Recipe tacosRecipe = new Recipe();
		tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
		tacosRecipe.setCookTime(9);
		tacosRecipe.setPrepTime(20);
		tacosRecipe.setServings(6);
		tacosRecipe.setDifficulty(Difficulty.MODERATE);
		tacosRecipe.setSource("Yahoo");
		tacosRecipe.setUrl("www.yahoo.com");
		tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n"
				+ "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n"
				+ "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" + "\n"
				+ "\n"
				+ "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n"
				+ "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n"
				+ "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n"
				+ "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n"
				+ "\n" + "\n"
				+ "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

		Note tacoNotes = new Note();
		tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n"
				+ "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n"
				+ "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n"
				+ "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n"
				+ "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n"
				+ "\n" + "\n"
				+ "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");

		tacosRecipe.setNotes(tacoNotes);

		tacosRecipe.addIngredient(Ingredient.builder().description("Ancho Chili Powder").amount(new BigDecimal(2))
				.uom(tableSpoonUom).build());
		tacosRecipe.addIngredient(
				Ingredient.builder().description("Dried Oregano").amount(new BigDecimal(1)).uom(teapoonUom).build());
		tacosRecipe.addIngredient(
				Ingredient.builder().description("Dried Cumin").amount(new BigDecimal(1)).uom(teapoonUom).build());
		tacosRecipe.addIngredient(
				Ingredient.builder().description("Sugar").amount(new BigDecimal(1)).uom(teapoonUom).build());
		tacosRecipe.addIngredient(
				Ingredient.builder().description("Salt").amount(new BigDecimal(".5")).uom(teapoonUom).build());
		tacosRecipe.addIngredient(Ingredient.builder().description("Clove of Garlic, Choppedr")
				.amount(new BigDecimal(1)).uom(eachUom).build());
		tacosRecipe.addIngredient(Ingredient.builder().description("finely grated orange zestr")
				.amount(new BigDecimal(1)).uom(tableSpoonUom).build());
		tacosRecipe.addIngredient(Ingredient.builder().description("fresh-squeezed orange juice")
				.amount(new BigDecimal(3)).uom(tableSpoonUom).build());
		tacosRecipe.addIngredient(
				Ingredient.builder().description("Olive Oil").amount(new BigDecimal(2)).uom(tableSpoonUom).build());
		tacosRecipe.addIngredient(Ingredient.builder().description("boneless chicken thighs").amount(new BigDecimal(4))
				.uom(tableSpoonUom).build());
		tacosRecipe.addIngredient(Ingredient.builder().description("small corn tortillasr").amount(new BigDecimal(8))
				.uom(eachUom).build());
		tacosRecipe.addIngredient(
				Ingredient.builder().description("packed baby arugula").amount(new BigDecimal(3)).uom(cupsUom).build());
		tacosRecipe.addIngredient(Ingredient.builder().description("medium ripe avocados, sliced")
				.amount(new BigDecimal(2)).uom(eachUom).build());
		tacosRecipe.addIngredient(Ingredient.builder().description("radishes, thinly sliced").amount(new BigDecimal(4))
				.uom(eachUom).build());
		tacosRecipe.addIngredient(Ingredient.builder().description("cherry tomatoes, halved")
				.amount(new BigDecimal(".5")).uom(pintUom).build());
		tacosRecipe.addIngredient(Ingredient.builder().description("red onion, thinly sliced")
				.amount(new BigDecimal(".25")).uom(eachUom).build());
		tacosRecipe.addIngredient(Ingredient.builder().description("Roughly chopped cilantro").amount(new BigDecimal(4))
				.uom(eachUom).build());
		tacosRecipe.addIngredient(Ingredient.builder().description("cup sour cream thinned with 1/4 cup milk")
				.amount(new BigDecimal(4)).uom(cupsUom).build());
		tacosRecipe.addIngredient(Ingredient.builder().description("lime, cut into wedges").amount(new BigDecimal(4))
				.uom(eachUom).build());

		tacosRecipe.getCategories().add(americanCategory);
		tacosRecipe.getCategories().add(mexicanCategory);

		recipes.add(tacosRecipe);
		return recipes;
	}

}