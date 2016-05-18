package info.FlixBusDemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.InjectView;
import info.FlixBusDemo.api.BusModelData;
import info.flixbus.R;

public class DepartFragment extends Fragment {

    @InjectView(R.id.listview)
    ListView saveListView;

    @InjectView(R.id.currentTimeTv)
    TextView currentTimeTV;


    public static List<BusModelData> responseList;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CustomBusAdapter customBusAdapter = new CustomBusAdapter(DepartFragment.this.getActivity(), responseList, 1);
        saveListView.setAdapter(customBusAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_arrival, container, false);
        saveListView = (ListView) rootView.findViewById(R.id.listview);
        currentTimeTV = (TextView) rootView.findViewById(R.id.currentTimeTv);
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
        String currentDateandTime = sdf.format(new Date());
        currentTimeTV.setText(currentDateandTime.toString().toUpperCase());
        return rootView;
    }
}
