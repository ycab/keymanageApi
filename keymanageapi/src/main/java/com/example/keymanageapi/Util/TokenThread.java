package com.example.keymanageapi.Util;


import com.example.keymanageapi.model.AccessToken;
import com.example.keymanageapi.model.JsapiTicket;

/**
 * 定时获取微信 access_token 和 jsapiTicket 的线程
 */
public class TokenThread implements Runnable {
    // 第三方用户唯一凭证
    public static String appid = "";
    // 第三方用户唯一凭证密钥
    public static String appsecret = "";
    public static AccessToken accessToken = null;
    public static JsapiTicket jsapiTicket = null;



    public void run() {
        while (true) {
            try {
                accessToken = WeiXinUtil.getAccessToken();
                if (null != accessToken) {
                    System.out.println("\u83b7\u53d6access_token\u6210\u529f\uff0c\u6709\u6548\u65f6\u957f" + accessToken.getExpires_in()+ "\u79d2 token:" + accessToken.getAccess_token());
                    try{
                        jsapiTicket = WeiXinUtil.getJsapiTicket(accessToken.getAccess_token());
                        if(jsapiTicket!=null){

                            //String TOKEN_ID="token9047";
                           //T_NJHG_ACCTOKEN t_njhg_acctoken=t_njhg_acctoken_repository.findByToken_Id(TOKEN_ID);
//                            t_njhg_acctoken.setAccess_token(accessToken.getAccess_token());
//                            t_njhg_acctoken.setJSAPI_TICKET(jsapiTicket.getTicket());
//                            Timestamp d = new Timestamp(System.currentTimeMillis());
//                            t_njhg_acctoken.setUPDATE_TIME(d.toString());
//                            t_njhg_acctoken_repository.save(t_njhg_acctoken);

                            System.out.println("\u83b7\u53d6jsapiTicket\u6210\u529f\uff0c\u6709\u6548\u65f6\u957f" + jsapiTicket.getExpiresIn()+ "\u79d2 ticket:" + jsapiTicket.getTicket());
                        }
                    }catch(Exception e){
                        // 如果jsapiTicket为null，60秒后再获取
                        Thread.sleep(60 * 1000);
                    }
                    // 休眠7000秒
                    Thread.sleep((accessToken.getExpires_in() - 200) * 1000);
                } else {
                    // 如果access_token为null，60秒后再获取
                    Thread.sleep(60 * 1000);
                }
            } catch (InterruptedException e) {
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e1) {
                    System.out.println(e1);
                }
                System.out.println( e);
            }
        }
    }
}
