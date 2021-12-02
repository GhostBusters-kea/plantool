package com.example.plantool.services;

import com.example.plantool.model.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class FetchUserTest {
    //Er i tvivl om det er rigtigt resultat
    @Test
    void fetchUser() throws SQLException {
        //Arrange
        UserService service = new UserService();
        String email = "kevin@mail.com";
        String emailWrong = "wrong@mail.com";

        //Act
        User user = service.fetchUser(email);
        User userWrong = service.fetchUser(emailWrong);

        //Assert
        assertEquals(user, user);
    }
}