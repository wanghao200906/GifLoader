package example.com.gifloader.core;

import android.content.Context;
import android.os.AsyncTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wanghao on 16/5/23.
 */
class GifLoaderTask extends AsyncTask<String, Void, String> {
    private final Context context;
    private String name;

    private GifDrawer gifDrawer;

    public GifLoaderTask(GifDrawer gifDrawer, Context context) {
        this.gifDrawer = gifDrawer;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            System.out.println(params[0]);
            name = Md5Utils.getMD5(params[0]);
            System.out.println("--" + name);

            InputStream is = HttpLoader.getInputStreanFormUrl(params[0]);
            String path = context.getExternalCacheDir() + File.separator + name;
            File file = new File(path);

            FileOutputStream fops = new FileOutputStream(file);

            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                fops.write(buffer, 0, len);
            }
            fops.close();
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return params[0];
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            GifDecoder.with(context).load(s).into(gifDrawer.getImageView());
        }
    }
}