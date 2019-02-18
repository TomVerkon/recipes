package com.diligentgroup.recipes.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 
 * @author tverk
 *
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true, exclude = { "categories", "ingredients", "image" })
@Document
public class Recipe extends DescribedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7466110349847642814L;

	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions;
	private Difficulty difficulty;

	@Builder.Default
	private Set<Ingredient> ingredients = new HashSet<>();
	private Byte[] image;
	private Note notes;

	@Builder.Default
	@DBRef
	private Set<Category> categories = new HashSet<>();

	public Recipe(String id, String description, Integer prepTime, Integer cookTime, Integer servings, String source,
			String url, String directions, Difficulty difficulty) {
		super(id, description);
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.servings = servings;
		this.source = source;
		this.url = url;
		this.directions = directions;
		this.difficulty = difficulty;
	}

	public Set<Ingredient> getIngredients() {
		if (this.ingredients == null) {
			this.ingredients = new HashSet<>();
		}
		return this.ingredients;
	}

	public void addIngredient(Ingredient ingredient) {
		getIngredients().add(ingredient);
	}

	public void setNotes(Note notes) {
		this.notes = notes;
	}

	public Set<Category> getCategories() {
		if (this.categories == null) {
			this.categories = new HashSet<>();
		}
		return this.categories;
	}

	public void addCategory(Category category) {
		category.getRecipes().add(this);
		getCategories().add(category);
	}

}
