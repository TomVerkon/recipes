package com.diligentgroup.recipes.command;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public abstract class DescribedCommand extends IdCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1441436143900720378L;

	private String description;

	public DescribedCommand(String id, String description) {
		super(id);
		this.description = description;
	}

}
