package kr.co.won.simpleboard.user.dto;

import kr.co.won.simpleboard.user.domain.UserDomain;

public class UserResponseDto {

    public record Registered(Long userIdx, String userId, String email, String name) {

        public static Registered ofDomain(UserDomain user) {
            return new Registered(user.getIdx(), user.getUserId(), user.getEmail(), user.getName());
        }
    }

    public record Profile(Long userIdx, String userId, String email, String name) {
        public static Profile ofDomain(UserDomain user) {
            return new Profile(user.getIdx(), user.getUserId(), user.getEmail(), user.getName());
        }
    }

    public record Update(Long userIdx, String userId, String email, String name) {
        public static Update ofDomain(UserDomain user) {
            return new Update(user.getIdx(), user.getUserId(), user.getEmail(), user.getName());
        }
    }

    public record UpdatePassword(Long userIdx, String userId, String email) {
        public static UpdatePassword ofDomain(UserDomain user) {
            return new UpdatePassword(user.getIdx(), user.getUserId(), user.getEmail());
        }
    }

    public record Verified(Long userIdx, String userId, String email, boolean verifiedFlag) {

        public static Verified verified(UserDomain user, boolean flag) {
            return new Verified(user.getIdx(), user.getUserId(), user.getEmail(), flag);
        }

        public static Verified ofDomain(UserDomain user) {
            return new Verified(user.getIdx(), user.getUserId(), user.getEmail(), user.isVerifiedFlag());
        }
    }

}
