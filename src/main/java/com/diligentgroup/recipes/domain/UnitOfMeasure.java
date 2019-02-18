package com.diligentgroup.recipes.domain;

import org.springframework.data.mongodb.core.mapping.Document;

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
@Document
public class UnitOfMeasure extends DescribedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8606785575499021363L;

	public UnitOfMeasure(String id, String description) {
		super(id, description);
	}

}
