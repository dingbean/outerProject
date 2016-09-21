import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2016/9/20.
 */
public class PostTest {

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public String sendPost(String url, String param) {
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
            out.print(param);
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

    @Test
    public void test1(){
        MultiThreadedHttpConnectionManager connectionManager =
                new MultiThreadedHttpConnectionManager();

        final HttpClient client = new HttpClient(connectionManager);
        ThreadTest tt = new ThreadTest(client);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i <50 ; i++) {
            executor.execute(tt);
        }
        try {
            Thread. sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        Thread t = new Thread(tt);
//        t.start();

        /*try {
            PostMethodTest pmt = new PostMethodTest();
            pmt.postMethod("http://localhost:8080/wechat/wx/controller");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //发送 POST 请求
//        String sr = sendPost("http://localhost:8081/wechat/wx/controller", null);
//        System.out.println(sr);
    }

    @Test
    public void test2(){
//        int size = keywordMap.size();

        // TODO Auto-generated method stub
        ExecutorService exec = Executors.newCachedThreadPool();
        // 50个线程可以同时访问
        final Semaphore semp = new Semaphore(4);
        // 模拟2000个客户端访问
        for (int index = 0; index < 4; index++) {
            final int NO = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // 获取许可
                        semp.acquire();
                        System.out.println("Thread:" + NO);
//                        String host = "http://192.168.56.1:8088/bjkw/admin_contractInfoSave.do?type=approveRi&";
//                        String para = "id=9084078";
//                        System.out.println(host + para);
                        String host = "http://localhost:8081/wechat/wx/controller";
                        URL url = new URL(host);// 此处填写供测试的url
                        HttpURLConnection connection = (HttpURLConnection) url
                                .openConnection();
                        connection.setRequestMethod("POST");
                        connection.setRequestProperty("Proxy-Connection",
                                "Keep-Alive");
                        connection.setDoOutput(true);
                        connection.setDoInput(true);

                        PrintWriter out = new PrintWriter(connection
                                .getOutputStream());
//                        out.print(para);
                        out.flush();
                        out.close();

                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(connection
                                        .getInputStream()));

                        String line = "";
                        String result = "";
                        while ((line = in.readLine()) != null) {
                            result += line;
                        }

                        // System.out.println(result);
                        // Thread.sleep((long) (Math.random()) * 1000);
                        // 释放
                        System.out.println("第：" + NO + " 个");
                        semp.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(run);
        }
        // 退出线程池
        exec.shutdown();
    }

}
