package com.songyang.tour.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author
 * @desc:
 * @date 2018/2/8
 */
@Component("taskJob")
public class TaskJob {

    int i=0;

//    @Scheduled(cron = "0/5 * *  * * ?")
//    public void job1() {
//        System.out.println((i++)+" taskJob：：："+new Date());
//    }
}