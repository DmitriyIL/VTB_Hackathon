package xyz.tusion.vtb_hackathon;


import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


@SuppressLint("ValidFragment")
public class SearchDialog extends DialogFragment {

    public SearchDialog() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.dialog_search, container, false);
        view.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        return view;
    }

    public void  Dismiss() {
        getDialog().dismiss();
    }
}

