package com.pranaysahu.expensetracker.service;

import com.pranaysahu.expensetracker.dto.UserDTO;
import com.pranaysahu.expensetracker.entity.User;
import com.pranaysahu.expensetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private  final ModelMapper modelMapper;

    public void save(UserDTO userDTO){
        User user = mapToEntity(userDTO);
        user.setUserId(UUID.randomUUID().toString());
        userRepository.save(user);
    }

    private User mapToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}