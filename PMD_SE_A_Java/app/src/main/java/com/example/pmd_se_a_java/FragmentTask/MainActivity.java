package com.example.pmd_se_a_java.FragmentTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pmd_se_a_java.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    Fragment1 fragment1 = new Fragment1();
    Fragment2 fragment2 = new Fragment2();
    Fragment3 fragment3 = new Fragment3();
    Fragment4 fragment4 = new Fragment4();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bindFrags();
    }

    public void bindFrags() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(R.id.fragmentContainerView7, fragment1);
        transaction.add(R.id.fragmentContainerView9, fragment2);
        transaction.add(R.id.fragmentContainerView10, fragment3);
        transaction.add(R.id.fragmentContainerView11, fragment4);

        transaction.commit();
    }

    class ImageDownload extends AsyncTask<String, Void, Bitmap[]> {

        @Override
        protected Bitmap[] doInBackground(String... strings) {
            Bitmap[] bitmaps = new Bitmap[4];
            try {
                for (int i = 0; i < 4; i++) {
                    URL url = new URL(strings[i]);
                    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                    connection.connect();
                    InputStream inputStream = connection.getInputStream();
                    bitmaps[i] = BitmapFactory.decodeStream(inputStream);
                }
                return bitmaps;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap[] bitmaps) {
            if (bitmaps != null) {
                // Set images to fragments here
                fragment1.setImage(bitmaps[0]);
                fragment2.setImage(bitmaps[1]);
                fragment3.setImage(bitmaps[2]);
                fragment4.setImage(bitmaps[3]);
            }
        }
    }

    public void DownloadImages(View view) {
        ImageDownload obj = new ImageDownload();
        obj.execute(
                "https://picsum.photos/200",
                "https://picsum.photos/200",
                "https://picsum.photos/200",
                "https://picsum.photos/200"
        );
    }
}
