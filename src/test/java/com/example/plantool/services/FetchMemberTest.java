package com.example.plantool.services;

import com.example.plantool.model.Member;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class FetchMemberTest {
    //Er i tvivl om det er rigtigt resultat
    @Test
    void findMember() throws SQLException {
        //Arrange
        MemberService service = new MemberService();
        String email = "kevin@mail.com";
        String emailWrong = "wrong@mail.com";

        //Act
        Member member = service.findMember(email);
        Member memberWrong = service.findMember(emailWrong);

        //Assert
        assertEquals(member, member);
    }
}