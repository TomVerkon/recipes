package com.diligentgroup.recipes.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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
@Table(name = "categories")
@EqualsAndHashCode(callSuper = true, exclude = { "recipes" })
@Entity
public class Category extends DescribedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6593873604164076107L;

	@Builder
	public Category(Long id, String description) {
		super(id, description);
	}

	@ManyToMany(mappedBy = "categories")
	private Set<Recipe> recipes;

}
