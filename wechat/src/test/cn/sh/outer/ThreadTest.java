import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/20.
 */
public class ThreadTest implements Runnable {

    private HttpClient client;
    public ThreadTest(HttpClient client){
        this.client = client;
    }

    /*public void postMethod(String url) throws IOException{
        PostMethod post = new PostMethod(url);
        try {
            int statusCode = client.executeMethod(post);
            System. out.println( "_______====statusCode=" + statusCode );
            post.releaseConnection();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void run() {

        //发送 POST 请求
//        for (int i = 0; i < 100; i++) {
            System.out.println("===========");
            try {
                int statusCode=0;
                System. out.println( "url====http://localhost:8080/wechat/wx/controller"  );
                PostMethod postMethod = new PostMethod("http://localhost:8080/wechat/wx/controller");

                    statusCode = client.executeMethod(postMethod);
                    System. out.println( "_______sleep" + statusCode );
//                    Thread. sleep(3000);//10s
                    postMethod.releaseConnection();

//                PostMethodTest pmt = new PostMethodTest();
//                pmt.postMethod("http://localhost:8080/wechat/wx/controller");
            } catch (Exception e) {
                e.printStackTrace();
            }
        System.out.println("+++++++++++");

//        }
    }
}
