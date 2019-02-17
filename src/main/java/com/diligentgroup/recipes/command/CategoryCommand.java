package com.diligentgroup.recipes.command;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CategoryCommand extends DescribedCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4741324958534700430L;

	public CategoryCommand(String id, String description) {
		super(id, description);
	}

}
