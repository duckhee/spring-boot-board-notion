package kr.co.won.simpleboard.user.service;

import kr.co.won.simpleboard.user.dto.UserForm;
import kr.co.won.simpleboard.user.dto.UserRegisteredForm;
import kr.co.won.simpleboard.user.dto.UserResponseDto;

public interface UserService {

    /**
     * user registered method
     */
    default UserResponseDto.Registered registeredUser(UserRegisteredForm form) {
        return null;
    }

    /**
     * user verified method
     */
    default UserResponseDto.Verified verifiedTokenUser(String userEmail, String token) {
        return null;
    }

    /**
     * user profile method
     */
    default UserResponseDto.Profile userProfile(Long userIdx) {
        return null;
    }

    /**
     * user profile method by user email
     */
    default UserResponseDto.Profile userProfileByUserId(String userId) {
        return null;
    }

    /**
     * user profile method by user id
     */
    default UserResponseDto.Profile userProfileByUserEmail(String userEmail) {
        return null;
    }

    /**
     * update user profile
     */
    default UserResponseDto.Update updateProfile(Long userIdx, UserForm.Update form) {
        return null;
    }

    /**
     * update user password
     */
    default UserResponseDto.UpdatePassword updatePassword(Long userIdx, UserForm.UpdatePassword form) {
        return null;
    }


}
