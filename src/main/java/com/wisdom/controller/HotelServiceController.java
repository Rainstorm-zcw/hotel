package com.wisdom.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wisdom.entity.*;
import com.wisdom.service.HotelImgService;
import com.wisdom.service.HotelInfoService;
import com.wisdom.service.HotelOrderService;
import com.wisdom.service.HotelTypeService;
import com.wisdom.util.ResultUtil;
import com.wisdom.vo.ReturnResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/hotelServer")
public class HotelServiceController {

    @Autowired
    private HotelTypeService hotelTypeService;

    @Autowired
    private HotelInfoService hotelInfoService;

    @Autowired
    private HotelImgService hotelImgService;

    @Autowired
    private HotelOrderService hotelOrderService;

    @GetMapping(value = "queryHotelType")
    @ApiOperation("获取酒店类型")
    @ResponseBody
    public ReturnResult<List<HotelType>> queryHotelType() {
        ReturnResult<List<HotelType>> returnResult = new ReturnResult<>();
        HotelType hotelType = new HotelType();
        List<HotelType> hotelTypes = hotelTypeService.queryHotelType(hotelType);
        returnResult.setObj(hotelTypes);
        return returnResult;
    }

    @GetMapping(value = "queryHotelInfoAll")
    @ApiOperation("获取全部酒店")
    @ResponseBody
    public ReturnResult<List<HotelInfo>> queryHotelInfoAll() {
        ReturnResult<List<HotelInfo>> hotelInfoList = hotelInfoService.queryHotelInfoAll(null);
        return hotelInfoList;
    }

    @PostMapping(value = "delHotelInfo")
    @ApiOperation("删除酒店")
    @ResponseBody
    public ReturnResult delHotelInfo(Integer id) {
        ReturnResult returnResult = new ReturnResult();
        returnResult.setStatus(hotelInfoService.delHotelInfo(id).toString());
        returnResult.setMessage("删除成功");
        HotelInfoImg hotelInfoImg = new HotelInfoImg();
        hotelInfoImg.setHotelInfoId(id);
        List<HotelInfoImg> hotelInfoImgs = hotelImgService.queryHotelInfoImg(hotelInfoImg);
        log.info("刪除酒店图片信息:{}", JSON.toJSONString(hotelInfoImgs));
        for (HotelInfoImg infoImg : hotelInfoImgs) {
            hotelImgService.delHotelInfoImg(infoImg.getId());
        }
        return returnResult;
    }

    @PostMapping(value = "saveHotelInfo")
    @ApiOperation("保存酒店")
    @ResponseBody
    public ReturnResult saveHotelInfo(HotelInfo hotelInfo, HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        hotelInfo.setUserId(userInfo.getId());
        log.info("输出保存酒店信息:{}", JSON.toJSONString(hotelInfo));
        hotelInfoService.saveHotelInfo(hotelInfo);
        ReturnResult<List<HotelInfo>> returnResult = hotelInfoService.queryHotelInfoAll(hotelInfo);
        HotelInfo hotelInfoResult = returnResult.getObj().get(0);
        if (!CollectionUtils.isEmpty(hotelInfo.getImages())) {
            for (Object image : hotelInfo.getImages()) {
                HotelInfoImg hotelInfoImg = new HotelInfoImg();
                hotelInfoImg.setHotelInfoId(hotelInfoResult.getId());
                hotelInfoImg.setImgUrl(image.toString());
                hotelInfoImg.setImgUrl(image.toString());
                hotelImgService.addHotelImgService(hotelInfoImg);
            }
        }
        return ResultUtil.GenSuccessMessageDto("酒店保存成功");
    }

    @GetMapping(value = "showHotelInfoDetail")
    @ApiOperation("显示单个酒店详情")
    public ModelAndView showHotelInfoDetail(Integer id, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        HotelInfo hotelInfo = hotelInfoService.getHotelInfo(id);
        HotelInfoImg imgParam = new HotelInfoImg();
        imgParam.setHotelInfoId(id);
        List<HotelInfoImg> hotelInfoImgs = hotelImgService.queryHotelInfoImg(imgParam);
        String[] str = new String[10];
        if (!CollectionUtils.isEmpty(hotelInfoImgs)) {
            for (int i = 0; i < hotelInfoImgs.size(); i++) {
                str[i] = hotelInfoImgs.get(i).getImgUrl();
            }
            modelAndView.addObject("hotelInfoImgs", hotelInfoImgs);
            modelAndView.addObject("hotelInfoImgStr", str);
        }
        modelAndView.addObject("userInfo", session.getAttribute("userInfo"));
        modelAndView.addObject("hotelInfo", hotelInfo);
        modelAndView.setViewName("hotelDetail");
        return modelAndView;
    }

    @PostMapping(value = "queryAllHotelInfo")
    @ApiOperation("获取酒店全部列表")
    @ResponseBody
    public ReturnResult<List<HotelInfo>> queryAllHotelInfo(@RequestBody HotelInfo hotelInfo) {
        log.info("获取酒店全部列表参数:{}", JSON.toJSONString(hotelInfo));
        ReturnResult<List<HotelInfo>> returnResult = hotelInfoService.queryHotelInfoAll(hotelInfo);
        List<HotelInfo> obj = returnResult.getObj();
        for (HotelInfo info : obj) {
            HotelInfoImg hotelInfoImg = new HotelInfoImg();
            hotelInfoImg.setHotelInfoId(info.getId());
            List<HotelInfoImg> hotelInfoImgs = hotelImgService.queryHotelInfoImg(hotelInfoImg);
            if (!CollectionUtils.isEmpty(hotelInfoImgs)) {
                info.setImageUrl(hotelInfoImgs.get(0).getImgUrl());
            }
        }
        returnResult.setObj(obj);
        log.info("输出结果:{}", JSON.toJSONString(returnResult));
        return returnResult;
    }

    @PostMapping(value = "saveHotelOrder")
    @ApiOperation("创建酒店订单")
    @ResponseBody
    public ReturnResult saveHotelOrder(HotelOrder hotelOrder, HttpSession session) {
        log.info("创建酒店订单参数:{}", JSON.toJSONString(hotelOrder));
        //1.判断酒店是否下架
        //2.判断酒店房间是否足够
        //3.判断自己是否已经订购同样的酒店
        HotelInfo hotelInfo = hotelInfoService.getHotelInfo(hotelOrder.getHotelId());
        if (Objects.isNull(hotelInfo)) {
            return ResultUtil.GenFailMessageDto("该酒店已下架");
        }
        HotelInfo editHotelInfo = new HotelInfo();
        editHotelInfo.setId(hotelInfo.getId());
        //房间类型 1标间 2双人间 3单人间
        switch (hotelOrder.getRoomType()) {
            case "1":
                if (hotelInfo.getNum1() < hotelOrder.getRoomNum()) {
                    return ResultUtil.GenFailMessageDto("标间房间不足");
                } else {
                    hotelOrder.setOrderNo("标间A" + hotelInfo.getNum1());
                    editHotelInfo.setNum1(hotelInfo.getNum1() - hotelOrder.getRoomNum());
                }
                break;
            case "2":
                if (hotelInfo.getNum2() < hotelOrder.getRoomNum()) {
                    return ResultUtil.GenFailMessageDto("双人间房间不足");
                } else {
                    hotelOrder.setOrderNo("双人间B" + hotelInfo.getNum2());
                    editHotelInfo.setNum2(hotelInfo.getNum2() - hotelOrder.getRoomNum());
                }
                break;
            case "3":
                if (hotelInfo.getNum3() < hotelOrder.getRoomNum()) {
                    return ResultUtil.GenFailMessageDto("单人间房间不足");
                } else {
                    hotelOrder.setOrderNo("单人间C" + hotelInfo.getNum3());
                    editHotelInfo.setNum3(hotelInfo.getNum3() - hotelOrder.getRoomNum());
                }
                break;
            default:
                break;
        }
        //判断是否已经预订过同一家酒店
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        HotelOrder orderParam = new HotelOrder();
        orderParam.setCreateTime(hotelOrder.getCreateTime());
        orderParam.setEndTime(hotelOrder.getEndTime());
        orderParam.setUserId(userInfo.getId());
        orderParam.setHotelId(hotelOrder.getHotelId());
        orderParam.setRoomType(hotelOrder.getRoomType());
        List<HotelOrder> hotelOrderList = hotelOrderService.queryHotelOrderAll(orderParam);
        if (!CollectionUtils.isEmpty(hotelOrderList)) {
            return ResultUtil.GenFailStatusMessageDto("-1", "您已经预定，请修改");
        }
        try {
            hotelOrder.setUserId(userInfo.getId());
            //创建酒店订单
            hotelOrderService.addHotelOrder(hotelOrder);
            //更新酒店信息房间数量
            hotelInfoService.updateHotelInfo(editHotelInfo);
        } catch (Exception ex) {
            log.error("创建酒店异常:", ex);
        }
        return ResultUtil.GenSuccessMessageDto("预订成功！您预定的房间是" + hotelOrder.getOrderNo());
    }

    @GetMapping(value = "hotelList")
    public ModelAndView hotelList(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("hotelList");
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        modelAndView.addObject("userInfo", userInfo);
        HotelOrder hotelOrder = new HotelOrder();
        //判断是用户还是管理员 用户通过id查询 管理员通过酒店id查询
        //1管理员  2用户
        if(userInfo.getUserType() == 2){
            hotelOrder.setUserId(userInfo.getId());
            ReturnResult<List<HotelInfo>> returnResult = hotelInfoService.queryHotelInfoAll(null);
            List<HotelInfo> obj = returnResult.getObj();
            Map<Integer, HotelInfo> hotelInfoMap = obj.stream().collect(Collectors.toMap(HotelInfo::getId, Function.identity(), (key1, key2) -> key2));
            modelAndView.addObject("hotelInfoMap", hotelInfoMap);
        }
        if(userInfo.getUserType() == 1){
            //获取管理员下面所有的酒店id
            HotelInfo hotelInfoParam = new HotelInfo();
            hotelInfoParam.setUserId(userInfo.getId());
            ReturnResult<List<HotelInfo>> returnResult = hotelInfoService.queryHotelInfoAll(hotelInfoParam);
            if(CollectionUtils.isEmpty(returnResult.getObj())){
                return modelAndView;
            }
            List<HotelInfo> obj = returnResult.getObj();
            List<Integer> hotelIds = Lists.newArrayList();
            for (HotelInfo hotelInfo : obj) {
                hotelIds.add(hotelInfo.getId());
            }
            Map<Integer, HotelInfo> hotelInfoMap = obj.stream().collect(Collectors.toMap(HotelInfo::getId, Function.identity(), (key1, key2) -> key2));
            modelAndView.addObject("hotelInfoMap", hotelInfoMap);
            //通过酒店id查询酒店
            hotelOrder.setHotelIds(hotelIds);
        }
        List<HotelOrder> hotelOrderList = hotelOrderService.queryHotelOrderAll(hotelOrder);
        if(!CollectionUtils.isEmpty(hotelOrderList)){
            modelAndView.addObject("hotelOrderList", hotelOrderList);
        }
        return modelAndView;
    }

    @GetMapping("getHotelOrder")
    @ResponseBody
    public Map getHotelOrder(Integer id){
        Map map = Maps.newHashMap();
        HotelOrder hotelOrder = hotelOrderService.getHotelOrder(id);
        map.put("hotelOrder", hotelOrder);
        HotelInfo hotelInfo = hotelInfoService.getHotelInfo(hotelOrder.getHotelId());
        map.put("hotelInfo", hotelInfo);
        return map;
    }

    @PostMapping("editHotelOrder")
    @ResponseBody
    public ReturnResult editHotelOrder(HotelOrder hotelOrder){
        log.info("输出编辑酒店订单参数:{}", JSON.toJSONString(hotelOrder));
        ReturnResult returnResult = new ReturnResult();
        HotelOrder hotelOrder1 = hotelOrderService.getHotelOrder(hotelOrder.getId());
        if(Objects.isNull(hotelOrder1)){
            return ResultUtil.GenFailMessageDto("无效的订单");
        }
        hotelOrderService.updateHotelOrder(hotelOrder);
        return ResultUtil.GenSuccessMessageDto("修改订单成功");
    }
}
