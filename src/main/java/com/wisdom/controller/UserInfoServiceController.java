package com.wisdom.controller;

import com.google.common.collect.Lists;
import com.wisdom.entity.HotelInfo;
import com.wisdom.entity.HotelInfoImg;
import com.wisdom.entity.HotelType;
import com.wisdom.entity.UserInfo;
import com.wisdom.service.HotelImgService;
import com.wisdom.service.HotelInfoService;
import com.wisdom.service.HotelTypeService;
import com.wisdom.service.UserInfoService;
import com.wisdom.vo.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/userInfo")
public class UserInfoServiceController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private HotelInfoService hotelInfoService;

    @Autowired
    private HotelTypeService hotelTypeService;

    @Autowired
    private HotelImgService hotelImgService;


    private final String FAIL = "0";
    private final String SUCCESS = "1";

    @GetMapping("login")
    public ModelAndView loginIn() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }


    @PostMapping("userLogin")
    @ResponseBody
    public ModelAndView login(UserInfo userInfo, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        ReturnResult<UserInfo> result = userInfoService.loginInfo(userInfo);
        if (SUCCESS.equals(result.getStatus())) {
            UserInfo infoResult = result.getObj();
            session.setAttribute("userInfo", infoResult);
            modelAndView.addObject("userInfo", infoResult);
            HotelInfo infoParam = new HotelInfo();
            //1管理员 2普通用户 如果是管理员，只显示自己当前下面的酒店
            if (1 == infoResult.getUserType()) {
                infoParam.setUserId(infoResult.getId());
                modelAndView.setViewName("index");
            }else{
                modelAndView.setViewName("indexAjax");
               // return modelAndView;
            }
            List<HotelType> hotelTypes = hotelTypeService.queryHotelType(null);
            modelAndView.addObject("hotelTypes", hotelTypes);
            ReturnResult<List<HotelInfo>> returnResult = hotelInfoService.queryHotelInfoAll(infoParam);
            if (returnResult.getStatus().equals(SUCCESS)) {
                List<HotelInfo> hotelInfoList = Lists.newArrayList();
                for (HotelInfo info : returnResult.getObj()) {
                    HotelInfoImg hotelInfoImg = new HotelInfoImg();
                    hotelInfoImg.setHotelInfoId(info.getId());
                    List<HotelInfoImg> hotelInfoImgs = hotelImgService.queryHotelInfoImg(hotelInfoImg);
                    if (!CollectionUtils.isEmpty(hotelInfoImgs)) {
                        info.setImageUrl(hotelInfoImgs.get(0).getImgUrl());
                    }
                    hotelInfoList.add(info);
                }
                modelAndView.addObject("hotelInfoList", hotelInfoList);
            }
        } else {
            modelAndView.addObject("prompt", result.getMessage());
        }
        return modelAndView;
    }



}
