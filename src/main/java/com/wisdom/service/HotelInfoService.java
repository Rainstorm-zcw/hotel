package com.wisdom.service;

import com.wisdom.entity.HotelInfo;
import com.wisdom.vo.ReturnResult;

import java.util.List;

public interface HotelInfoService {

    /**
     * 查看酒店详情
     *
     * @param hotelId 酒店id
     * @return 返回酒店详情
     */
    HotelInfo getHotelInfo(Integer hotelId);

    /**
     * 获取酒店详情信息
     *
     * @param hotelInfo 酒店信息
     * @return 返回酒店列表
     */
    ReturnResult<List<HotelInfo>> queryHotelInfoAll(HotelInfo hotelInfo);


    /**
     * 删除酒店
     *
     * @param hotelId 酒店id
     * @return 返回删除结果
     */
    Integer delHotelInfo(Integer hotelId);

    /**
     * 保存酒店
     *
     * @param hotelInfo 酒店信息
     * @return 返回保存结果
     */
    Integer saveHotelInfo(HotelInfo hotelInfo);

    int updateHotelInfo(HotelInfo hotelInfo);
}
