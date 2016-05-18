package info.FlixBusDemo;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import info.FlixBusDemo.api.BusModelData;
import info.FlixBusDemo.api.TimeTableListener;
import info.FlixBusDemo.volley.AuthFailureError;
import info.FlixBusDemo.volley.RequestQueue;
import info.FlixBusDemo.volley.Response;
import info.FlixBusDemo.volley.VolleyError;
import info.FlixBusDemo.volley.toolbox.HurlStack;
import info.FlixBusDemo.volley.toolbox.JsonObjectRequest;
import info.FlixBusDemo.volley.toolbox.Volley;

/**
 * Created by omar.assi on 4/23/2016.
 */
public final class HttpEngine {
    String baseUrl = "https://api.mobile.staging.mfb.io";
    private RequestQueue requestQueue;
    public HttpEngine(Context context) {
        acceptAllCerts();
        requestQueue = Volley.newRequestQueue(context, new HurlStack(null, null));
    }


    private String createUrl(String endPoint) {
        return baseUrl + endPoint;
    }


    public void retrieveTimeTable(String stationID, final TimeTableListener listener) {

        String endPoint = "/mobile/v1/network/station/"+stationID+"/timetable";

        JsonObjectRequest request = new JsonObjectRequest(createUrl(endPoint), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // List<BusModelData> retVal = parseJsonArrivals(response);
                listener.onSuccess(response);

            }

          }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("X-Api-Authentication", "intervIEW_TOK3n");
                return params;
            }
        };
        Volley.newRequestQueue(SplashScreen.context).add(request);
    }

    public static void acceptAllCerts() {
        TrustManager[] trustAllCerts;
        SSLContext sc = null;
        try {
            trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[0];
                        }
                    }
            };

            sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
    }
    public List<BusModelData> parseJsonArrivals(String destinationStr, JSONObject array) throws JSONException {
        JSONObject sys = array.getJSONObject("timetable");
        JSONArray arrivals = sys.optJSONArray(destinationStr);
        List<BusModelData> retVal = new ArrayList<BusModelData>();
        for (int i = 0; i < arrivals.length(); i++) {
            JSONObject jsonPartnerConfig = arrivals.optJSONObject(i);
            BusModelData busModelData = new BusModelData();
            busModelData.setStations(jsonPartnerConfig.optString("through_the_stations"));
            busModelData.setDateTime(jsonPartnerConfig.optString("datetime"));
            busModelData.setLineDirection(jsonPartnerConfig.optString("line_direction"));
            busModelData.setBriefRoute(jsonPartnerConfig.optString("route"));
            busModelData.setCoordinates(jsonPartnerConfig.optString("coordinates"));
            busModelData.setLineCode(jsonPartnerConfig.optString("line_code"));
            busModelData.setDelayStatus(jsonPartnerConfig.optString("delay_status"));
            busModelData.setDelayTime(jsonPartnerConfig.optString("delay"));
            retVal.add(busModelData);
        }
        return retVal;
    }
}