import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
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

    public void postMethod(String url) throws IOException{
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(url);
        try {
            client.executeMethod(post);
            post.releaseConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void run() {

        //发送 POST 请求
//        for (int i = 0; i < 100; i++) {
            System.out.println("===========");
            try {
                postMethod("http://localhost:8081/wechat/wx/controller");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("+++++++++++");

//        }
    }
}
