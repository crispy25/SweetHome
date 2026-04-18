package com.sweethome.backend.repository;

import com.sweethome.backend.entity.FeedbackOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackOptionRepository extends JpaRepository<FeedbackOption, Integer> {
}
