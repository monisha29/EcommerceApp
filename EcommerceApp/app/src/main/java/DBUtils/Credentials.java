package DBUtils;

/**
 * Created by FDUSER on 11/5/2017.
 */

public class Credentials {
    private static String DB_NAME = "ecommerceapplication";
    public static String API_KEY = "48BIPLlX6BZCHsJdwF_3VFWwMuC2IF5r";
    public static  String base_url = "https://api.mlab.com/api/1/databases/ecommerceapplication";

    public static String getAdressString(String collection_name)
    {
        String url = String.format(base_url+"/collections/%s",collection_name);
        StringBuilder stringBuilder = new StringBuilder(url);
        stringBuilder.append("/?apiKey="+API_KEY);
        return stringBuilder.toString();
    }

    public static String getAdressString(String collection_name,String document_id)
    {
        String url = String.format(base_url+"/collections/%s/%s",collection_name,document_id);
        StringBuilder stringBuilder = new StringBuilder(url);
        stringBuilder.append("/?apiKey="+API_KEY);
        return stringBuilder.toString();
    }

}
