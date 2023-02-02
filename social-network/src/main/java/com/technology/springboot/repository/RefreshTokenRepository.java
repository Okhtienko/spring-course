package com.technology.springboot.repository;

import com.technology.springboot.model.RefreshToken;
import com.technology.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

  Optional<RefreshToken> findByToken(String token);

  RefreshToken findByUser(User user);

  boolean existsByUser(User user);

}
