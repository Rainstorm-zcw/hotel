package com.wisdom.service;

import com.wisdom.entity.HotelType;

import java.util.List;

public interface HotelTypeService {

    /**
     * 添加酒店类型
     * @param hotelType
     * @return
     */
    int addHotelType(HotelType hotelType);

    /**
     * 查询全部酒店类型
     * @param hotelType
     * @return
     */
    List<HotelType> queryHotelType(HotelType hotelType);
}
