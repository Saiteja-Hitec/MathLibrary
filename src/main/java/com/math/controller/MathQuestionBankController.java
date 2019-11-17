/**
 * 
 */
package com.math.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.math.entity.Category;
import com.math.entity.MathQuery;
import com.math.service.MathQueryService;

/**
 * @author Ajay J
 *
 * 
 */
@Controller
public class MathQuestionBankController {
	
	@Autowired
	private MathQueryService service;

	@RequestMapping(value = { "/", "/hello" })
	public ModelAndView helloworld() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("message", "Hello World");
		return mv;
	}

	
	@ResponseBody
	@RequestMapping(value = "/getMathQueries")
	public ModelAndView getQueries() {
		List<MathQuery> mathQueries;
		mathQueries = service.getAllQueries();
		List<Category> ctgList= service.getAllCategories();
		ModelAndView mv = new ModelAndView();
		if(mathQueries.size() == 0) {
			mv.addObject("notFound", "No Queries Found!");
		}
		mv.addObject("mathList", mathQueries);
		mv.addObject("categoryList", ctgList);
		mv.addObject("ActiveCategory", "ALL");
		mv.setViewName("viewList");
		return mv;
	}
	
	@PostMapping(value = "/getMathQueries")
	public String getMathQueries(@RequestParam(defaultValue = "-1", required = false) int categoryId, ModelMap model) {
		System.out.println("Category Id:"+categoryId);
		List<Category> categories;
		categories = service.getAllCategories();
		if(categoryId == -1) {
			List<MathQuery> mathQueryList;
			mathQueryList = service.getAllQueries();
			if(mathQueryList.size() == 0) {
				model.put("notFound", "No Queries Found!");
			}
			model.put("mathList", mathQueryList);
			model.put("categoryList", categories);
			model.put("ActiveCategory", "ALL");
			return "viewList";
		} else {
			List<MathQuery> mathQueryList;
			Category ctg = service.getCategoryById(categoryId);
			mathQueryList = service.getQueriesByCtgId(categoryId);
			if(mathQueryList.size() == 0) {
				model.put("notFound", "No Queries Found!");
			}
			model.put("mathList", mathQueryList);
			model.put("categoryList", categories);
			model.put("ActiveCategory", ctg.getCategoryName());
			return "viewList";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/addQuery")
	public ModelAndView getCategories() {
		List<Category> categories;
		categories = service.getAllCategories();
        System.out.println(categories.size());
		ModelAndView mv = new ModelAndView();
		mv.addObject("categoryList", categories);
		mv.setViewName("addMathQuery");
		return mv;
	}
	
	@PostMapping("/postQuery")
	public String postQuery(@RequestParam("queryDesc") String queryDesc, @RequestParam("categoryId") int ctgId, ModelMap model) {
		MathQuery query = new MathQuery();
		System.out.println("Category Id:" +ctgId);
		Category ctg = service.getCategoryById(ctgId);
		query.setQueryDesc(queryDesc.trim());
		query.setCategory(ctg);
		String msg = service.saveMathQuery(query);
		System.out.println(msg);
		return "hello";
		
	}
	
	@RequestMapping("home")
	public String homePage(ModelMap model) {
		return "hello";
	}
	
	@RequestMapping("/addCategory")
	public String addNewCtg(ModelMap model) {
		List<Category> ctglist = service.getAllCategories();
        model.put("categoryList", ctglist);
		return "addNewCategory";
	}
	
	boolean isEmptyString(String string) {
	    return string == null || string.isEmpty();
	}
	
	@RequestMapping("/postCtg")
	public String addNewCatg(@RequestParam("categoryName") String ctgName, ModelMap model) {
		List<Category> ctgList = service.getAllCategories();
		boolean match = false;
		if(!isEmptyString(ctgName)) {
			String ctgname = ctgName.trim();
			for(Category ctg : ctgList) {
				if(ctg.getCategoryName().trim().equalsIgnoreCase(ctgname)) {
					match = true;
				}
			}
			if(!match) {
				System.out.println("entered" +ctgname);
				Category ctg = new Category();
				ctg.setCategoryName(ctgname);
				service.saveCategory(ctg);
				model.put("successmsg", "Category Saved Successfully");
			    ctgList = service.getAllCategories();
				model.put("categoryList", ctgList);
				return "addNewCategory";
			} else {
				model.put("errormsg", "Category Already exists. Please try again");
				model.put("categoryList", ctgList);
				return "addNewCategory";
			}
		}
		return "hello";
	}

}
