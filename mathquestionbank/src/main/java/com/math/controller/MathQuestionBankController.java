/**
 * 
 */
package com.math.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.math.service.IMathQuestionBankService;

/**
 * @author Ajay J
 *
 * 
 */
@Controller
@RequestMapping("/questions")
public class MathQuestionBankController {

	@Autowired
	private IMathQuestionBankService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String intialScreen() {
		return "intial-screen";
	}

	@RequestMapping(value = "/search/category", method = RequestMethod.GET)
	public ModelAndView displayQuestionListByCategoryId(@RequestParam("categoryId") int categoryId,
			@RequestParam("categoryName") String categoryName) {

		System.out.println(categoryId + categoryName);

		ModelAndView mv = new ModelAndView();
		System.out.println(this.service.fetchAllQuestions());
		mv.setViewName("displayquestions");
		mv.addObject("category", this.service.fetchAllCategory());
		mv.addObject("questions", this.service.fetchQuestionsByCategory(categoryId));
		return mv;
	}

	@RequestMapping(value = "/search/all", method = RequestMethod.GET)
	public ModelAndView displayAllQuestionList() {

		ModelAndView mv = new ModelAndView();
		System.out.println(this.service.fetchAllQuestions());
		mv.setViewName("displayquestions");
		mv.addObject("category", this.service.fetchAllCategory());
		mv.addObject("questions", this.service.fetchAllQuestions());
		return mv;
	}

	@RequestMapping(value = "/add-question", method = RequestMethod.GET)
	public ModelAndView addQuestion() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addquestion");
		mv.addObject("category", this.service.fetchAllCategory());
		return mv;
	}

	@RequestMapping(value = "/add-category", method = RequestMethod.GET)
	public ModelAndView addCategory() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addcategory");
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/add-question/validate", method = RequestMethod.GET)
	public String validateQuestion(@RequestParam("question") String question,
			@RequestParam("categoryId") int categoryId, @RequestParam("categoryName") String categoryName,
			@RequestParam("keywords") String keywords) {
		System.out.println(question + categoryId + categoryName);
		try {
			this.service.saveQuestion(question, categoryId, categoryName, keywords);
			return "Saved Successfully";
		} catch (Exception ex) {
			System.out.println(ex);
			return "error";
		}

	}

	@ResponseBody
	@RequestMapping(value = "/add-category/validate", method = RequestMethod.GET)
	public String validatecategory(@RequestParam(name = "category", required = true) String category) {
		System.out.println(category);
		try {
			this.service.saveCategory(category);
			return "Saved Successfully";
		} catch (Exception ex) {
			return "error";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/add-keyword", method = RequestMethod.GET)
	public String addKeyword(@RequestParam(name = "questionId", required = true) int id,
			@RequestParam(name = "keyword", required = true) String keyword) {
		System.out.println(keyword);
		try {
			this.service.updateKeywordByQuesId(id, keyword);
			return "Saved Successfully";
		} catch (Exception ex) {
			return "error";
		}
	}

	@RequestMapping(value = "/search/keywords", method = RequestMethod.GET)
	public ModelAndView searchByKeyword(@RequestParam(name = "keywords", required = true) String[] keywords) {
		System.out.println(" IN CONTROLLER KEWYWORDS:   " + keywords.length + keywords[0] + " ");
		System.out.println("PRINTING---->" + this.service.fetchQuestionsByKeyowrds(keywords));
		ModelAndView mv = new ModelAndView();
		System.out.println(this.service.fetchAllQuestions());
		mv.setViewName("displayquestions");
		mv.addObject("category", this.service.fetchAllCategory());
		mv.addObject("questions", this.service.fetchQuestionsByKeyowrds(keywords));
		return mv;

	}

}
