package com.songyang.tour.controller.mobile.api;/**
 * Created by lenovo on 2017/11/4.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.components.ControllerExecuteCallback;
import com.songyang.tour.components.ControllerExecuteTemplate;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.exception.TourBizException;
import com.songyang.tour.model.Model;
import com.songyang.tour.pojo.SyMailingAddress;
import com.songyang.tour.query.SyMailingAddressQuery;
import com.songyang.tour.service.SyMailingAddressService;
import com.songyang.tour.utils.JsonUtil;
import com.songyang.tour.utils.SessionUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 收货地址Controller
 *
 * @author
 * @create 2017-11-04 16:36
 **/
@Controller
@RequestMapping("/mobile/api/member")
public class MailingAddressController {
    private Logger logger = LoggerFactory.getLogger(MailingAddressController.class);
    @Resource
    private ControllerExecuteTemplate controllerExecuteTemplate;

    @Resource
    private SyMailingAddressService syMailingAddressService;

    @ResponseBody
    @RequestMapping(value = "/address/save", method = {RequestMethod.GET, RequestMethod.POST})
    private Model<JSONObject> save(final HttpServletRequest request) {
        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<JSONObject>() {
            @Override
            public Model doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                JSONObject jo = new JSONObject();
                String userId = SessionUtil.getCurrUserId();
                String userName = param.getString("userName");
                String phone = param.getString("phone");
                String address = param.getString("address");
                SyMailingAddress syMailingAddress = new SyMailingAddress();

                Long id = param.getLong("id");
                if (null == id) {
                    Assert.hasText(userId, "用户id不能为空");
                    Assert.hasText(userName, "收货人不能为空");
                    Assert.hasText(phone, "联系电话不能为空");
                    Assert.hasText(address, "联系地址不能为空");
                    SyMailingAddressQuery query = new SyMailingAddressQuery();
                    query.setUserId(userId);
                    query.setOffset(0);
                    query.setRows(10);
                    List<SyMailingAddress> addresseList = syMailingAddressService.queryListByParam(query);
                    if (!CollectionUtils.isEmpty(addresseList)) {
                        if (addresseList.size() > 5) {
                            throw new TourBizException("最多可以添加5个收货地址哦~");
                        }
                    }
                    syMailingAddress.setAddress(address);
                    syMailingAddress.setPhone(phone);
                    syMailingAddress.setType(TourConstants.MAILING_ADDRESS_TYPE.BACKUP_TYPE);
                    syMailingAddress.setUserId(userId);
                    syMailingAddress.setUserName(userName);
                    syMailingAddressService.insert(syMailingAddress);
                } else {
                    syMailingAddress.setId(id);
                    syMailingAddress.setAddress(address);
                    syMailingAddress.setPhone(phone);
                    syMailingAddress.setUserId(userId);
                    syMailingAddress.setUserName(userName);
                    syMailingAddressService.updateById(syMailingAddress);
                }

                return Model.buidSucc();
            }
        }, logger, "保存地址");
    }


    @ResponseBody
    @RequestMapping(value = "/address/addressList", method = {RequestMethod.GET, RequestMethod.POST})
    private Model<JSONObject> addressList(final HttpServletRequest request) {

        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<JSONObject>() {
            @Override
            public Model<JSONObject> doExecute() throws IOException {
                Model<JSONObject> model = new Model<>();
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                JSONObject jo = new JSONObject();
                String userId = SessionUtil.getCurrUserId();

                Assert.hasText(userId, "用户id不能为空");

                SyMailingAddressQuery query = new SyMailingAddressQuery();
                query.setUserId(userId);
                query.setOffset(0);
                query.setRows(100);
                List<SyMailingAddress> addresseList = syMailingAddressService.queryListByParam(query);

                List<JSONObject> list = new ArrayList<JSONObject>();
                if (CollectionUtils.isNotEmpty(addresseList)) {
                    JSONObject jsonObject = null;
                    for (SyMailingAddress address : addresseList) {
                        jsonObject = new JSONObject();
                        jsonObject.put("id", address.getId());
                        jsonObject.put("userName", address.getUserName());
                        jsonObject.put("phone", address.getPhone());
                        jsonObject.put("address", address.getAddress());
                        jsonObject.put("type", address.getType() == TourConstants.MAILING_ADDRESS_TYPE.DEFAULT_TYPE ?
                                TourConstants.MAILING_ADDRESS_TYPE.DEFAULT_TYPE : TourConstants.MAILING_ADDRESS_TYPE.BACKUP_TYPE);
                        list.add(jsonObject);
                    }
                }
                jo.put("addressList", list);
                model.setData(jo);
                return model;
            }
        }, logger, "地址列表");
    }

    @ResponseBody
    @RequestMapping(value = "/address/edit", method = {RequestMethod.GET, RequestMethod.POST})
    private Model<JSONObject> edit(final HttpServletRequest request) {

        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<JSONObject>() {
            @Override
            public Model<JSONObject> doExecute() throws IOException {

                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);

                String userId = SessionUtil.getCurrUserId();
                Long id = param.getLong("id");

                Assert.notNull(id, "参数不正确");

                SyMailingAddress address = syMailingAddressService.selectById(id);
                if (null == address) {
                    throw new TourBizException("收货地址不存在");
                }
                if (!StringUtils.equals(address.getUserId(), userId)) {
                    throw new TourBizException("操作用户不正确");
                }
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("id", address.getId());
                jsonObject.put("userName", address.getUserName());
                jsonObject.put("phone", address.getPhone());
                jsonObject.put("address", address.getAddress());

                return Model.buidSucc(jsonObject);
            }
        }, logger, "地址编辑");
    }

    @ResponseBody
    @RequestMapping(value = "/address/setDefault", method = {RequestMethod.GET, RequestMethod.POST})
    private Model<JSONObject> setDefault(final HttpServletRequest request) {

        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<JSONObject>() {
            @Override
            public Model<JSONObject> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                Long id = param.getLong("id");

                Assert.notNull(id, "参数不正确");

                SyMailingAddress address = syMailingAddressService.selectById(id);
                if (null == address) {
                    throw new TourBizException("收货地址不存在");
                }

                String userId = SessionUtil.getCurrUserId();
                if (!StringUtils.equals(address.getUserId(), userId)) {
                    throw new TourBizException("操作用户不正确");
                }

                SyMailingAddressQuery query = new SyMailingAddressQuery();
                query.setUserId(address.getUserId());
                query.setOffset(0);
                query.setRows(50);
                List<SyMailingAddress> addresseList = syMailingAddressService.queryListByParam(query);

                SyMailingAddress mailingAddress = null;
                if (CollectionUtils.isNotEmpty(addresseList)) {
                    for (SyMailingAddress temp : addresseList) {
                        mailingAddress = new SyMailingAddress();
                        mailingAddress.setId(temp.getId());
                        mailingAddress.setType(TourConstants.MAILING_ADDRESS_TYPE.BACKUP_TYPE);
                        syMailingAddressService.updateById(mailingAddress);
                    }
                }

                address.setType(TourConstants.MAILING_ADDRESS_TYPE.DEFAULT_TYPE);
                syMailingAddressService.updateById(address);

                return Model.buidSucc();
            }
        }, logger, "设置默认地址");
    }

    @ResponseBody
    @RequestMapping(value = "/address/delete", method = {RequestMethod.GET, RequestMethod.POST})
    private Model<JSONObject> delete(final HttpServletRequest request) {

        return controllerExecuteTemplate.execute(request, new ControllerExecuteCallback<JSONObject>() {
            @Override
            public Model<JSONObject> doExecute() throws IOException {
                JSONObject param = JsonUtil.getJSONObjectFromRequestStream(request);
                Long id = param.getLong("id");

                Assert.notNull(id, "参数不正确");
                String userId = SessionUtil.getCurrUserId();

                SyMailingAddress address = syMailingAddressService.selectById(id);
                if (null == address) {
                    throw new TourBizException("收货地址不存在");
                }

                if (!StringUtils.equals(address.getUserId(), userId)) {
                    throw new TourBizException("操作用户不正确");
                }

                syMailingAddressService.deleteById(id);

                return Model.buidSucc();
            }
        }, logger, "删除地址");
    }
}
