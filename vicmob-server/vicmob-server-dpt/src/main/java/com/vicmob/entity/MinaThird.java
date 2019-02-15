package com.vicmob.entity;

import lombok.Data;
import java.util.Date;

/**
 * 小程序第三方平台实体类
 * @author ziv
 * @date 2019-01-24
 */
@Data
public class MinaThird {

    private Integer thirdId;

    private String appId;

    private String appSecret;

    private String encodingKey;

    private String token;

    private String ticket;

    private Date ticketUpdate;

    private String accessToken;

    private Integer tonkenExpire;
}
