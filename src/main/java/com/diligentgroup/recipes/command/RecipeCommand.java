package com.diligentgroup.recipes.command;

import java.util.HashSet;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class RecipeCommand extends DescribedCommand {

	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions;
	private DifficultyCommand difficulty;
	@Builder.Default
	private Set<IngredientCommand> ingredients = new HashSet<>();
	private NoteCommand notes;
	@Builder.Default
	private Set<CategoryCommand> categories = new HashSet<>();

	public void addIngredient(IngredientCommand ingredient) {
		ingredients.add(ingredient);
	}

	public void addCategory(CategoryCommand category) {
		getCategories().add(category);
	}
}
