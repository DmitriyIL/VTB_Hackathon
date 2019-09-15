package xyz.tusion.vtb_hackathon;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.fragment.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


@SuppressLint("ValidFragment")
public class CreateRoomDialog extends DialogFragment {

    public String nameRoom = "";
    public String summa = "";
    public int numberUsers = 0;


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
        ((EditText)view.findViewById(R.id.name_room)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                setNameRoom(editable.toString());
            }
        });

        return view;
    }

    public void  Dismiss() {
        getDialog().dismiss();
    }

    public void showFragment(final Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public int getNumberUsers() {
        return numberUsers;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public String getSumma() {
        return summa;
    }

    public void setSumma(String summa) {
        this.summa = summa;
    }

    public void setNumberUsers(int numberUsers) {
        this.numberUsers = numberUsers;
    }
}
