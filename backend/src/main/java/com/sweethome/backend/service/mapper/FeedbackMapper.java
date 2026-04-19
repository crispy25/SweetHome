package com.sweethome.backend.service.mapper;

import com.sweethome.backend.entity.Feedback;
import com.sweethome.backend.entity.FeedbackOption;
import com.sweethome.backend.service.dto.FeedbackDto;
import com.sweethome.backend.service.dto.FeedbackResponseDto;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class FeedbackMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(target = "feedbackOptions", expression = "java(mapOptionIds(feedback.getFeedbackOptions()))")
    public abstract FeedbackResponseDto toDto(@NotNull Feedback feedback);

    public abstract Feedback toEntity(@NotNull FeedbackDto dto);

    protected Set<Integer> mapOptionIds(Set<FeedbackOption> options) {
        if (options == null)
            return new LinkedHashSet<>();
        return options.stream().map(FeedbackOption::getId).collect(Collectors.toSet());
    }
}
