package com.wisdom.entity;

import java.io.Serializable;
import java.util.List;

public class HotelInfo implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_info.ID
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_info.hotelName
     *
     * @mbggenerated
     */
    private String hotelname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_info.hotelAddress
     *
     * @mbggenerated
     */
    private String hoteladdress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_info.hotelTel
     *
     * @mbggenerated
     */
    private String hoteltel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_info.hotelType
     *
     * @mbggenerated
     */
    private String hoteltype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_info.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_info.num1
     *
     * @mbggenerated
     */
    private Integer num1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_info.num2
     *
     * @mbggenerated
     */
    private Integer num2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_info.num3
     *
     * @mbggenerated
     */
    private Integer num3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_info.hotelStart
     *
     * @mbggenerated
     */
    private String hotelstart;

    private Integer userId;

    private List images;

    private List<HotelOrder> hotelOrderList;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table hotel_info
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_info.ID
     *
     * @return the value of hotel_info.ID
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_info.ID
     *
     * @param id the value for hotel_info.ID
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_info.hotelName
     *
     * @return the value of hotel_info.hotelName
     *
     * @mbggenerated
     */
    public String getHotelname() {
        return hotelname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_info.hotelName
     *
     * @param hotelname the value for hotel_info.hotelName
     *
     * @mbggenerated
     */
    public void setHotelname(String hotelname) {
        this.hotelname = hotelname == null ? null : hotelname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_info.hotelAddress
     *
     * @return the value of hotel_info.hotelAddress
     *
     * @mbggenerated
     */
    public String getHoteladdress() {
        return hoteladdress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_info.hotelAddress
     *
     * @param hoteladdress the value for hotel_info.hotelAddress
     *
     * @mbggenerated
     */
    public void setHoteladdress(String hoteladdress) {
        this.hoteladdress = hoteladdress == null ? null : hoteladdress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_info.hotelTel
     *
     * @return the value of hotel_info.hotelTel
     *
     * @mbggenerated
     */
    public String getHoteltel() {
        return hoteltel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_info.hotelTel
     *
     * @param hoteltel the value for hotel_info.hotelTel
     *
     * @mbggenerated
     */
    public void setHoteltel(String hoteltel) {
        this.hoteltel = hoteltel == null ? null : hoteltel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_info.hotelType
     *
     * @return the value of hotel_info.hotelType
     *
     * @mbggenerated
     */
    public String getHoteltype() {
        return hoteltype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_info.hotelType
     *
     * @param hoteltype the value for hotel_info.hotelType
     *
     * @mbggenerated
     */
    public void setHoteltype(String hoteltype) {
        this.hoteltype = hoteltype == null ? null : hoteltype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_info.content
     *
     * @return the value of hotel_info.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_info.content
     *
     * @param content the value for hotel_info.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_info.num1
     *
     * @return the value of hotel_info.num1
     *
     * @mbggenerated
     */
    public Integer getNum1() {
        return num1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_info.num1
     *
     * @param num1 the value for hotel_info.num1
     *
     * @mbggenerated
     */
    public void setNum1(Integer num1) {
        this.num1 = num1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_info.num2
     *
     * @return the value of hotel_info.num2
     *
     * @mbggenerated
     */
    public Integer getNum2() {
        return num2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_info.num2
     *
     * @param num2 the value for hotel_info.num2
     *
     * @mbggenerated
     */
    public void setNum2(Integer num2) {
        this.num2 = num2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_info.num3
     *
     * @return the value of hotel_info.num3
     *
     * @mbggenerated
     */
    public Integer getNum3() {
        return num3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_info.num3
     *
     * @param num3 the value for hotel_info.num3
     *
     * @mbggenerated
     */
    public void setNum3(Integer num3) {
        this.num3 = num3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_info.hotelStart
     *
     * @return the value of hotel_info.hotelStart
     *
     * @mbggenerated
     */
    public String getHotelstart() {
        return hotelstart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_info.hotelStart
     *
     * @param hotelstart the value for hotel_info.hotelStart
     *
     * @mbggenerated
     */
    public void setHotelstart(String hotelstart) {
        this.hotelstart = hotelstart == null ? null : hotelstart.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List getImages() {
        return images;
    }

    public void setImages(List images) {
        this.images = images;
    }

    public List<HotelOrder> getHotelOrderList() {
        return hotelOrderList;
    }

    public void setHotelOrderList(List<HotelOrder> hotelOrderList) {
        this.hotelOrderList = hotelOrderList;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hotel_info
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", hotelname=").append(hotelname);
        sb.append(", hoteladdress=").append(hoteladdress);
        sb.append(", hoteltel=").append(hoteltel);
        sb.append(", hoteltype=").append(hoteltype);
        sb.append(", content=").append(content);
        sb.append(", num1=").append(num1);
        sb.append(", num2=").append(num2);
        sb.append(", num3=").append(num3);
        sb.append(", hotelstart=").append(hotelstart);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}