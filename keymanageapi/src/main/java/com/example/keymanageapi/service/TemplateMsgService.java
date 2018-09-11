package com.example.keymanageapi.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.example.keymanageapi.Util.TokenThread;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

@Service
public class TemplateMsgService {




    //人员审核通过
    public String WeChatTemplateMsgService(String openid,String result,String time ){
        String  res;
        String access_token;

        try{
            //获取access_token
            //Get方式
                access_token = TokenThread.accessToken.getAccess_token();
                    String myData = "{" +
                                "\"touser\":\"" + openid + "\"," +
                                "\"template_id\":\"r3WCbmYFGzdUL6CclwT9atdlTvSDVoZAgeFNnsk3T04\"," +
                                "\"url\":\"\"," +
                                "\"data\":" +
                                "{" +
                                "\"first\":" +
                                "{" +
                                "\"value\":\"您好！您提交的个人信息已审核，结果如下：\"," +
                                "\"color\":\"#173177\"" +
                                "}," +
                                "\"keyword1\":" +
                                "{" +
                                "\"value\":\"" + result + "\"," +
                                "\"color\":\"#173177\"" +
                                "}," +
                                "\"keyword2\":" +
                                "{" +
                                "\"value\":\"" + time + "\"," +
                                "\"color\":\"#173177\"" +
                                "}," +
                                "\"remark\":" +
                                "{" +
                                "\"value\":\"您的信息审核通过后，即可自动登录用户界面。\"," +
                                "\"color\":\"#173177\"" +
                                "}" +
                                "}" +
                                "}";

                String str2 = HttpPost(access_token, myData);
                JSONObject json2= JSON.parseObject(str2);   //解析json成json对象

                if(json2.getString("errcode").equals("0")){
                    res = "[{msg:'ok'}]";
                }else{
                    res="[{mgs:'fail',faiInfo:'Post推送消息失败'}]";
                }

        }catch(Exception e){
            res="[{mgs:'fail',faiInfo:'"+e.toString()+"'}]";
        }
        return res;
    }


    //人员审核不通过
    public String WeChatTemplateMsg1Service(String openid,String result,String time ){
        String  res;
        String access_token;

        try{
            //获取access_token
            //Get方式
            access_token = TokenThread.accessToken.getAccess_token();
            String myData = "{" +
                    "\"touser\":\"" + openid + "\"," +
                    "\"template_id\":\"r3WCbmYFGzdUL6CclwT9atdlTvSDVoZAgeFNnsk3T04\"," +
                    "\"url\":\"\"," +
                    "\"data\":" +
                    "{" +
                    "\"first\":" +
                    "{" +
                    "\"value\":\"您好！您提交的个人信息已审核，结果如下：\"," +
                    "\"color\":\"#173177\"" +
                    "}," +
                    "\"keyword1\":" +
                    "{" +
                    "\"value\":\"" + result + "\"," +
                    "\"color\":\"#173177\"" +
                    "}," +
                    "\"keyword2\":" +
                    "{" +
                    "\"value\":\"" + time + "\"," +
                    "\"color\":\"#173177\"" +
                    "}," +
                    "\"remark\":" +
                    "{" +
                    "\"value\":\"请仔细核对个人信息是否有误，如有需要请重新注册提交个人信息。\"," +
                    "\"color\":\"#173177\"" +
                    "}" +
                    "}" +
                    "}";

            String str2 = HttpPost(access_token, myData);
            JSONObject json2= JSON.parseObject(str2);   //解析json成json对象

            if(json2.getString("errcode").equals("0")){
                res = "[{msg:'ok'}]";
            }else{
                res="[{mgs:'fail',faiInfo:'Post推送消息失败'}]";
            }

        }catch(Exception e){
            res="[{mgs:'fail',faiInfo:'"+e.toString()+"'}]";
        }
        return res;
    }



    //物品审核通过
    public String WeChatTemplateMsg2Service(String openid,String result,String time ){
        String  res;
        String access_token;

        try{
            access_token = TokenThread.accessToken.getAccess_token();
            String myData = "{" +
                    "\"touser\":\"" + openid + "\"," +
                    "\"template_id\":\"r3WCbmYFGzdUL6CclwT9atdlTvSDVoZAgeFNnsk3T04\"," +
                    "\"url\":\"     \"," +
                    "\"data\":" +
                    "{" +
                    "\"first\":" +
                    "{" +
                    "\"value\":\"您好！您提交物品申请已审核，结果如下：\"," +
                    "\"color\":\"#173177\"" +
                    "}," +
                    "\"keyword1\":" +
                    "{" +
                    "\"value\":\"" + result + "\"," +
                    "\"color\":\"#173177\"" +
                    "}," +
                    "\"keyword2\":" +
                    "{" +
                    "\"value\":\"" + time + "\"," +
                    "\"color\":\"#173177\"" +
                    "}," +
                    "\"remark\":" +
                    "{" +
                    "\"value\":\"您的信息审核通过后，即可扫码开箱取物。\"," +
                    "\"color\":\"#173177\"" +
                    "}" +
                    "}" +
                    "}";

            String str2 = HttpPost(access_token, myData);
            JSONObject json2= JSON.parseObject(str2);   //解析json成json对象

            if(json2.getString("errcode").equals("0")){
                res = "[{msg:'ok'}]";
            }else{
                res="[{mgs:'fail',faiInfo:'Post推送消息失败'}]";
            }

        }catch(Exception e){
            res="[{mgs:'fail',faiInfo:'"+e.toString()+"'}]";
        }
        return res;
    }




    //物品审核不通过
    public String WeChatTemplateMsg3Service(String openid,String result,String time ){
        String  res;
        String access_token;

        try{
            access_token = TokenThread.accessToken.getAccess_token();
            String myData = "{" +
                    "\"touser\":\"" + openid + "\"," +
                    "\"template_id\":\"r3WCbmYFGzdUL6CclwT9atdlTvSDVoZAgeFNnsk3T04\"," +
                    "\"url\":\"\"," +
                    "\"data\":" +
                    "{" +
                    "\"first\":" +
                    "{" +
                    "\"value\":\"您好！您提交物品申请已审核，结果如下：\"," +
                    "\"color\":\"#173177\"" +
                    "}," +
                    "\"keyword1\":" +
                    "{" +
                    "\"value\":\"" + result + "\"," +
                    "\"color\":\"#173177\"" +
                    "}," +
                    "\"keyword2\":" +
                    "{" +
                    "\"value\":\"" + time + "\"," +
                    "\"color\":\"#173177\"" +
                    "}," +
                    "\"remark\":" +
                    "{" +
                    "\"value\":\"您暂时无法申请使用该物品。\"," +
                    "\"color\":\"#173177\"" +
                    "}" +
                    "}" +
                    "}";

            String str2 = HttpPost(access_token, myData);
            JSONObject json2= JSON.parseObject(str2);   //解析json成json对象

            if(json2.getString("errcode").equals("0")){
                res = "[{msg:'ok'}]";
            }else{
                res="[{mgs:'fail',faiInfo:'Post推送消息失败'}]";
            }

        }catch(Exception e){
            res="[{mgs:'fail',faiInfo:'"+e.toString()+"'}]";
        }
        return res;
    }

















    /**********************
     * httpget
     * httppost
     * ********************/
    //HttpGet方法
    public String HttpGet(String URL, String GetDataStr){

        String getUrl = URL+GetDataStr;
        StringBuffer sb = new StringBuffer();
        InputStreamReader isr = null;
        BufferedReader br = null;
        try
        {
            java.net.URL url = new URL(getUrl);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setAllowUserInteraction(false);
            isr = new InputStreamReader(url.openStream());
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try {
                br.close();
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // fileOperator.closeResources(isr, br);
        }
        return sb.toString();
    }

    //HttpPost方法
    public  String HttpPost(String access_token, String myData){
        String url = String.format("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s",access_token);
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(myData);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }


}
