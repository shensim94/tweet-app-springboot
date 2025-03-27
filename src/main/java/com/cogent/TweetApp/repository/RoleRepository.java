package com.cogent.TweetApp.repository;

import com.cogent.TweetApp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String roleUser);

    boolean existsByName(String roleUser);
}
