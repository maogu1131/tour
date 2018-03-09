package com.songyang.tour.query;/**
 * Created by lenovo on 2017/12/9.
 */

/**
 * 管理员查询query
 *
 * @author
 * @create 2017-12-09 21:21
 **/
public class SyAdminUserQuery extends BaseQuery {
    private Long id;
    //用户名
    private String userName;
    //用户状态：1:可用  2:不可用
    private Integer status;
    //角色类型：1.超级管理员 2:普通管理员
    private Integer roleType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }
}


