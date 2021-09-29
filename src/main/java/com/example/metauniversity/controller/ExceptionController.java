package com.example.metauniversity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.metauniversity.exception.NoSuchBoardException;
import com.example.metauniversity.exception.NoSuchUserException;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(NoSuchUserException.class)
    public String noSuchUser(RedirectAttributes redirectAttributes, Exception e) {
		String[] error = e.getMessage().split("*");
		
    	redirectAttributes.addFlashAttribute("errorMessage", error[0]);
    	
        return "redirect:/" + error[1];
    }

    @ExceptionHandler(NoSuchBoardException.class)
    public String noSuchBoard(RedirectAttributes redirectAttributes, Exception e) {
    	redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
    	
        return "redirect:/boardList";
    }
}
