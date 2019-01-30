package com.diligentgroup.recipes.command;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class UnitOfMeasureCommand extends DescribedCommand {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8060474858624093946L;
	
	public UnitOfMeasureCommand(Long id, String description) {
		super(id, description);
	}
	

}
