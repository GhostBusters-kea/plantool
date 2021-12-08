package com.example.plantool.services;

import com.example.plantool.model.Member;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class isPassValidTest {

    @Test
    void isPassValid() {

        //Arrange
        MemberService service = new MemberService();
        String password = "666";
        String wrongPassword = "123";

        Member tmp = new Member("jesper", "jesper@mail.com", password);
        //Act

        boolean isValid = service.passwordValidation(tmp, password);
        boolean isValidWrong = service.passwordValidation(tmp, wrongPassword);

        //Assert

        assertEquals(true, isValid);
        assertEquals(false, isValidWrong);
    }
}