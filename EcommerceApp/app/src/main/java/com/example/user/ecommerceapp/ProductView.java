package com.example.user.ecommerceapp;

import android.media.Rating;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Model.Product;

public class ProductView extends AppCompatActivity {

    TextView description ;
    TextView title ;
    //Todo: onclick lisnter : intent ViewCommentListActivity
    //Todo : Create Activity ViewCommentListActivity
    Button viewComments ;
    Button addComments;
    EditText addCommentsEditText;
    Rating rating;
    Product productDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        description =(TextView) findViewById(R.id.productDesc);
        addCommentsEditText = (EditText)findViewById(R.id.addcommentsedittext);
        viewComments = (Button)findViewById(R.id.viewcommentsbutton);
        addComments = (Button)findViewById(R.id.addcommentbtn);
        title = (TextView)findViewById(R.id.producttitle);

//        product = new Product();
//        product.fetchProductFromDb("something");
//        product.title = "Motorola Moto C XT1755 (Starry Black)";
//        product.description = "VoLTE Model, Android 7.0 (Nougat)\n" +
//                "Display 5.0 inches 480x854 pixels\n" +
//                "Mediatek MT6737M Quad-core 1.1 GHz Cortex-A53\n" +
//                "Dual SIM (Micro-SIM, dual stand-by)\n" +
//                "1 Year Manufacture Warranty\n" +
//                "Removable Li-Po 2350 mAh battery";
//        title.setText(product.title.toString());
//        description.setText(product.description);

         new fetchProduct().execute();




    }

    class fetchProduct extends AsyncTask<String,Void,Product>{
        @Override
        protected void onPostExecute(Product product) {
            super.onPostExecute(product);
            if(product!=null)
            {
                title.setText(product.title.toString());
                description.setText(product.description);
            }

        }

        @Override
        protected Product doInBackground(String... params) {
            String check = "";
            productDb = new Product();
            return productDb.fetchProductFromDb();

        }
    }




    }

