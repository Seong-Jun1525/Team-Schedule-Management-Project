package com.yuhan.TeamScheduleManagement.repository;

import com.yuhan.TeamScheduleManagement.domain.User;
import com.yuhan.TeamScheduleManagement.domain.User.UserGender;
import com.yuhan.TeamScheduleManagement.domain.User.UserState;
import com.yuhan.TeamScheduleManagement.persistance.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    //@Test
    public void testCreateUser() {
        // 테스트 회원정보 생성
        User user = new User();
        user.setUserId("testUser");
        user.setUserPw("testPassword");
        user.setUserName("Test Name");
        user.setUserEmail("test@example.com");
        user.setUserGender(UserGender.MAN);
        user.setUserJob("프론트엔드 개발자");
        user.setUserState(UserState.ONLINE);

        // DB에 저장
        User savedUser = userRepository.save(user);

        // 결과 출력
        assertNotNull(savedUser);
        assertEquals("testUser", savedUser.getUserId());
        assertEquals("Test Name", savedUser.getUserName());

        System.out.println("Create User Test Passed: " + savedUser);
    }

    //@Test
    public void testReadUser() {
        // 테스트 회원정보 가져오기
        Optional<User> foundUser = userRepository.findById("testUser");

        // 결과 출력
        assertTrue(foundUser.isPresent());
        assertEquals("Test Name", foundUser.get().getUserName());

        System.out.println("Read User Test Passed: " + foundUser.get());
    }

    //@Test
    public void testUpdateUser() {
        // 테스트 회원 정보 가져오기
        Optional<User> foundUser = userRepository.findById("testUser");
        assertTrue(foundUser.isPresent());
        
        // DB에서 업데이트
        foundUser.get().setUserName("Updated Name");
        foundUser.get().setUserEmail("updated@example.com");
        User updatedUser = userRepository.save(foundUser.get());

        // 결과 출력
        assertEquals("Updated Name", updatedUser.getUserName());
        assertEquals("updated@example.com", updatedUser.getUserEmail());

        System.out.println("Update User Test Passed: " + updatedUser);
    }

    //@Test
    public void testDeleteUser() {
        // DB에서 삭제
        userRepository.deleteById("testUser");
        Optional<User> deletedUser = userRepository.findById("testUser");

        // 결과 출력
        assertFalse(deletedUser.isPresent());

        System.out.println("Delete User Test Passed: User with ID 'testUser' was successfully deleted.");
    }
}
