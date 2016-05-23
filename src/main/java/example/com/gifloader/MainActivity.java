package example.com.gifloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import example.com.gifloader.core.GifDecoder;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    public static String url = "http://image.haha.mx/2014/02/02/middle/1115779_c221d1fc47b97bb1605cddc9c8aec0a7_1391347675.gif";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.iv);
        try {
            GifDecoder.with(this).load(url).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}