package com.example.elmserver.controllers.oldcontrollers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "test")
public class TestController {
    @GetMapping("/")
    public String helloworld(){
        var str="helloWorld!";
        System.out.println(str);
        return str;
    }
}
