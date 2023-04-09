
package com.wisdom.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/test")
public class TestController {




    @RequestMapping(value = "/testJsp", method = RequestMethod.GET)
    public ModelAndView getTest(){

        return new ModelAndView("test");
    }


    @RequestMapping(value = "/printInfo", method = RequestMethod.POST)
    public void printInfo(@RequestBody Map map){

        log.info("輸出:{}", JSON.toJSONString(map));
    }


}

