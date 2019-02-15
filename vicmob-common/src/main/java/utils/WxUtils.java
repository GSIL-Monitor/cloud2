package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 微信工具类
 * @author ziv
 * @date 2019-01-22
 */
public class WxUtils {

    /**
     * sha1加密
     * @param inStr 需要加密的字符串
     * @return String 加密后字符串
     */
    public static String SHA1(String inStr) {
        MessageDigest md = null;
        String outStr = null;
        try {
            // 选择SHA-1，也可以选择MD5
            md = MessageDigest.getInstance("SHA-1");
            // 返回的是byet[]，要转化为String存储比较方便
            byte[] digest = md.digest(inStr.getBytes());
            outStr = byteToString(digest);
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        return outStr;
    }

    /**
     * 字节转字符串
     * @param digest 字节数组
     * @return String 转换后字符串
     */
    public static String byteToString(byte[] digest) {
        StringBuilder builder = new StringBuilder();
        String tempStr = "";
        for (int i = 0; i < digest.length; i++) {
            tempStr = (Integer.toHexString(digest[i] & 0xff));
            if (tempStr.length() == 1) {
                builder.append("0");
                builder.append(tempStr);
            } else {
                builder.append(tempStr);
            }
        }
        return builder.toString().toLowerCase();
    }
}
