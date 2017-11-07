package Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import DBUtils.ProductHelper;

/**
 * Created by FDUSER on 11/5/2017.
 */

public class Product {
    public String title ;
    public String description ;
    public String price;
    public String quantitystatus ;
    public String rating ;
    public  String imgurl ;
    public List<String> tags = new ArrayList<String>();
    public List<String> comments ;


    public Product fetchProductFromDb()
    {
        Product product = new Product();
        String productJson = ProductHelper.getAllDocuments(ProductHelper.PRODUCTTABLENAME);
        List<Product> productList = ProductHelper.parseProductJson(productJson);
        for(Product pro : productList)
        {
            product.title = pro.title;
            product.description = pro.description;
        }



        return  product;
    }

}
