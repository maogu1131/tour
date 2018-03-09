package com.songyang.tour.vo;

/**
 * @author
 * @desc:
 * @date 2017/12/17
 */
public class MailAddressVO {

    // 收货人
    private String userName;
    // 联系电话
    private String phone;
    // 地址
    private String address;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
