package info.FlixBusDemo.api;

import org.json.JSONObject;

import info.FlixBusDemo.volley.VolleyError;

/**
 * Created by omar.assi on 4/24/2016.
 * The TimeTableListener delivers the result of the
 * {@link info.androidhive.FlixBusDemo.HttpEngine#retrieveTimeTable(String, TimeTableListener)}method.
 */
public interface TimeTableListener {

    /**
     * The Time Table was retrieved successfully.
     * @param response The generated TimeTable.
     */
    void onSuccess(JSONObject response);

    /**
     * The Time Table generation failed.
     * @param error The occurred error.
     */
    void onError(VolleyError error);
}
