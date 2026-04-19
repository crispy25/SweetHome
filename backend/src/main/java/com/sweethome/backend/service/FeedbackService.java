package com.sweethome.backend.service;

import com.sweethome.backend.entity.Feedback;
import com.sweethome.backend.entity.User;
import com.sweethome.backend.enums.FeedbackSortByType;
import com.sweethome.backend.repository.FeedbackOptionRepository;
import com.sweethome.backend.repository.FeedbackRepository;
import com.sweethome.backend.service.dto.FeedbackDto;
import com.sweethome.backend.service.dto.FeedbackResponseDto;
import com.sweethome.backend.service.mapper.FeedbackMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;

@Service
@Transactional
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackOptionRepository feedbackOptionRepository;
    private final CurrentUserService currentUserService;
    private final FeedbackMapper feedbackMapper;

    public FeedbackService(FeedbackRepository feedbackRepository, FeedbackOptionRepository feedbackOptionRepository, CurrentUserService currentUserService, FeedbackMapper feedbackMapper) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackOptionRepository = feedbackOptionRepository;
        this.currentUserService = currentUserService;
        this.feedbackMapper = feedbackMapper;
    }

    public Page<FeedbackResponseDto> getAll(int page, int size, FeedbackSortByType sortBy, Sort.Direction sortDir) {
        final Pageable pageable = PageRequest.of(page, size, Sort.by(sortDir, sortBy.getField()));
        final Page<Feedback> feedbacks = feedbackRepository.findAll(pageable);
        return feedbacks.map(feedbackMapper::toDto);
    }

    public FeedbackResponseDto save(FeedbackDto dto) {
        final User user = currentUserService.getCurrentUser();

        final Feedback entity = feedbackMapper.toEntity(dto);
        entity.setUser(user);
        entity.setFeedbackOptions(new LinkedHashSet<>(feedbackOptionRepository.findAllById(dto.getFeedbackOptionIds())));
        final Feedback saved = feedbackRepository.saveAndFlush(entity);

        return feedbackMapper.toDto(saved);
    }
}
