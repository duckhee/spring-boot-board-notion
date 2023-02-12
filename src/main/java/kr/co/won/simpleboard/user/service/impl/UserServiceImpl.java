package kr.co.won.simpleboard.user.service.impl;

import kr.co.won.simpleboard.user.domain.UserDomain;
import kr.co.won.simpleboard.user.dto.UserForm;
import kr.co.won.simpleboard.user.dto.UserRegisteredForm;
import kr.co.won.simpleboard.user.dto.UserResponseDto;
import kr.co.won.simpleboard.user.persistence.UserPersistence;
import kr.co.won.simpleboard.user.persistence.UserPersistenceExtension;
import kr.co.won.simpleboard.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserPersistence userPersistence;

    @Transactional
    @Override
    public UserResponseDto.Registered registeredUser(UserRegisteredForm form) {
        UserDomain newUser = UserDomain.of(form.getUserId(), form.getUserEmail(), form.getName(), form.getPassword());
        UserDomain savedUser = userPersistence.save(newUser);
        return UserResponseDto.Registered.ofDomain(savedUser);
    }

    @Transactional
    @Override
    public UserResponseDto.Verified verifiedTokenUser(String userEmail, String token) {
        UserDomain findUser = userPersistence.findByEmail(userEmail).orElseThrow(() -> {
            // TODO custom user exception
            throw new IllegalArgumentException();
        });
        // TODO check throw or not
        if (!findUser.tokenVerified(token)) {
            UserResponseDto.Verified.verified(findUser, false);
        }
        return UserResponseDto.Verified.verified(findUser, true);
    }
}
