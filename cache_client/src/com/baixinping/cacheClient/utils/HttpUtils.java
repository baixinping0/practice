package com.baixinping.cacheClient.utils;

import java.io.BufferedReader;
    import java.io.ByteArrayOutputStream;  
    import java.io.IOException;  
    import java.io.InputStream;  
    import java.io.InputStreamReader;  
    import java.io.PrintWriter;  
    import java.net.HttpURLConnection;  
    import java.net.URL;  
      
    public class HttpUtils
    {  
        private static final int TIMEOUT_IN_MILLIONS = 5000;
        public interface CallBack
        {  
            void onRequestComplete(String result);  
        }  
        /**
         * 异步的Get请求 
         *  
         * @param urlStr 
         * @param callBack 
         */  
        public static void doGetAsynCallBack(final String urlStr, final CallBack callBack)
        {  
            new Thread()  
            {  
                public void run()  
                {  
                    try  
                    {  
                        String result = doGet(urlStr);  
                        if (callBack != null)  
                        {  
                            callBack.onRequestComplete(result);  
                        }  
                    } catch (Exception e)  
                    {  
                        e.printStackTrace();  
                    }  
      
                };  
            }.start();  
        }

        /**
         * 异步的Get请求
         *
         * @param urlStr
         */
        public static void doGetAsyn(final String urlStr)
        {
            new Thread()
            {
                public void run()
                {
                    try
                    {
                        String result = doGet(urlStr);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                };
            }.start();
        }

        /**
         * 异步的Post请求 
         * @param urlStr 
         * @param params 
         * @throws Exception
         */  
        public static void doPostAsyn(final String urlStr, final String params) throws Exception
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("发送请求");
                    doPost(urlStr, params);
                    System.out.println("111发送请求");
                }
            }).start();
        }


        /**
         * 异步的Post请求
         * @param urlStr
         * @param params
         * @param callBack
         * @throws Exception
         */
        public static void doPostAsynCallBack(final String urlStr, final String params,
                                      final CallBack callBack) throws Exception
        {
            new Thread()
            {
                public void run()
                {
                    try
                    {
                        String result = doPost(urlStr, params);
                        if (callBack != null)
                        {
                            callBack.onRequestComplete(result);
                        }
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                };
            }.start();

        }

        /** 
         * Get请求，获得返回数据 
         * @param urlStr
         * @return 
         * @throws Exception 
         */  
        public static String doGet(String urlStr)   
        {  
            URL url = null;  
            HttpURLConnection conn = null;  
            InputStream is = null;  
            ByteArrayOutputStream baos = null;  
            try  
            {  
                url = new URL(urlStr);  
                conn = (HttpURLConnection) url.openConnection();  
                conn.setReadTimeout(TIMEOUT_IN_MILLIONS);  
                conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);  
                conn.setRequestMethod("GET");  
                conn.setRequestProperty("accept", "*/*");
                conn.setRequestProperty("X-Bmob-REST-API-Key","8032a3407adfe5b02689f9cc3962fffe");
                conn.setRequestProperty("X-Bmob-REST-API-Key","043c532e6050fae1056acc1127741458");
                conn.setRequestProperty("connection", "Keep-Alive");
                conn.setRequestProperty("Content-Type", "application/json");
                if (conn.getResponseCode() == 200)  
                {  
                    is = conn.getInputStream();  
                    baos = new ByteArrayOutputStream();  
                    int len = -1;  
                    byte[] buf = new byte[128];  
      
                    while ((len = is.read(buf)) != -1)  
                    {  
                        baos.write(buf, 0, len);  
                    }  
                    baos.flush();  
                    return baos.toString();  
                } else  
                {  
                    throw new RuntimeException("发生错误");
                }  
      
            } catch (Exception e)  
            {  
                e.printStackTrace();  
            } finally  
            {  
                try  
                {  
                    if (is != null)  
                        is.close();  
                } catch (IOException e)  
                {  
                }  
                try  
                {  
                    if (baos != null)  
                        baos.close();  
                } catch (IOException e)  
                {  
                }  
                conn.disconnect();  
            }  
            return null ;
        }
      
        /**  
         * 向指定 URL 发送POST方法的请求  
         *   
         * @param url  
         *            发送请求的 URL  
         * @param param  
         *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。  
         * @return 所代表远程资源的响应结果  
         * @throws Exception  
         */  
        public static String doPost(String url, String param)   
        {  
            PrintWriter out = null;  
            BufferedReader in = null;  
            String result = "";  
            try  
            {  
                URL realUrl = new URL(url);  
                // 打开和URL之间的连接  
                HttpURLConnection conn = (HttpURLConnection) realUrl  
                        .openConnection();  
                // 设置通用的请求属性  
                conn.setRequestProperty("accept", "*/*");  
                conn.setRequestProperty("connection", "Keep-Alive");  
                conn.setRequestMethod("POST");  
                conn.setRequestProperty("Content-Type",  
                        "application/x-www-form-urlencoded");  
                conn.setRequestProperty("charset", "utf-8");  
                conn.setUseCaches(false);  
                // 发送POST请求必须设置如下两行  
                conn.setDoOutput(true);  
                conn.setDoInput(true);  
                conn.setReadTimeout(TIMEOUT_IN_MILLIONS);  
                conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);  
      
                if (param != null && !param.trim().equals(""))  
                {  
                    // 获取URLConnection对象对应的输出流  
                    out = new PrintWriter(conn.getOutputStream());  
                    // 发送请求参数  
                    out.print(param);  
                    // flush输出流的缓冲  
                    out.flush();  
                }  
                // 定义BufferedReader输入流来读取URL的响应  
                in = new BufferedReader(  
                        new InputStreamReader(conn.getInputStream()));  
                String line;  
                while ((line = in.readLine()) != null)  
                {  
                    result += line;  
                }  
            } catch (Exception e)  
            {  
                e.printStackTrace();  
            }  
            // 使用finally块来关闭输出流、输入流  
            finally  
            {  
                try  
                {  
                    if (out != null)  
                    {  
                        out.close();  
                    }  
                    if (in != null)  
                    {  
                        in.close();  
                    }  
                } catch (IOException ex)  
                {  
                    ex.printStackTrace();  
                }  
            }  
            return result;  
        }  
    }  