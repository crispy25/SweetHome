package com.sweethome.backend.repository;

import com.sweethome.backend.entity.Favorite;
import com.sweethome.backend.entity.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
    List<Favorite> findByUserId(UUID userId);
}
