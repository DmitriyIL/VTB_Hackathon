package xyz.tusion.vtb_hackathon;


import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


@SuppressLint("ValidFragment")
public class SearchDialog extends DialogFragment {

    public SearchDialog() {}

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.dialog_search, container, false);
        view.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.dialog_search_qr).setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }

    public void  Dismiss() {
        getDialog().dismiss();
    }
}

