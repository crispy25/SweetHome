package com.sweethome.backend.service;

import com.sweethome.backend.repository.FeedbackOptionRepository;
import com.sweethome.backend.service.dto.FeedbackOptionResponseDto;
import com.sweethome.backend.service.mapper.FeedbackOptionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FeedbackOptionService {

    private final FeedbackOptionRepository feedbackOptionRepository;
    private final FeedbackOptionMapper feedbackOptionMapper;

    public FeedbackOptionService(FeedbackOptionRepository feedbackOptionRepository, FeedbackOptionMapper feedbackOptionMapper) {
        this.feedbackOptionRepository = feedbackOptionRepository;
        this.feedbackOptionMapper = feedbackOptionMapper;
    }

    public List<FeedbackOptionResponseDto> getAll() {
        return feedbackOptionRepository.findAll().stream().map(feedbackOptionMapper::toDto).toList();
    }
}
