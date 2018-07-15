package com.copyright.coinbig.controller;

import com.copyright.coinbig.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @RequestMapping("/trade")
    public String trade(){
        return "trade success";
    }

}
