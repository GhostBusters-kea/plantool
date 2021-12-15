package com.example.plantool.services;

import com.example.plantool.model.Member;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

/**
 * Author: Michael Dyvad
 *
 * Test findmember
 */

import static org.junit.jupiter.api.Assertions.*;

class FetchMemberTest {

    //Test that the findmember method can find a member on email / Successful test
    @Test
    void findMember() throws SQLException {
        //Arrange
        MemberService service = new MemberService();
        String email = "kevin@mail.com";

        //Act
        String memberFindEmail = service.findMember(email).getEmail();

        //Assert
        assertEquals(email, memberFindEmail);
    }
}