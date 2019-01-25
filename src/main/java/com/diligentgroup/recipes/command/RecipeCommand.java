package com.diligentgroup.recipes.command;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
	
	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions;
	private DifficultyCommand difficulty;
	private Set<IngredientCommand> ingredients = new HashSet<>();
	private NoteCommand notes;
	private Set<CategoryCommand> categories = new HashSet<>();
	
}
