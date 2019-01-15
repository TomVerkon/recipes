package com.diligentgroup.recipes.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diligentgroup.recipes.domain.Category;
import com.diligentgroup.recipes.domain.UnitOfMeasure;
import com.diligentgroup.recipes.repositories.CategoryRepository;
import com.diligentgroup.recipes.repositories.UnitOfMeasureRepository;

@Controller
public class RootController {
	
	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository uomRepository;
	
	
	
	public RootController(CategoryRepository categoryRepository, UnitOfMeasureRepository uomRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.uomRepository = uomRepository;
	}



	@RequestMapping({"", "/", "index", "/index.html"})
	public String getIndexPage() {
		
		Optional<Category> optionalCategory = this.categoryRepository.findByDescription("Fast Food");
		if (optionalCategory.isPresent())
			System.out.println("Fast Food id: " + optionalCategory.get().getId());
		else
			System.out.println("An Id for Fast Food was not found!");
		
		optionalCategory = this.categoryRepository.findByDescription("Ameracan");
		if (optionalCategory.isPresent())
			System.out.println("Ameracan id: " + optionalCategory.get().getId());
		else
			System.out.println("An Id for Ameracan was not found!");
		
		Optional<UnitOfMeasure> optionalUnitOfMeasure = this.uomRepository.findByDescription("Pinch");
		if (optionalUnitOfMeasure.isPresent())
			System.out.println("Pinch id: " + optionalUnitOfMeasure.get().getId());
		else
			System.out.println("An Id for Pinch was not found!");
		
		return "index";
	}
}
