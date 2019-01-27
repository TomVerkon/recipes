package com.diligentgroup.recipes.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipes")
@EqualsAndHashCode(callSuper = true, exclude = { "categories", "ingredients",
		"image" })
@Entity
public class Recipe extends DescribedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7466110349847642814L;

	@Builder
	public Recipe(Long id, String description, Integer prepTime,
			Integer cookTime, Integer servings, String source, String url,
			String directions, Difficulty difficulty) {
		super(id, description);
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.servings = servings;
		this.source = source;
		this.url = url;
		this.directions = directions;
		this.difficulty = difficulty;
	}

	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;

	@Lob
	private String directions;

	@Enumerated(value = EnumType.STRING)
	private Difficulty difficulty;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Ingredient> ingredients = new HashSet<>();

	@Lob
	private Byte[] image;

	// cascade used so that nots are managed by a recipe
	@OneToOne(cascade = CascadeType.ALL)
	private Note notes;

	@ManyToMany
	@JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();

	public void addIngredient(Ingredient ingredient) {
		ingredient.setRecipe(this);
		ingredients.add(ingredient);
	}

	public void setNotes(Note notes) {
		notes.setRecipe(this);
		this.notes = notes;
	}

	public void addCategory(Category category) {
		getCategories().add(category);
	}

}
