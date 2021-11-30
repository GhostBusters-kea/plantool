package com.example.plantool.services;

import com.example.plantool.model.User;
import com.example.plantool.repository.UserRepo;

import java.sql.SQLException;

public class UserService {
    UserRepo userRepo = new UserRepo();

    // new instance of user
    public void createUser(String name,String email, String password){
        User user = new User(name,email,password);


        if (isEmailValid(email) == false){
            System.out.println("Email not valid");
        }

        else if (doesEmailExist(email) == false){
            userRepo.insertUserToDB(user);
        }
        else {
            System.out.println("Emailen already exists");
        }

    }

    // checks if the email is already registered
    public boolean doesEmailExist(String email){

        for(int i = 0; i < userRepo.fetchAllUser().size(); i++){
            if(userRepo.fetchAllUser().get(i).getEmail().equals(email)){
                return true;
            }
        }

        return false;
    }

    // email validation
    public boolean isEmailValid(String email){
        if(email.contains(".") && email.contains("@")){
            return true;
        } else
            return false;
    }

    // checks password
    public boolean isPassValid(User user, String userInput){
        boolean passIsValid = false;

        if(userInput.equals(user.getPassword())){
            passIsValid = true;
        }

        return passIsValid;
    }

    // User login
    public boolean login(String email, String password) throws SQLException {
        boolean validLogin = false;

        if(doesEmailExist(email)){
            if(isPassValid(userRepo.fetchUser(email), password)){
                validLogin = true;
            }
            else{

            }
        }

        else{

        }
        return validLogin;
    }

    public void isLeaderBoolean(boolean isLeader, int userId){
        try {
            userRepo.isLeaderBoolean(isLeader, userId);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
