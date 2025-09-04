package com.example.TripAgora.user.service;

import com.example.TripAgora.tag.repository.TagRepository;
import com.example.TripAgora.tag.repository.UserTagRepository;
import com.example.TripAgora.user.dto.NicknameResponse;
import com.example.TripAgora.user.entity.User;
import com.example.TripAgora.user.exception.DuplicateNicknameException;
import com.example.TripAgora.user.exception.UserNotFoundException;
import com.example.TripAgora.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final UserTagRepository userTagRepository;

    @Transactional
    public NicknameResponse updateNickname(long userId, String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new DuplicateNicknameException();
        }

        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.updateNickname(nickname);

        return new NicknameResponse(user.getNickname());
    }
}