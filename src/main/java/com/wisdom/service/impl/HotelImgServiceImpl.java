package com.wisdom.service.impl;

import com.wisdom.entity.HotelInfoImg;
import com.wisdom.entity.HotelInfoImgExample;
import com.wisdom.mapper.HotelInfoImgMapper;
import com.wisdom.service.HotelImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class HotelImgServiceImpl  implements HotelImgService {

    @Autowired
    private HotelInfoImgMapper hotelInfoImgMapper;

    @Override
    public int addHotelImgService(HotelInfoImg hotelInfoImg) {

        return hotelInfoImgMapper.insert(hotelInfoImg);
    }

    @Override
    public List<HotelInfoImg> queryHotelInfoImg(HotelInfoImg hotelInfoImg){
        HotelInfoImgExample example = new HotelInfoImgExample();
        HotelInfoImgExample.Criteria criteria = example.createCriteria();
        if(Objects.nonNull(hotelInfoImg.getHotelInfoId())){
            criteria.andHotelInfoIdEqualTo(hotelInfoImg.getHotelInfoId());
        }
        if(Objects.nonNull(hotelInfoImg.getId())){
            criteria.andIdEqualTo(hotelInfoImg.getId());
        }
        if(Objects.nonNull(hotelInfoImg.getImgUrl())){
            criteria.andImgUrlEqualTo(hotelInfoImg.getImgUrl());
        }
        return hotelInfoImgMapper.selectByExample(example);
    }

    @Override
    public int delHotelInfoImg(Integer id){
        return hotelInfoImgMapper.deleteByPrimaryKey(id);
    }
}
