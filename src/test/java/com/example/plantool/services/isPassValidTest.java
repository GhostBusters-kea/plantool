package com.example.plantool.services;

import com.example.plantool.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class isPassValidTest {

    @Test
    void isPassValid() {

        //Arrange
        UserService service = new UserService();
        String password = "666";
        String wrongPassword = "123";

        User tmp = new User("jesper", "jesper@mail.com", password);
        //Act

        boolean isValid = service.isPassValid(tmp, password);
        boolean isValidWrong = service.isPassValid(tmp, wrongPassword);

        //Assert

        assertEquals(true, isValid);
        assertEquals(false, isValidWrong);
    }
}