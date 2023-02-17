package kr.co.won.simpleboard.user.service.impl;

import kr.co.won.simpleboard.user.domain.UserDomain;
import kr.co.won.simpleboard.user.domain.UserRole;
import kr.co.won.simpleboard.user.domain.UserRoleDomain;
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
        // add user role
        newUser.addRole(UserRoleDomain.of(newUser, UserRole.USER));
        UserDomain savedUser = userPersistence.save(newUser);
        return UserResponseDto.Registered.ofDomain(savedUser);
    }

    @Transactional
    @Override
    public UserResponseDto.Verified verifiedTokenUser(String userEmail, String token) {
        UserDomain findUser = userPersistence.findByEmail(userEmail).orElse(null);
        if (findUser == null) {
            return UserResponseDto.Verified.verified(null, false);
        }
        if (!findUser.tokenVerified(token)) {
            return UserResponseDto.Verified.verified(findUser, false);
        }
        return UserResponseDto.Verified.verified(findUser, true);
    }

    @Override
    public UserResponseDto.Profile userProfileByUserEmail(String userEmail) {
        UserDomain findUser = userPersistence.findByEmail(userEmail).orElseThrow(() -> {
            // TODO find user failed
            throw new IllegalArgumentException();
        });
        return UserResponseDto.Profile.ofDomain(findUser);
    }
}
