package com.sweethome.backend.service;

import com.sweethome.backend.constants.FeedbackSortByType;
import com.sweethome.backend.entity.Feedback;
import com.sweethome.backend.entity.User;
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
    private final UserService userService;
    private final FeedbackMapper feedbackMapper;

    public FeedbackService(FeedbackRepository feedbackRepository, FeedbackOptionRepository feedbackOptionRepository, UserService userService, FeedbackMapper feedbackMapper) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackOptionRepository = feedbackOptionRepository;
        this.userService = userService;
        this.feedbackMapper = feedbackMapper;
    }

    public Page<FeedbackResponseDto> getAll(int page, int size, FeedbackSortByType sortBy, Sort.Direction sortDir) {
        final Pageable pageable = PageRequest.of(page, size, Sort.by(sortDir, sortBy.getField()));
        final Page<Feedback> feedbacks = feedbackRepository.findAll(pageable);
        return feedbacks.map(feedbackMapper::toDto);
    }

    public FeedbackResponseDto save(FeedbackDto dto) {
        final User user = userService.getCurrentUser();

        final Feedback entity = feedbackMapper.toEntity(dto);
        entity.setUser(user);
        entity.setFeedbackOptions(new LinkedHashSet<>(feedbackOptionRepository.findAllById(dto.getFeedbackOptionIds())));

        final Feedback saved = feedbackRepository.save(entity);
        return feedbackMapper.toDto(saved);
    }
}
