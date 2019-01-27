package com.diligentgroup.recipes.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
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
