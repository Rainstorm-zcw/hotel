package com.wisdom.service.impl;

import com.wisdom.entity.HotelType;
import com.wisdom.entity.HotelTypeExample;
import com.wisdom.mapper.HotelTypeMapper;
import com.wisdom.service.HotelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class HotelTypeServiceImpl implements HotelTypeService {

    @Autowired
    private HotelTypeMapper hotelTypeMapper;


    @Override
    public int addHotelType(HotelType hotelType) {
        return hotelTypeMapper.insert(hotelType);
    }

    @Override
    public List<HotelType> queryHotelType(HotelType hotelType) {
        HotelTypeExample hotelTypeExample = new HotelTypeExample();
        HotelTypeExample.Criteria criteria = hotelTypeExample.createCriteria();
        hotelTypeExample.setOrderByClause("id asc");
        if(null == hotelType){
            hotelType = new HotelType();
        }
        if(Objects.nonNull(hotelType.getId())){
            criteria.andIdEqualTo(hotelType.getId());
        }
        return hotelTypeMapper.selectByExample(hotelTypeExample);
    }
}
