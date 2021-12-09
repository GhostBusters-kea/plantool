package com.example.plantool.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class FragmentsController {

    @GetMapping("/navbar")
    public String getNavbar(){
        return "navbar.html";
    }

}
