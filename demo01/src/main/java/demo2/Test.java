package demo2;

/**
 * @author kismet
 * @date 2019-11-11 15:16
 */
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    private static volatile int number = 0;
    private static final String url ="https://blog.csdn.net/weixin_44036154";

    /**
     * @param args
     */
    public static void main(String[] args) {
        //简单的用一个线程池
        ExecutorService es = Executors.newFixedThreadPool(30);
        //线程的个数应该根据自家的网络来定.我家是10M网所以20个基本上就是上限了.
        for(int i=0;i<20;i++){
            Runnable r = new Runnable() {
                public void run() {
                    refreshBlog();
                }
            };
            es.submit(r);
        }
    }

    /**
     * refreshBlog 这个方法是相应请求
     * @author heinz_ho
     * @return void
     */
    public static  void refreshBlog(){
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        getMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.99 Safari/537.36 LBBROWSER");
        //其实这里我也不太懂.我只是知道cookie的生成策略,但是因为 csdn会判断力过来的cookie是啥.必须加.
        getMethod.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        //偷懒就用了一个死循环
        while(true) {
            try {
                //这句话会造成阻塞因为想网络发起请求.
                int statusCode = httpClient.executeMethod(getMethod);
                if (statusCode != HttpStatus.SC_OK) {
                    System.out.print("失败：" + getMethod.getStatusLine());
                }
                System.out.println("你已经刷新了:"+number++);
            } catch (Exception e) {
                System.out.print("请检查网络地址！");
            } finally {
                getMethod.releaseConnection();
            }
        }
    }

}
