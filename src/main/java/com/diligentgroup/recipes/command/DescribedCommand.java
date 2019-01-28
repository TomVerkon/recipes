package com.diligentgroup.recipes.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public abstract class DescribedCommand extends IdCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1441436143900720378L;
	
//	public DescribedCommand(Long id, String description) {
//		super(id);
//		this.description = description;
//	}

	private String description;

}
