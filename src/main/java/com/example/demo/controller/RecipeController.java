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
import com.example.demo.model.Account;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.RecipeRepository;

@Controller
public class RecipeController {

	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	Account account;


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
//		model.addAttribute("userName", account.getUserName());
		model.addAttribute("recipes", recipeList);

		return "recipeList";
	}

	// 新規登録画面表示
	@GetMapping("/recipes/new")
	public String createRecipes(
			@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(value = "recipeName", defaultValue = "") String recipeName,
			@RequestParam(value = "materials", defaultValue = "") String materials,
			@RequestParam(value = "contents", defaultValue = "") String contents,
			Model model) {
//		model.addAttribute("categoryId", categoryId);
//		model.addAttribute("recipeName", recipeName);
//		model.addAttribute("materials", materials);
//		model.addAttribute("contents", contents);
		return "recipeRegistration";
	}

	// 新規登録処理
	@PostMapping("/recipes/add")
	public String add(
			@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam("recipeName") String recipeName,
			@RequestParam("materials") String materials,
			@RequestParam("contents") String contents,
			Model model) {
		
		if (categoryId == null || categoryId== 0) {
			model.addAttribute("message", "入力項目をすべて入力してください");
			return "recipeRegistration";
		}
		if (recipeName == null || recipeName.length() == 0) {
			model.addAttribute("message", "入力項目をすべて入力してください");
			return "recipeRegistration";
		}
		if (materials == null || materials.length() == 0) {
			model.addAttribute("message", "入力項目をすべて入力してください");
			return "recipeRegistration";
		}
		if (contents == null || contents.length() == 0) {
			model.addAttribute("message", "入力項目をすべて入力してください");
			return "recipeRegistration";
		}

		RecipeEntity recipe = new RecipeEntity(categoryId,account.getUserName(), recipeName,materials,contents);
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
			Model model) {
		
		List<RecipeEntity> recipeList = null;
		recipeList = recipeRepository.findAll();
		
		Random rand = new Random();
		Integer num = rand.nextInt(recipeList.size())+ 1;
		RecipeEntity recipe = recipeRepository.findById(num).get();
		model.addAttribute("recipe", recipe);
		
		return "recipeData";
	}


	// 更新処理
	@PostMapping("/recipes/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam("recipeName") String recipeName,
			@RequestParam("materials") String materials,
			@RequestParam("contents") String contents,
			Model model) {

		if (categoryId == null || categoryId== 0) {
			model.addAttribute("message", "入力項目をすべて入力してください");
			return "recipeUpdate";
		}
		if (recipeName == null || recipeName.length() == 0) {
			model.addAttribute("message", "入力項目をすべて入力してください");
			return "recipeUpdate";
		}
		if (materials == null || materials.length() == 0) {
			model.addAttribute("message", "入力項目をすべて入力してください");
			return "recipeUpdate";
		}
		if (contents == null || contents.length() == 0) {
			model.addAttribute("message", "入力項目をすべて入力してください");
			return "recipeUpdate";
		}
		
		RecipeEntity recipe = new RecipeEntity(id,categoryId,account.getUserName(),recipeName,materials,contents);
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
