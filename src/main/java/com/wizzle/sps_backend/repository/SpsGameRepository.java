package com.wizzle.sps_backend.repository;

import com.wizzle.sps_backend.model.SpsGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpsGameRepository extends JpaRepository<SpsGame, Long> {}
