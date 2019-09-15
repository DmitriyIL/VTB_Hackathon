package xyz.tusion.vtb_hackathon;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ScanChoiseDialog extends Fragment {

    public ScanChoiseDialog() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_scan, container, false);

        ((EditText)view.findViewById(R.id.summa_scan)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                setSumma(editable.toString());
            }
        });

        return view;
    }

    public void setSumma(String summa) {
        ((CreateRoomDialog)getParentFragment()).setSumma(summa);
    }

}
