package com.diligentgroup.recipes.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
//@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class DescribedEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3912920655242117093L;

	@Column(name = "description")
	private String description;

	public DescribedEntity(Long id, String description) {
		super(id);
		this.description = description;
	}

}
