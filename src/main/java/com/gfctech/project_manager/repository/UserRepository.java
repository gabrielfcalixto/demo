package com.gfctech.project_manager.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gfctech.project_manager.entity.UserEntity;

public interface UserRepository  extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByLogin(String login);
}
