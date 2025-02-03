package com.gfctech.project_manager.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.gfctech.project_manager.dto.UserDTO;
import com.gfctech.project_manager.entity.UserEntity;
import com.gfctech.project_manager.repository.UserRepository;

@Service
public class UserService {

    @Autowired  
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> findAll() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    public void insert(UserDTO user) {
        UserEntity userEntity = new UserEntity(user);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userEntity);
    }

    public void insertNewUser(UserDTO user) {
        UserEntity userEntity = new UserEntity(user);
        userEntity.setId(null);
        userRepository.save(userEntity);
    }

    public UserDTO update(UserDTO user) {
        UserEntity userEntity = new UserEntity(user);
        return new UserDTO(userRepository.save(userEntity));
    }

    public void delete(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        userRepository.delete(user);
    }

    public UserDTO findById(Long id) {
        return new UserDTO(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id)));
    }
}
