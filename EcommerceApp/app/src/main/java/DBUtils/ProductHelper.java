package DBUtils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import Model.Comment;
import Model.Product;

/**
 * Created by FDUSER on 11/5/2017.
 */

public class ProductHelper {
    public  static String PRODUCTTABLENAME = "product";
    public static String ID ="_id" ;
    public static String  PRODUCTTITLE= "producttitle";
    public static String  PRODCTDESC= "productdescription";
    public static String  PRODUCTPRICE= "productprice";
    public static String PRODUCTQUANTITYSTATUS  = "productquantitystatus";
    public static String PRODUCTRATING = "productrating";
    public static String PRODUCTIMGURL = "productimgurl";
    public static String PRODUCTTAGS = "productimgurl";
    public static String PRODUCTCOMMENTS = "comments";




    public static JSONObject generateProductJson(Product product)
    {
        JSONObject document = new JSONObject();
        try {
            document.put(PRODUCTTITLE, product.title);
            document.put(PRODCTDESC,product.description);
            document.put(PRODUCTIMGURL,product.imgurl);
         //   document.put(PRODUCTCOMMENTS,product.comments);

            return document;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return document;
    }

    public static List<Product> parseProductJson(String s)
    {
        List<Product> productList = new ArrayList<Product>();
        try {
            JSONArray productDocuments = new JSONArray(s);
            for (int i = 0; i < productDocuments.length(); i++) {
                JSONObject document = new JSONObject();
                Product product = new Product();
                document = productDocuments.getJSONObject(i);
                product.title = document.get(PRODUCTTITLE).toString();
                product.description = document.get(PRODCTDESC).toString();
                String comments  =  document.get(PRODUCTCOMMENTS).toString();
                JSONArray commentDocuments = new JSONArray(s);


                productList.add(product);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return  productList;
    }

    public static String getAllDocuments(String collection)
    {
        String op = "";
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
                op = "failed";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return op;
    }

    public static JSONObject updateCommentsJson(Comment comment)
    {
        JSONArray jsonArray = new JSONArray();
        JSONObject json = new JSONObject();
        try
        {

            json.put("commentid",comment.id);
            json.put("commentbody",comment.body);
            json.put("commentsentiment",comment.sentiment);
            json.put("status","not processed");
            json.put("userid",1);
            

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return json;
    }

    public static String updateDocument(String collection,String documentId,Product product)
    {
        documentId = "59ff2f1c734d1d63bdd27beb";
        String op = "";
        try {
            JSONObject document = generateProductJson(product) ;
            String urlString = Credentials.getAdressString(collection,documentId);
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type","application/json");
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("Method","PUT");
            OutputStream os = urlConnection.getOutputStream();
            os.write(document.toString().getBytes("UTF-8"));
            os.close();
            StringBuilder sb = new StringBuilder();
            int HttpResult = urlConnection.getResponseCode();
            if(HttpResult == HttpURLConnection.HTTP_OK)
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"utf-8"));

                String line = null;
                while((line = br.readLine())!= null)
                {
                    sb.append(line + "\n");
                }
                br.close();
                Log.e("what happened " , sb.toString());
                op="Success";
            }
            else
            {
                System.out.println("" + urlConnection.getResponseCode());
                System.out.println(urlConnection.getResponseMessage());
            }
            urlConnection.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return op;
    }

}
