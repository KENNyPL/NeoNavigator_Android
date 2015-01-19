package pl.cydo.util;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by root on 19.01.15.
 */
public class RestRepository {

    public static String callWebService(String serviceEndPoint) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet request = new HttpGet(serviceEndPoint);
        //add the parameters to your request
//        request.addHeader("paramname", deviceId);
        String result = null;
        ResponseHandler<String> handler = new BasicResponseHandler();
        try {
            result = httpclient.execute(request, handler);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        httpclient.getConnectionManager().shutdown();


        System.out.println("Response: " + result);
        return result;


    }
}
