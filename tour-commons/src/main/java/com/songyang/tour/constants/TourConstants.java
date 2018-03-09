package com.songyang.tour.constants;

/**
 * @author
 * @desc: 常量类
 * @date 2017/9/9
 */
public interface TourConstants {


    // 用户ID前缀+9位主键ID
    Integer LEN = 9;
    // 用户ID前缀
    Integer USER_ID_PREFIX = 7;

    String CODE_KEY = "code";

    String MSG_KEY = "msg";

    /**
     * 线上环境
     */
    String PIC_URL_PREFIX = "http://115.239.255.114:8888/img/showPic?imgPath=";

    String remoteFilePath = "/data/www/upload_pic";

    //测试环境
    //String PIC_URL_PREFIX = "http://localhost:8080/img/showPic?imgPath=";
    //String remoteFilePath = "D:";

    String END_TITLE_MSG = "数据已加载完毕";


    String[] RANDOM_CHAR = {"A", "b", "C", "d", "e", "F", "g", "H", "i",
            "j", "K", "M", "n", "P", "Q", "R", "s", "t", "U", "v", "W", "x", "Y", "z"};


    // 评价纬度
    class EFFECT_TYPE {

        // 1-商品
        public static final Integer PROD = 1;

    }

    // 商品来源
    class PROD_SRC {

        // 1 门票
        public static final Integer TICKET = 1;
        // 2 农产品
        public static final Integer FARM_PROD = 2;

    }

    // 份额变动方向
    class DIRECTION {
        // -1 扣减
        public static final Integer DEDUCT = -1;
        // 1 加
        public static final Integer PLUS = 1;
    }

    // 份额流水状态
    class FLOW_STATUS {

        // -1扣减失败
        public static final Integer FAIL = -1;
        // 1扣减成功
        public static final Integer SUCCESS = 1;

    }


    // 订单状态
    class ORDER_STATUS {

        // -1失败
        public static final Integer FAIL = -1;
        // 0待确认
        public static final Integer INIT = 0;
        // 1成功
        public static final Integer SUCCESS = 1;
        // 2确认中
        public static final Integer CONFIRMING = 2;
        // 5已退款
        public static final Integer REFUND = 5;

    }

    // 支付状态
    class PAY_STATUS {
//        0待支付,1支付成功,-1支付失败,2-处理中

        // -1支付失败
        public static final Integer FAIL = -1;
        //  0待支付
        public static final Integer INIT = 0;
        // 1支付成功
        public static final Integer SUCCESS = 1;
        // 2-处理中
        public static final Integer CONFIRMING = 2;

    }

    // 记录状态
    class STATUS {

        // 正常
        public static final Integer NORMAL = 1;
        // 删除
        public static final Integer DELETE = -1;

    }

    //热度
    class HOT {
        //不热
        public static final int NO = 1;
        //热
        public static final int YES = 2;
    }

    //民俗活动开关
    class FOLK_SWITCH {
        //开启
        public static final int ON = 1;
        //关闭
        public static final int OFF = 2;
    }

    //产品状态
    class PROD_STATUS {
        // 删除
        public static final int DEL = -1;
        // 待售
        public static final int FOR_SALE = 1;
        // 售罄
        public static final int SOLD_OUT = 3;
    }

    //收货地址状态
    class MAILING_ADDRESS_TYPE {
        //默认
        public static final int DEFAULT_TYPE = 1;
        //备份
        public static final int BACKUP_TYPE = 2;
    }

    // 操作类型
    class OPERATE_TYPE {
        // 初始化
        public static final int INIT = 0;
        // 添加
        public static final int ADD = 1;
        // 更新
        public static final int UPDATE = 2;
        // 删除
        public static final int DELETE = 3;
        // 查看
        public static final int SEE = 4;
    }

    //管理员角色
    class ADMIN_ROLE {
        //超级管理员
        public static final int SUPER_TYPE = 1;
        //普通管理员
        public static final int NORMAL_TYPE = 2;
    }

    //管理员状态
    class ADMIN_STATUS {
        //可用
        public static final int AVAILABLE = 1;
        //不可用
        public static final int UNAVAILABLE = 2;
    }

    //支付方式
    class PAY_WAY {
        public static final int ALIPAY = 1;

        public static final int WEIXIN_PAY = 2;
    }
}
