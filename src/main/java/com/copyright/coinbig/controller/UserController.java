package com.copyright.coinbig.controller;

import com.copyright.coinbig.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/orderFee")
    public String orderFee() {
        userService.orderFee();
        return "orderFee";
    }

    @RequestMapping("/userInfo")
    public String userInfo() {
        userService.userInfo();
        return "userInfo";
    }

}
