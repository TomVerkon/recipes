package com.diligentgroup.recipes.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public abstract class DescribedCommand extends IdCommand {
	
	private String description;

}
