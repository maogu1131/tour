package com.songyang.tour.test;

/**
 * @author
 * @desc:
 * @date 2017/10/26
 */
public class Demo1 {
    public static void main(String[] args){
        String[] split = "141661,6117".split(",");
        System.out.println(split.length);


        String[] split2 = "141661|6117".split("\\|");
        System.out.println(split2.length);
    }
}
