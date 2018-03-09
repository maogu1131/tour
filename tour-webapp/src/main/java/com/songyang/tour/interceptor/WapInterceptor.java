package com.songyang.tour.interceptor;/**
 * Created by lenovo on 2017/12/7.
 */

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 跨域拦截器
 *
 * @author
 * @create 2017-12-07 11:18
 **/
public class WapInterceptor implements HandlerInterceptor {
    private static Pattern pattern = Pattern.compile("(http|https)://(\\w|\\.)*(songyang|syfund|isongyang|tongzier)\\.(com|org)");

    public WapInterceptor() {
    }

    public static void main(String[] args) {
        Matcher m = pattern.matcher("http://m.songyang.com/sy/page/register.html?redirectURL=http%3A%2F%2Fm.songyang.com#page_1");
        Matcher f = pattern.matcher("http://mapi.syfund.com/sy/page/register.html?redirectURL=http%3A%2F%2Fm.songyang.com#page_1");
        Matcher or = pattern.matcher("http://mapi.isongyang.com/sy/page/register.html?redirectURL=http%3A%2F%2Fm.songyang.com#page_1");
        System.out.println(m.find());
        System.out.println(m.group());
        System.out.println(f.find());
        System.out.println(f.group());
        System.out.println(or.find());
        System.out.println(or.group());
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String referer = request.getHeader("Referer");
        if (referer != null) {
            Matcher m = pattern.matcher(referer);
            if (m.find()) {
                response.setHeader("Access-Control-Allow-Origin", m.group());
                response.setHeader("Access-Control-Allow-Credentials", "true");
            }
        }

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
