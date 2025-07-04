package co.ab180.exabr

import android.app.Application
import co.ab180.airbridge.Airbridge
import co.ab180.airbridge.AirbridgeLogLevel
import co.ab180.airbridge.AirbridgeOptionBuilder

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // For proper operation, please make sure that the init method is
        // called at the time of onCreate of the Application class.
        initAirbridge()
    }

    private fun initAirbridge() {
        val option = AirbridgeOptionBuilder("exabr", "c3b61a44d8f74811b2f63857cfcd3a7f")
            // Check in LogCat (Default log level is AirbridgeLogLevel.WARNING)
            .setLogLevel(AirbridgeLogLevel.DEBUG)
            .build()
        Airbridge.initializeSDK(this, option)
    }
}