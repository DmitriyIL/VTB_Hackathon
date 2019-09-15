package xyz.tusion.vtb_hackathon;


import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


@SuppressLint("ValidFragment")
public class CreateRoomDialog extends DialogFragment {

    public CreateRoomDialog() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.dialog_create_room, container, false);
        view.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        showFragment(new BaseFragment());
        return view;
    }

    public void  Dismiss() {
        getDialog().dismiss();
    }

    public void showFragment(final Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

}
