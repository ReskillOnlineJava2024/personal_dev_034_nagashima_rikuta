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
			recipeList = recipeRepository.findAllByOrderByIdAsc();
			
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
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("recipeName", recipeName);
		model.addAttribute("materials", materials);
		model.addAttribute("contents", contents);
		return "recipeRegistration";
	}

	// 新規登録処理
	@PostMapping("/recipes/add")
	public String add(
			@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(value = "recipeName", defaultValue = "") String recipeName,
			@RequestParam(value = "materials", defaultValue = "") String materials,
			@RequestParam(value = "contents", defaultValue = "") String contents,
			Model model) {
		
		if (categoryId == null || categoryId== 0) {
			model.addAttribute("message", "入力項目をすべて入力してください");
			model.addAttribute("recipeName", recipeName); 
			model.addAttribute("materials", materials); 
			model.addAttribute("contents", contents); 
			return "recipeRegistration";
		}
		if (recipeName == null || recipeName.length() == 0) {
			model.addAttribute("message", "入力項目をすべて入力してください");
			model.addAttribute("categoryId", categoryId); 
			model.addAttribute("materials", materials); 
			model.addAttribute("contents", contents); 
			return "recipeRegistration";
		}
		if (materials == null || materials.length() == 0) {
			model.addAttribute("message", "入力項目をすべて入力してください");
			model.addAttribute("categoryId", categoryId); 
			model.addAttribute("recipeName", recipeName);  
			model.addAttribute("contents", contents); 
			return "recipeRegistration";
		}
		if (contents == null || contents.length() == 0) {
			model.addAttribute("message", "入力項目をすべて入力してください");
			model.addAttribute("categoryId", categoryId); 
			model.addAttribute("recipeName", recipeName); 
			model.addAttribute("materials", materials); 
			return "recipeRegistration";
		}
		if (recipeName.length() > 30) {
			model.addAttribute("message", "料理名は30文字以内で入力してください");
			return "recipeRegistration";
		}
		if (materials.length() > 200) {
			model.addAttribute("message", "材料は200文字以内で入力してください");
			return "recipeRegistration";
		}
		if (contents.length() > 500) {
			model.addAttribute("message", "作り方は500文字以内で入力してください");
			return "recipeRegistration";
		}

		RecipeEntity recipe = new RecipeEntity(categoryId,account.getUserName(), recipeName,materials,contents);
		recipeRepository.save(recipe);
		model.addAttribute("recipe", recipe); //追加構文

		return "redirect:/recipes";
	}

	// 更新画面表示
	@GetMapping("/recipes/{id}/edit")
	public String edit(
			@PathVariable("id") Integer id,
			Model model) {
		
		RecipeEntity recipe = recipeRepository.findById(id).get();
		model.addAttribute("recipe", recipe);
		
		if (!(account.getUserName().equals(recipe.getName()))){
			model.addAttribute("message", "作成者以外の編集はできません");
			List<RecipeEntity> recipeList = null;
			recipeList = recipeRepository.findAllByOrderByIdAsc();
			model.addAttribute("recipes", recipeList);
			return "recipeList";
		}
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
	public String random(Model model) {

	    List<RecipeEntity> recipeList = recipeRepository.findAll();

	    Random rand = new Random();
	    RecipeEntity recipe = null;
//	    while (true) {
	        Integer num = rand.nextInt(recipeList.size());
	        recipe = recipeList.get(num);
//	        if (recipe != null) {
//	            break;
//	        }
//	    }

	    model.addAttribute("recipe", recipe);
	    return "recipeData";
	}
	
//	@PostMapping("/recipes/random")
//	public String rundam(
//			Model model) {
//		
//		List<RecipeEntity> recipeList = null;
//		recipeList = recipeRepository.findAll();
//		
//		Random rand = new Random();
//		while(true) {
//			Integer num = rand.nextInt(recipeList.size())+ 1;
//			RecipeEntity recipe = recipeRepository.findById(num).get();
//			if(recipe!=null) {
//				
//				break;
//			}
//		
//		}

//		if(recipe==null) {
//			Random id = new Random();
//			Integer num2 = id.nextInt(5);
//			RecipeEntity recipe2 = recipeRepository.findById(num2).get();
//			model.addAttribute("recipe", recipe2);
//			
//			return "recipeData";
//			
//		}
//		model.addAttribute("recipe", recipe);
//		return "recipeData";
//	}


	// 更新処理
	@PostMapping("/recipes/{id}/edit")
	public String update(
			@PathVariable(value ="id") Integer id,
			@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(value ="recipeName", defaultValue = "") String recipeName,
			@RequestParam(value ="materials", defaultValue = "") String materials,
			@RequestParam(value ="contents", defaultValue = "") String contents,
			Model model) {

		if (categoryId == null || categoryId== 0) {
			model.addAttribute("message", "入力項目をすべて入力してください");
			RecipeEntity recipe = recipeRepository.findById(id).get();
			model.addAttribute("recipe", recipe);
			return "recipeUpdate";
		}
		if (recipeName == null || recipeName.length() == 0) {
			model.addAttribute("message", "入力項目をすべて入力してください");
			RecipeEntity recipe = recipeRepository.findById(id).get();
			model.addAttribute("recipe", recipe);
			return "recipeUpdate";
		}
		if (materials == null || materials.length() == 0) {
			model.addAttribute("message", "入力項目をすべて入力してください");
			RecipeEntity recipe = recipeRepository.findById(id).get();
			model.addAttribute("recipe", recipe);
			return "recipeUpdate";
		}
		if (contents == null || contents.length() == 0) {
			model.addAttribute("message", "入力項目をすべて入力してください");
			RecipeEntity recipe = recipeRepository.findById(id).get();
			model.addAttribute("recipe", recipe);
			return "recipeUpdate";
		}
		if (recipeName.length() > 30) {
			model.addAttribute("message", "料理名は30文字以内で入力してください");
			RecipeEntity recipe = recipeRepository.findById(id).get();
			model.addAttribute("recipe", recipe);
			return "recipeUpdate";
		}
		if (materials.length() > 200) {
			model.addAttribute("message", "材料は200文字以内で入力してください");
			RecipeEntity recipe = recipeRepository.findById(id).get();
			model.addAttribute("recipe", recipe);
			return "recipeUpdate";
		}
		if (contents.length() > 500) {
			model.addAttribute("message", "作り方は500文字以内で入力してください");
			RecipeEntity recipe = recipeRepository.findById(id).get();
			model.addAttribute("recipe", recipe);
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
