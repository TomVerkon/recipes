package com.diligentgroup.recipes.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "uoms")
@EqualsAndHashCode(callSuper = true)
@Entity
public class UnitOfMeasure extends DescribedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8606785575499021363L;

	@Builder
	public UnitOfMeasure(Long id, String description) {
		super(id, description);
	}

}
