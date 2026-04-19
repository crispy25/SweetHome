package com.sweethome.backend.controller;

import com.sweethome.backend.enums.FeedbackSortByType;
import com.sweethome.backend.service.FeedbackService;
import com.sweethome.backend.service.dto.FeedbackDto;
import com.sweethome.backend.service.dto.FeedbackResponseDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/feedback")
public class FeedbackController implements SecuredRestController {

    private final FeedbackService feedbackService;

    private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public ResponseEntity<Page<FeedbackResponseDto>> getAll(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "5") int size,
                                                            @RequestParam(name = "sort_by", defaultValue = "CREATED_AT") FeedbackSortByType sortBy,
                                                            @RequestParam(name = "order", defaultValue = "DESC") Sort.Direction sortDir) {

        logger.info("Request to get feedbacks");

        Page<FeedbackResponseDto> responseDtoPage = feedbackService.getAll(page, size, sortBy, sortDir);

        logger.info("Successfully retrieved feedbacks");
        return ResponseEntity.ok(responseDtoPage);
    }


    @PostMapping
    public ResponseEntity<FeedbackResponseDto> createFeedback(@RequestBody @Valid FeedbackDto feedback) {
        logger.info("Request to save feedback");

        FeedbackResponseDto saved = feedbackService.save(feedback);

        logger.info("Successfully saved feedback");
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
