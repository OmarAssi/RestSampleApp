package info.FlixBusDemo;

import android.content.Context;
import android.net.ParseException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.InjectView;
import info.FlixBusDemo.api.BusModelData;
import info.flixbus.R;

/**
 * Created by omar.assi on 4/27/2016.
 */
public class CustomBusAdapter extends ArrayAdapter {
    @InjectView(R.id.textViewTo)
    TextView destinationTv;

    @InjectView(R.id.directionTv)
    TextView routeTv;

    @InjectView(R.id.routeTv)
    TextView directionTv;

    @InjectView(R.id.timingTv)
    TextView timingTv;


    private final Context context;
    private final List<BusModelData> values;
    String routeName;
    Date date;
    String timeStr;
    int i = 0;
    JSONObject obj1;

    public CustomBusAdapter(Context context, List<BusModelData> values, int i) {
        super(context,-1,values);
        this.context = context;
        this.values = values;
        this.i = i;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_view_detail, parent, false);

        BusModelData item = values.get(position);
        destinationTv = (TextView) rowView.findViewById(R.id.textViewTo);

        // Get Line Code
        routeTv = (TextView) rowView.findViewById(R.id.routeTv);
        routeTv.setText(item.getLineCode());

        // Get Route Name
        String routeStr = item.getBriefRoute();
        try {
            JSONArray jsonArrayRoute = new JSONArray(routeStr);
            if(i == 1){
                obj1 = jsonArrayRoute.getJSONObject(jsonArrayRoute.length()-1);
                destinationTv.setText("from");
            }else{
                obj1 = jsonArrayRoute.getJSONObject(0);
            }

            routeName = obj1.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        directionTv = (TextView) rowView.findViewById(R.id.directionTv);
        directionTv.setText(routeName);

        // Get time
        timingTv = (TextView) rowView.findViewById(R.id.timingTv);
        String timingConvertStr = item.getDateTime();
        // Convert string to jsonObject
        try {
            JSONObject jsonArrayRoute = new JSONObject(timingConvertStr);
            String timeStampStr = jsonArrayRoute.getString("timestamp");
            String timeZoneStr = jsonArrayRoute.getString("tz");
            convertTime(timeStampStr, timeZoneStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        timingTv.setText(timeStr);
        return rowView;
    }
    public String convertTime(String timeStampStr, String timeZoneStr){

        TimeZone timeZone =  TimeZone.getTimeZone(timeZoneStr);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        sdf.setTimeZone(timeZone);//set time zone.
        String localTime = sdf.format(new Date(Long.parseLong(timeStampStr)*1000));
        SimpleDateFormat formatter_to = new SimpleDateFormat("hh:mm");
        date = new Date();

            try {
                date = sdf.parse(localTime);//get local date
                timeStr = formatter_to.format(date);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return timeStr;

    }

}
