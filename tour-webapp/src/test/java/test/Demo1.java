package test;

import com.songyang.tour.constants.TourConstants;
import org.apache.commons.lang3.StringUtils;

/**
 * @author
 * @desc:
 * @date 2017/12/3
 */
public class Demo1 {
    public static void main(String[] args){
        System.out.println(TourConstants.USER_ID_PREFIX + StringUtils.leftPad(String.valueOf("123"),TourConstants.LEN,"0"));
    }
}
