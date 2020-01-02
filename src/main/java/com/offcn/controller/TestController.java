package com.offcn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈旺生
 * @create 2019-12-31 10:20
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/show")
    public String show(){
        System.out.println("ok");
        return "show-test";
    }
}
