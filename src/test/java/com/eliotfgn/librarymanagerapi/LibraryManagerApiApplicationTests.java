package com.eliotfgn.librarymanagerapi;

import com.eliotfgn.librarymanagerapi.models.Role;
import com.eliotfgn.librarymanagerapi.models.User;
import com.eliotfgn.librarymanagerapi.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.List;

@SpringBootTest
public class LibraryManagerApiApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {

        User user1 = new User(150L, "toto", "toto", "psw", "toto@gmail.com", "00000000", true, Instant.now(), true, null);

        userRepository.save(user1);
    }
}
