package xyz.tusion.vtb_hackathon;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Pair;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoScanFragment extends Fragment {

    List<Pair<String, Boolean>> list = new ArrayList<>(Arrays.asList(new Pair<>("Виктор Петрович", false)
            ,new Pair<>("Марина Николаевна", true)));

    public NoScanFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_no_scan, container, false);
        RecyclerView list_users = view.findViewById(R.id.list_users);
        list_users.setAdapter(new UserAdapter(getContext(), list));
        list_users.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        view.findViewById(R.id.frag_check_details_qr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.
                        findNavController(getActivity(), R.id.act_main_nav_host_fragment);
                navController.navigate(R.id.addUserDialog);
            }
        });
        return view;
    }

    public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ItemViewHolder> {
        private List<Pair<String, Boolean>> list;
        Context context;

        public UserAdapter(Context context, List<Pair<String, Boolean>> list){
            this.context = context;
            this.list = list;
        }

        @Override
        public UserAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_no_scan, parent, false);
            ItemViewHolder holder = new ItemViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            holder.text.setText(list.get(position).first);
            if(list.get(position).second)
                holder.fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.col_item_balance_on)));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ItemViewHolder extends RecyclerView.ViewHolder{
            TextView text;
            FloatingActionButton fab;

            public ItemViewHolder(View view) {
                super(view);
                text = (TextView) view.findViewById(R.id.text_user);
                fab = (FloatingActionButton) view.findViewById(R.id.check_pay);
            }
        }
    }

}
