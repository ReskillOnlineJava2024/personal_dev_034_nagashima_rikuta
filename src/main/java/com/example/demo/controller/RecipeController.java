package com.example.demo.controller;//kou

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.RecipeEntity;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.RecipeRepository;

@Controller
public class RecipeController {

	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	// 一覧表示
	@GetMapping("/recipes")
	public String showAll(
			@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			Model model) {
		
		List<RecipeEntity> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		List<RecipeEntity> recipeList = null;
		
		if (keyword.length() == 0&&categoryId == null) {
			recipeList = recipeRepository.findAll();
			
		} else if(keyword.length() != 0&&categoryId == null) {
			recipeList = recipeRepository. findByRecipeNameContaining(keyword);
			
		} else if(keyword.length() == 0&&categoryId != null) {
			recipeList = recipeRepository.findByCategoryId(categoryId);
		}

		model.addAttribute("keyword", keyword);
		model.addAttribute("recipes", recipeList);

		return "recipeList";
	}

	// 新規登録画面表示
	@GetMapping("/recipes/new")
	public String createRecipes() {
		return "recipeRegistration";
	}

	// 新規登録処理
	@PostMapping("/recipes/add")
	public String add(
			@RequestParam("categoryId") Integer categoryId,
			@RequestParam("recipeName") String recipeName,
			@RequestParam("materials") String materials,
			@RequestParam("contents") String contents) {

		RecipeEntity recipe = new RecipeEntity(categoryId, recipeName,materials,contents);
		recipeRepository.save(recipe);

		return "redirect:/recipes";
	}

	// 更新画面表示
	@GetMapping("/recipes/{id}/edit")
	public String edit(
			@PathVariable("id") Integer id,
			Model model) {

		RecipeEntity recipe = recipeRepository.findById(id).get();
		model.addAttribute("recipe", recipe);

		return "recipeUpdate";
	}
	
	@GetMapping("/recipes/{id}/all")
	public String showAll(
			@PathVariable("id") Integer id, 
			Model model) {

		RecipeEntity recipe = recipeRepository.findById(id).get();
		model.addAttribute("recipe", recipe);
		return "recipeData";
	}
	
	@PostMapping("/recipes/random")
	public String rundam(
			@PathVariable("id") Integer id, 
			Model model) {
		
		List<RecipeEntity> recipeList = null;
		recipeList = recipeRepository.findAll();
		//recipelistのレングスを最大値に設定してランダム生成する。.length()
//		Random rand = new Random();
//		int dice = rand.nextInt(6) + 1;
		Random rand = new Random();
		int dice = rand.nextInt(recipeList.size());
		id = dice;
	
		RecipeEntity recipe = recipeRepository.findById(id).get();
		model.addAttribute("recipe", recipe);

		return "recipeData";
	}


	// 更新処理
	@PostMapping("/recipes/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam("categoryId") Integer categoryId,
			@RequestParam("recipeName") String recipename,
			@RequestParam("materials") String materials,
			@RequestParam("contents") String contents) {

		RecipeEntity recipe = new RecipeEntity(id,categoryId,recipename,materials,contents);
		recipeRepository.save(recipe);
	
		return "redirect:/recipes";
	}
	
	// 削除処理
	@PostMapping("/recipes/{id}/delete")
	public String delete(@PathVariable("id") Integer id) {
		recipeRepository.deleteById(id);
		return "redirect:/recipes";
	}

}
