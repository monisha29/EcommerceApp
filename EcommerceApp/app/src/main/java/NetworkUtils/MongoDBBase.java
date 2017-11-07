package NetworkUtils;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import DBUtils.Credentials;

/**
 * Created by FDUSER on 11/5/2017.
 */

public class MongoDBBase {

    public static String getAllDocuments(String collection, Context ctx)
    {
        String op = "failed";
        try {
            String urlString = Credentials.getAdressString(collection);
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            if(urlConnection.getResponseCode()==200)
            {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                String line ;
                StringBuilder sb = new StringBuilder();
                while((line=r.readLine())!=null)
                    sb.append(line);
                op = sb.toString();
                urlConnection.disconnect();
            }
            else
            {
                Toast.makeText(ctx,"Something went wrong",Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return op;
    }


}
