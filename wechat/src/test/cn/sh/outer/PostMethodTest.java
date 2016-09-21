import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

/**
 * Created by HP on 2016/9/20.
 */
public class PostMethodTest {
    public void postMethod(final String url) throws IOException {
        MultiThreadedHttpConnectionManager connectionManager =
                new MultiThreadedHttpConnectionManager();

        final HttpClient client = new HttpClient(connectionManager);

        /*Runnable r = new Runnable(){
            public  void run(){
                int statusCode=0;
                System. out.println( "url====" + url );
                PostMethod postMethod = new PostMethod(url);
                try {
                    statusCode = client.executeMethod(postMethod);
                    System. out.println( "_______sleep" + statusCode );
//                    Thread. sleep(3000);//10s
                    postMethod.releaseConnection();
                } catch (HttpException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
        };*/

        ThreadTest r = new ThreadTest(client);

        for (int i = 0; i < 10; i++) {
            new Thread(r).start();

        }
        try {
            Thread. sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        /*PostMethod post = new PostMethod(url);
        try {
            client.executeMethod(post);
            post.releaseConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
