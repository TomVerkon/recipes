package com.diligentgroup.recipes.domain;

import org.springframework.stereotype.Component;

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
@Component
public class UnitOfMeasure extends DescribedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8606785575499021363L;

	public UnitOfMeasure(String id, String description) {
		super(id, description);
	}

}
