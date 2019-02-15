package utils;

/**
 * 工具类
 * @author ricky
 * @date 2019-01-29
 */
public class DecryptUtils {

    /**
     *  解密
     * @param  minaStr 得到参数，小程序ID
     * @param  appId 得到参数，appID
     * @return String  返回字符串
     */
    @SuppressWarnings("finally")
    public static String getMinaId(String minaStr,String appId) {
        String result=null;
        try {
            result = AesEncrypt.aesDecryption(appId, minaStr);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * 图片重组
     *  @param picture 图片路径
     *  @param path 域名路径
     *  @return String  返回拼接完整的图片路径
     */
    public static  String rebuildPic(String picture,String path) {
        if (picture != null && !"".equals(picture)) {
            String[] imgList = picture.split("\\|");
            // 判断是否为多张图片
            if (imgList.length > 1) {
                // 多张
                StringBuffer newPicture = new StringBuffer();
                for (int i = 0; i < imgList.length; i++) {
                    if (imgList[i] != null && !"".equals(imgList[i])) {
                        newPicture.append("|");
                        // 判断是否是同步过来的数据
                        if (imgList[i].split("images").length <= 1) {
                            boolean b=imgList[i].startsWith("http");
                            if(!b) {
                                newPicture.append(path + imgList[i]);
                            }else {
                                newPicture.append(imgList[i]);
                            }
                        } else {
                            newPicture.append(imgList[i]);
                        }
                    }
                }
                picture = newPicture.toString();
            } else {
                // 单张
                // 判断是否是同步过来的数据
                if (picture.split("images").length <= 1) {
                    boolean b=picture.startsWith("http");
                    if(!b) {
                        picture = path + picture;
                    }
                }
            }
        }
        return picture;
    }
}
