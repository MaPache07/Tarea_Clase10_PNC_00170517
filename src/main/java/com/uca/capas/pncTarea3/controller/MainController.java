package com.uca.capas.pncTarea3.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping("/ingresar")
	public ModelAndView ingresar() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("commons/index");
		return mav;
	}
	
	@RequestMapping("/validar")
	public ModelAndView validar(@RequestParam String names, @RequestParam String lastnames, @RequestParam String birthdate, 
		@RequestParam String place, @RequestParam String school, @RequestParam String phonef, @RequestParam String phonem) throws ParseException {
		List<String> errors = new ArrayList<>();
		ModelAndView result = new ModelAndView();
		
		if(names.length() > 25) {
			errors.add("Los nombres ingresados no pueden superar los 25 caracteres.");
		}
		else if(names.length() < 1){
			errors.add("No puede dejar el campo 'Nombres' vacio.");
		};
		
		if(lastnames.length() > 25) {
			errors.add("Los apellidos ingresados no pueden superar los 25 caracteres.");
		}
		else if(lastnames.length() < 1) {
			errors.add("No puede dejar el campo 'Apellidos' vacio.");
		};
		
		Date dBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
		Date minBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse("2003-01-01");
		if(dBirthDate.compareTo(minBirthDate) < 0) {
			errors.add("La fecha de nacimiento no puede ser menor a la de 2003-01-01.");
		}
		
		if(place.length() > 25) {
			errors.add("El lugar de nacimiento no puede superar los 25 caracteres.");
		}
		else if(place.length() < 1) {
			errors.add("No puede dejar el campo 'Lugar de nacimiento' vacio.");
		};
		
		if(school.length() > 25) {
			errors.add("La escuela de procedencia no puede superar los 25 caracteres.");
		}
		else  if(school.length() < 1) {
			errors.add("No puede dejar el campo 'Colegio de procedencia' vacio.");
		};
		
		if(phonef.length() != 8) {
			errors.add("El numero de telefono fijo que ingreso no es valido.");
		}
		
		if(phonem.length() != 8) {
			errors.add("El numero de telefono movil que ingreso no es valido.");
		}
		
		if(errors.size() >= 1) {
			result.setViewName("commons/errorList");
			result.addObject("errors", errors);
		}
		else {
			result.setViewName("commons/successful");
		}
		return result;
	}
}
