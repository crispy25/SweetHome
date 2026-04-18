package com.sweethome.backend.repository;

import com.sweethome.backend.entity.Feedback;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FeedbackRepository extends PagingAndSortingRepository<Feedback, Integer> {
    @NotNull
    Page<Feedback> findAll(@NotNull Pageable pageable);

    @NotNull
    Feedback save(Feedback entity);
}
