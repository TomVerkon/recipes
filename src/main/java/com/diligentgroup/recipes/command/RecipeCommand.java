package com.diligentgroup.recipes.command;

import java.util.HashSet;
import java.util.Set;

import com.diligentgroup.recipes.domain.Difficulty;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 8532296600920179235L;

	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions;
	private Difficulty difficulty;

	@Builder.Default
	private Set<IngredientCommand> ingredients = new HashSet<>();

	private NoteCommand notes;

	@Builder.Default
	private Set<CategoryCommand> categories = new HashSet<>();

	public RecipeCommand(Long id, String description, Integer prepTime, Integer cookTime, Integer servings,
			String source, String url, String directions, Difficulty difficulty) {
		super(id, description);
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.servings = servings;
		this.source = source;
		this.url = url;
		this.directions = directions;
		this.difficulty = difficulty;
	}

	public Set<IngredientCommand> getIngredients() {
		if (this.ingredients == null) {
			this.ingredients = new HashSet<>();
		}
		return this.ingredients;
	}

	public void addIngredient(IngredientCommand ingredient) {
		ingredients.add(ingredient);
	}

	public Set<CategoryCommand> getCategories() {
		if (this.categories == null) {
			this.categories = new HashSet<>();
		}
		return this.categories;
	}

	public void addCategory(CategoryCommand category) {
		getCategories().add(category);
	}
}
