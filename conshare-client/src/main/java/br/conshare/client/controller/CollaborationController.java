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
@RequestMapping("/collaboration")
public class CollaborationController {
	
	
	
	@GetMapping("/start")
	public String getStartCollaboration() {
		return "/collaboration/start";
	}

	@GetMapping("/frequenty_question")
	public String getFrequentyQuestionPage() {
		return "/collaboration/frequenty_question";

	}
	
	@GetMapping("/recomended_doc")
	public String getRecomendedDocPage() {
		return "/collaboration/recomended_doc";

	}
	
	
	@GetMapping("/online_teachers")
	public String getOnlineTeachersPage() {
		return "/collaboration/online_teachers";
	}
	
	@GetMapping("/best_rate_question")
	public String getRatedQuestionPage() {
		return "/collaboration/best_rate_question";
	}
	
	@GetMapping("/bug_report")
	public String getReportBugPage() {
		return "/collaboration/bug_report";
	}	
	
	@GetMapping("/chat")
	public String getOpenChat() {
		return "/collaboration/chat";
	}
	
	@GetMapping("/chatopen")
	public String getClosedChat() {
		return "/collaboration/chatopen";
	}
	
	@GetMapping("/construction")
	public String getErro() {
		return "/collaboration/construction";
	}


}
