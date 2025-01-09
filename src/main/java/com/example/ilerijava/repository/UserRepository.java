package com.example.ilerijava.repository;

import com.example.ilerijava.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}