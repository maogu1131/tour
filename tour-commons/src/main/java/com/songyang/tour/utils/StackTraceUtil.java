package com.songyang.tour.utils;/**
 * Created by lenovo on 2017/9/29.
 */

/**
 * 堆栈工具
 *
 * @author
 * @create 2017-09-29 23:18
 **/
public class StackTraceUtil {

    @SuppressWarnings("unused")
    public static  String getCurrentCallMethodName(){
        return getCallMethodName(1);
    }

    /**
     * 获取执行getCurrentCallMethodName方法的当前方法方法名
     * @param depth 调用深度，调用getCallMethodName的方法深度为0, 每往上一层调用+1
     * @return 返回方法名
     */
    public static  String getCallMethodName(int depth){
        if (depth < 0){
            throw new IllegalArgumentException("depth must bigger than 0!");
        }

        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        if (2 + depth > stacks.length -1){
            return null;
        }
        return stacks[2 + depth].getMethodName();
    }
}
