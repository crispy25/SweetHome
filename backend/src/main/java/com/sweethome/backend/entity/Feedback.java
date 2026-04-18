package com.sweethome.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "feedback", schema = "project")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Size(max = 10)
    @NotNull
    @Column(name = "user_type", nullable = false, length = 10)
    private String userType;

    @Column(name = "message", length = Integer.MAX_VALUE)
    private String message;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "feedback_options_map",
            joinColumns = @JoinColumn(name = "feedback_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id"))
    @BatchSize(size = 50)
    private Set<FeedbackOption> feedbackOptions = new LinkedHashSet<>();

}