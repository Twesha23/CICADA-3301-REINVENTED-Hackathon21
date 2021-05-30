package com.brocode.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.brocode.model.Area;
import com.brocode.model.City;
import com.brocode.model.State;
import com.brocode.service.AreaService;
import com.brocode.service.CityService;
import com.brocode.service.StateService;

@Controller
@RequestMapping("/city")
public class CityController {

	@Autowired
	CityService cityservice;

	@Autowired
	StateService stateservice;

	@RequestMapping("/cityList")
	public ModelAndView getAllCities() {
		List<City> list = cityservice.getAll();
		ModelAndView model = new ModelAndView();
		City city = new City();
		List<State> stateList = stateservice.getAll();
		model.addObject("stateList", stateList);
		model.addObject("city", city);
		model.addObject("edit", false);
		model.addObject("listcity", list);
		model.setViewName("citylist");
		return model;
	}

	@RequestMapping("/saveCity")
	public String saveCity(@ModelAttribute("city") City city) {

		long result1 = cityservice.addCity(city);
		return "redirect:/city/cityList";
	}

	/*@RequestMapping("/addModel")
	public String addModel(Model model) {

		City city = new City();
		List<State> stateList = stateservice.getAll();
		model.addAttribute("stateList", stateList);
		model.addAttribute("city", city);
		model.addAttribute("edit", false);
		return "addcity";

	}*/

	@RequestMapping("/deleteCity/{id}")
	public String deleteCity(@PathVariable long id) {
		boolean result = cityservice.deleteCity(id);
		return "redirect:/city/cityList";
	}

	@RequestMapping("/editCity/{id}")
	public ModelAndView editCity(@PathVariable long id) {
		List<City> list = cityservice.getAll();
		ModelAndView model = new ModelAndView();
		City city = cityservice.editCity(id);
		List<State> stateList = stateservice.getAll();
		model.addObject("stateList", stateList);
		model.addObject("city", city);
		model.addObject("edit", true);
		model.addObject("listcity", list);
		model.setViewName("citylist");
		return model;
	}

}