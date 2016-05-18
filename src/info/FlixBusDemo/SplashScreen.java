package info.FlixBusDemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import info.FlixBusDemo.api.TimeTableListener;
import info.FlixBusDemo.volley.VolleyError;
import info.flixbus.R;

/**
 * Created by omar.assi on 5/3/2016.
 */
public class SplashScreen extends Activity {
    private HttpEngine httpEngine;
    private MainActivity mainActivity = new MainActivity();
    public static Context context;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    /**
     * Called when the activity is first created.
     */
    Thread splashTread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        context = getApplicationContext();
        StartAnimations();
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
        l.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    parseResponse();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        splashTread.start();

    }

    private void parseResponse() {
        httpEngine = new HttpEngine(SplashScreen.this);
        httpEngine.retrieveTimeTable("1", new TimeTableListener() {

            @Override
            public void onSuccess(JSONObject response) {
                //mainActivity.alertDialog(ArriveFragment.this.getActivity());
                Intent intent = new Intent(SplashScreen.this,
                        MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                SplashScreen.this.finish();

                try {
                    DepartFragment.responseList = httpEngine.parseJsonArrivals("departures", response);
                    ArriveFragment.responseList = httpEngine.parseJsonArrivals("arrivals", response);

                } catch (JSONException e) {
                    e.printStackTrace();
                    mainActivity.alertDialog(SplashScreen.this, e.getMessage());
                }
                SplashScreen.this.finish();
            }

            @Override
            public void onError(VolleyError error) {
                mainActivity.alertDialog(SplashScreen.this, error.getMessage());
            }
        });
    }
}
