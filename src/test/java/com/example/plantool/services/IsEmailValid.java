package com.example.plantool.services;

import com.example.plantool.model.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsEmailValid {

    @Test
    void isEmailValid() {

        //Arrange
        MemberService service = new MemberService();
        String email = "peter@mail.com";
        String wrongEmail = "peter.com";


        //Act
        boolean isEmailValid = service.emailValidation(email);
        boolean isEmailNotValid = service.emailValidation(wrongEmail);

        //Assert

        assertEquals(true, isEmailValid);
        assertEquals(false, isEmailNotValid);


    }
}