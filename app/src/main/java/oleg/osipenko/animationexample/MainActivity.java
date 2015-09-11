package oleg.osipenko.animationexample;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView anim = (ImageView) findViewById(R.id.animation);
        Button btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Animatable) anim.getBackground()).start();
            }
        });
        String soft = "Soft";
        String media = "Media";

        ParseQuery<OlegTest> querySoft1 = ParseQuery.getQuery(OlegTest.class);
        querySoft1.whereContains("skills", soft);
        ParseQuery<OlegTest> querySoft2 = ParseQuery.getQuery(OlegTest.class);
        querySoft2.whereContains("skills", soft.toLowerCase());
        ParseQuery<OlegTest> querySoft3 = ParseQuery.getQuery(OlegTest.class);
        querySoft3.whereContains("skills", soft.toUpperCase());
        ParseQuery<OlegTest> querySoft = ParseQuery.or(Arrays.asList(querySoft1, querySoft2, querySoft3));
        String pattern = "^.*" + soft + ".*$";
        ParseQuery<OlegTest> queryreg = ParseQuery.getQuery(OlegTest.class);
        queryreg.whereMatches("skills", pattern, "i");
        queryreg.findInBackground(new FindCallback<OlegTest>() {
            @Override
            public void done(List<OlegTest> list, ParseException e) {
                if (e != null) {
                    Log.e(MainActivity.class.getName(), e.getMessage());
                    return;
                } else {
                    Log.d(MainActivity.class.getName(), "reg soft found " + list.size() + "objects");
                }
            }
        });

        querySoft.findInBackground(new FindCallback<OlegTest>() {
            @Override
            public void done(List<OlegTest> list, ParseException e) {
                if (e != null) {
                    Log.e(MainActivity.class.getName(), e.getMessage());
                    return;
                } else {
                    Log.d(MainActivity.class.getName(), "soft found " + list.size() + "objects");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
