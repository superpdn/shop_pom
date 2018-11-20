package com.qf.shop_back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author pdn
 * @Time 2018/11/19 17:50
 * @Version 1.0
 */
@Controller
public class IndexController {

    /**
     * 统一的跳转页面
     * @param page
     * @return
     */
    @RequestMapping(value = "/topage/{page}")
    public String toPage(@PathVariable("page") String page){
        return page;
    }
}
