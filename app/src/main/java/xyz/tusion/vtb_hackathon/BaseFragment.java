package xyz.tusion.vtb_hackathon;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

    private View rootView;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_base, container, false);
        rootView.findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((CreateRoomDialog)getParentFragment()).showFragment(new ScanChoiseDialog());
            }
        });
        rootView.findViewById(R.id.btn_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((CreateRoomDialog)getParentFragment()).showFragment(new NoScanChoiseDialog());
            }
        });
        return rootView;
    }

}
