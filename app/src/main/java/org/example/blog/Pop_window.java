package org.example.blog;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Alan Beas on 11/13/2016.
 */

public class Pop_window extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detailed_post);

        Bundle bun = this.getIntent().getExtras();
        ((TextView)findViewById(R.id.post_username)).setText(bun.getString("username"));
        ((TextView)findViewById(R.id.post_departure)).setText(bun.getString("start"));
        ((TextView)findViewById(R.id.post_destination)).setText(bun.getString("dest"));
        ((TextView)findViewById(R.id.post_price)).setText(bun.getString("price"));
        ((TextView)findViewById(R.id.post_additional)).setText(bun.getString("addit"));

        String temp = stringMonth(bun.getInt("month")) +" "+ bun.getInt("day")+", "+bun.getInt("year");

        ((TextView)findViewById(R.id.post_date)).setText(temp);


        /*DisplayMetrics ms = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(ms);

        int width = ms.widthPixels;
        int height = ms.heightPixels;

        getWindow().setLayout( (int)(width*.8) , (int)(height*.6) );*/
    }

    public void goBack(View view){
        super.onBackPressed();
    }

    public String stringMonth(int m){
        switch (m){
            case 1:
                return "Jan";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Oct";
            case 9:
                return "Aug";
            case 10:
                return "Sep";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
        }
        return "";
    }
}
