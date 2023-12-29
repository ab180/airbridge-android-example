package co.ab180.exabr

import android.app.Application
import android.util.Log
import co.ab180.airbridge.Airbridge
import co.ab180.airbridge.AirbridgeConfig

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // For proper operation, please make sure that the init method is
        // called at the time of onCreate of the Application class.
        initAirbridge()
    }

    private fun initAirbridge() {
        val config = AirbridgeConfig.Builder(BuildConfig.APP_NAME, BuildConfig.APP_SDK_TOKEN)
            // Check in LogCat (Default log level is Log.INFO)
            .setLogLevel(Log.VERBOSE)
            // Deferred Deep Link Callback Setup
            .setOnDeferredDeeplinkReceiveListener { // shouldLaunchReceivedDeferredDeeplink(uri: Uri): Boolean

                // If you want to open the target activity, please return true otherwise false.
                // Default returning value is true
                true
            }
            .build()
        Airbridge.init(this, config)
    }
}