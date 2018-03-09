package com.songyang.tour.login;

/**
 * @author
 * @desc:
 * @date 2017/11/11
 */

//         import cn.xishan.oftenporter.porter.core.base.WObject;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * 微信登录
 */
public class WeiXinLogin {
    private final String APPID = "";
    private final String SECRET = "";

    /**
     * 通过请求微信登录，跳转后进行处理
     * https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
     * 授权成功返回：
     * redirect_uri?code=CODE&state=STATE
     * 授权失败返回：
     * redirect_uri?state=STATE
     * 获得code后获取access_token
     */

//      if(wObject.getRequest().getParameter("code")!=null){
//        String code = (String) wObject.getRequest().getParameter("code");

    public void redirect(HttpServletRequest request) {
        String code = request.getParameter("code");
        String accessToken = getAccessToken(code);

        if (accessToken != null) {
            JSONObject returnObj = JSONObject.parseObject(accessToken);
            if (returnObj != null) {
                String access_token = returnObj.getString("access_token");
                String openId = returnObj.getString("openid");

                String userInfo = getUserInfo(access_token, openId);
                System.out.println(userInfo);
            }
        }

    }

    /**
     * 获得code后获取access_token
     * {
     * "access_token":"ACCESS_TOKEN",
     * "expires_in":7200,
     * "refresh_token":"REFRESH_TOKEN",
     * "openid":"OPENID",
     * "scope":"SCOPE"
     * }
     *
     * @param code
     */
    public String getAccessToken(String code) {
        // https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
        try {
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
            String param = "appid=" + APPID + "&secret=" + SECRET + "&code=" + code + "&grant_type=authorization_code";
            return sendGet(url, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取微信用户信息
     * https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
     * {
     * "openid":"OPENID",
     * "nickname":"NICKNAME",
     * "sex":1,
     * "province":"PROVINCE",
     * "city":"CITY",
     * "country":"COUNTRY",
     * "headimgurl": "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
     * "privilege":[
     * "PRIVILEGE1",
     * "PRIVILEGE2"
     * ],
     * "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
     * <p>
     * }
     *
     * @param access_token
     * @param openId
     * @return
     */
    public String getUserInfo(String access_token, String openId) {
        return sendGet("https://api.weixin.qq.com/sns/userinfo", "access_token=" + access_token + "&openid=" + openId);
    }


    /**
     * 发送GET请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


}
