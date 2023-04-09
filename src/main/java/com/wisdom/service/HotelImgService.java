package com.wisdom.service;

import com.wisdom.entity.HotelInfoImg;

import java.util.List;

public interface HotelImgService {

    int addHotelImgService(HotelInfoImg hotelInfoImg);

    List<HotelInfoImg> queryHotelInfoImg(HotelInfoImg hotelInfoImg);

    int delHotelInfoImg(Integer id);
}
