package xyz.tusion.vtb_hackathon;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NoScanChoiseDialog extends Fragment {

    public NoScanChoiseDialog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.content_no_scan, container, false);

        ((EditText)view.findViewById(R.id.summa_room)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                setSumma(editable.toString());
            }
        });

        ((EditText)view.findViewById(R.id.number_users)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                setNumberUsers(Integer.valueOf(editable.toString()));
            }
        });

        return view;
    }

    public void setSumma(String summa) {
        ((CreateRoomDialog)getParentFragment()).setSumma(summa);
    }

    public void setNumberUsers(int numberUsers) {
        ((CreateRoomDialog)getParentFragment()).setNumberUsers(numberUsers);
    }

}
