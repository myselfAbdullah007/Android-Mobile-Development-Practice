package com.example.pmd_se_a_java.FragmentTask;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pmd_se_a_java.R;

public class Fragment1  extends Fragment {
    ImageView imageView1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.activity_fragment1,container,false);
        imageView1 = mainView.findViewById(R.id.imageView5);
        return mainView;
    }

    public void setImage(Bitmap bitmap){
        imageView1.setImageBitmap(bitmap);

        imageView1.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Drawable drawable = imageView1.getDrawable();



                if(drawable.getIntrinsicHeight()!=-1){

                    imageView1.setImageBitmap(null);

                }

                else{

                    imageView1.setImageBitmap(bitmap);

                }

            }

        });

    }
}
