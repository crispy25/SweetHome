package com.sweethome.backend.controller;

import com.sweethome.backend.service.FeedbackOptionService;
import com.sweethome.backend.service.dto.FeedbackOptionResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback-options")
public class FeedbackOptionController implements SecuredRestController {

    private final FeedbackOptionService feedbackOptionService;

    private static final Logger logger = LoggerFactory.getLogger(FeedbackOptionController.class);

    public FeedbackOptionController(FeedbackOptionService feedbackOptionService) {
        this.feedbackOptionService = feedbackOptionService;
    }

    @GetMapping
    public ResponseEntity<List<FeedbackOptionResponseDto>> getAll() {
        logger.info("Request to get feedback options");

        List<FeedbackOptionResponseDto> feedbackOptions = feedbackOptionService.getAll();

        logger.info("Successfully retrieved feedback options");
        return ResponseEntity.ok(feedbackOptions);
    }
}
