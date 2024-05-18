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
public class Fragment4 extends Fragment {
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment4, container, false);
        imageView = view.findViewById(R.id.imageView8);
        return view;
    }
    public void setImage(Bitmap bitmap){
        imageView.setImageBitmap(bitmap);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                Drawable drawable = imageView.getDrawable();

                if(drawable.getIntrinsicHeight()!=-1){

                    imageView.setImageBitmap(null);

                }
                else{

                    imageView.setImageBitmap(bitmap);

                }



            }

        });

    }
}
