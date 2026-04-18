package com.sweethome.backend.service.mapper;

import com.sweethome.backend.entity.FeedbackOption;
import com.sweethome.backend.service.dto.FeedbackOptionResponseDto;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class FeedbackOptionMapper {

    public abstract FeedbackOptionResponseDto toDto(@NotNull FeedbackOption feedbackOption);
}
