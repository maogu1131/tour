package com.songyang.tour.controller;/**
 * Created by lenovo on 2017/8/30.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 首页controller
 *
 * @author
 * @create 2017-08-30 18:33
 **/
@Controller
public class IndexController {

    @RequestMapping(value = "/",method = {RequestMethod.GET,RequestMethod.POST})
    public String  index(HttpServletRequest request){

        return "index";
    }


    @RequestMapping(value = "/manage")
    public String  managerIndex(HttpServletRequest request){
        return "adminIndex";
    }
}
