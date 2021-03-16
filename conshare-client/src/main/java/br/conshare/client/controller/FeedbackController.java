package br.conshare.client.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.conshare.client.service.FeedbackService;
import br.conshare.model.entities.Feedback;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackService;

	@PostMapping("/create")
	public String create(Feedback feedbacks, Model model) {
		
		Long id = feedbackService.create(feedbacks);
		
		if(id == 0) {
			return "redirect:/account/register?serverError";
		}
		
		feedbacks.setId(id);
		model.addAttribute("feedback", feedbacks);
		
		return "/collaboration/start";
		
	}
	
	
	@GetMapping("/send_feedback")
	public String getFeedbackPage(Feedback feedbacks) {
		return "/collaboration/send_feedback";
	}
	
	
}
