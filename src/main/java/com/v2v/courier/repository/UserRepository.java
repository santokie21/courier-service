package com.v2v.courier.repository;

import com.v2v.courier.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

  Optional<User> findByUsername(String username);
}
