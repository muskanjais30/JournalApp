package com.springtutorial.journalApp.service;

import com.springtutorial.journalApp.entity.User;
import com.springtutorial.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired UserService userService;
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
        assertTrue(userService.saveNewUser(user));
    }
//    @Disabled
//    @Test
//    public void testFindByUsername(){
//        assertNotNull(userRepository.findByUsername("Muskan"),"Failed for: Muskan");
//    }
//    @ParameterizedTest
//    @CsvSource({
//            "1,1,2",
//            "4,2,2",
//            "2,10,12"
//    })
//    public void test(int a, int b, int expected){
//        assertEquals(expected,a+b);
//    }
}
