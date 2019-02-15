package beans;

/**
 * 返回值
 * @author ricky
 * @date 2019-01-31
 */
public class StateCode {

    /**
     * 点赞状态枚举类
     */
    public enum PraiseStatusEnum {
        PRAISE(1,"点赞"),
        NOT_PRAISE(0,"未点赞");
        private String value;
        private int code;
        PraiseStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        public int getCode() {
            return code;
        }
        public String getValue() {
            return value;
        }
    }

    /**
     * 推荐、不推荐枚举类
     */
    public enum RecommendEnum {
        Recommend(2,"商品推荐成功！"),
        NOT_Recommend(3,"已取消推荐！"),
        Num(4,"最多只可推荐4个商品！");
        private String value;
        private int code;
        RecommendEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        public int getCode() {
            return code;
        }
        public String getValue() {
            return value;
        }
    }

    /**
     * 商品或分类列表
     */
    public enum ClassifyEnum {
        PRODUCT(10,"商品列表"),
        CLASSIFY(20,"分类列表");
        private String value;
        private int code;
        ClassifyEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        public int getCode() {
            return code;
        }
        public String getValue() {
            return value;
        }
    }

}
