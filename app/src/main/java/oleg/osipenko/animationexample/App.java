package oleg.osipenko.animationexample;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by olegosipenko on 30.07.15.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(OlegTest.class);

        Parse.initialize(this, "rVpjsr245kbCgKD6Z0r6VhNiIVvL2AMVSq3Q0cHl", "3z9FI0hfwlPvsl2r7vbyH9tdbjTIPBXHgqxH2zTQ");
    }
}
