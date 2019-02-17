package com.diligentgroup.recipes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ImageController {
	
	
	@GetMapping("/recipe/{id}/image")
	public String getRecipeImage() {
		return "/recipe/imageuploadform";
	}
	
	@PostMapping("/recipe/{id}/image")
	public String postRecipeImage(@PathVariable String id, Model model) {
		return "/recipe/show";
	}

}
