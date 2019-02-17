package com.diligentgroup.recipes.command;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@SuperBuilder
public abstract class IdCommand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4584230674018206090L;

	private String id;

}
