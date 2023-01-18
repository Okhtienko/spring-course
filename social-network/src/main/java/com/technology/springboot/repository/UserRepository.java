package com.technology.springboot.repository;

import com.technology.springboot.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findUserByName(String name);

  Optional<User> findUserById(Long signedInUserId);

  Page<User> findSuggestedFriendsByIdNot(Long signedInUserId, Pageable pageable);

  boolean existsUserByName(String name);

}
