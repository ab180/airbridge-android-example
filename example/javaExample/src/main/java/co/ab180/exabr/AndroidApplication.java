package co.ab180.exabr;

import android.app.Application;
import android.util.Log;

import co.ab180.airbridge.Airbridge;
import co.ab180.airbridge.AirbridgeConfig;

public class AndroidApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // For proper operation, please make sure that the init method is
        // called at the time of onCreate of the Application class.
        initAirbridge();
    }

    private void initAirbridge() {
        AirbridgeConfig config = new AirbridgeConfig.Builder(BuildConfig.APP_NAME, BuildConfig.APP_SDK_TOKEN)
                // Check in LogCat (Default log level is Log.INFO)
                .setLogLevel(Log.VERBOSE)
                // Deferred Deep Link Callback Setup
                .setOnDeferredDeeplinkReceiveListener(uri -> {
                    // If you want to open the target activity, please return true otherwise false.
                    // Default returning value is true
                    return false;
                })
                .build();

        Airbridge.init(this, config);
    }
}
