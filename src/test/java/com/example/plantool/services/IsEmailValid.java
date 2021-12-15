package com.example.plantool.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Kevin Funch
 *
 * Test if an email is valid
 */

class IsEmailValid {

    //Test if an email has the valid input to be detected as an email / Successful test
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