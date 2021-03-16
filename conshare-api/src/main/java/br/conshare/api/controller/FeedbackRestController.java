package br.conshare.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.conshare.api.service.FeedbackService;
import br.conshare.model.entities.Feedback;

@RestController
@RequestMapping("/api/v1/feedback")
@CrossOrigin(origins = "*")
public class FeedbackRestController {
	
	@Autowired
	private FeedbackService feedbackService;
	
	public static Long ID = Long.valueOf(1);
	
	@PostMapping("/create")
	public ResponseEntity<Long> create(@RequestBody Feedback entity) {
		
		//entity.setId(ID);

		Long id = feedbackService.create(entity);

		if (id == 0) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(id);
	}

}
