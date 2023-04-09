package com.wisdom;

import com.wisdom.entity.HotelType;
import com.wisdom.service.HotelTypeService;
import com.wisdom.vo.ReturnResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelTypeService hotelTypeService;


    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    @GetMapping(value = "queryHotelType")
    @ApiOperation("获取酒店类型")
    @ResponseBody
    public ReturnResult<List<HotelType>> queryHotelType(){
        ReturnResult<List<HotelType>> returnResult = new ReturnResult<>();
        HotelType hotelType = new HotelType();
        List<HotelType> hotelTypes = hotelTypeService.queryHotelType(hotelType);
        returnResult.setObj(hotelTypes);
        return returnResult;
    }
}
