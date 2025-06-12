package com.springboot.autowiki.repository;

import com.springboot.autowiki.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
    Optional<User> findByVerificationToken(String token);
    Optional<User> findByPasswordResetToken(String token);

    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);

    List<User> findAllByRole(String role);

    List<User> findAllByEnabled(boolean enabled);

    List<User> findAllByRoleAndEnabled(String role, boolean enabled);
}