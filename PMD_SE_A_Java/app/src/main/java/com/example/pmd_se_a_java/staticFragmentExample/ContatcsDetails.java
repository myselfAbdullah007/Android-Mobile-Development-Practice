package com.example.pmd_se_a_java.staticFragmentExample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pmd_se_a_java.R;

public class ContatcsDetails extends Fragment{
    TextView textview;
    int arrayLength;
    int currentIndex = -1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contactdetails, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textview = getActivity().findViewById(R.id.contactdetailtextview);
        arrayLength = MyContactsMainActivity.contactsArray.length;
    }
    public int getshowIndex(){
        return currentIndex;
    }

    public void ContactIndex(int currentIndex){
        if(currentIndex < 0 || currentIndex >= arrayLength){
            return;
        }
       this.currentIndex = currentIndex;
        textview.setText(MyContactsMainActivity.contactDetails[currentIndex]);
    }
}
