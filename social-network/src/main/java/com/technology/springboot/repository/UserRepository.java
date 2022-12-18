package com.technology.springboot.repository;

import com.technology.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findUserByName(String name);

  Optional<User> findUserById(Long signedInUserId);

  List<User> findSuggestedFriendsByIdNot(Long signedInUserId);

  boolean existsUserByName(String name);
}
