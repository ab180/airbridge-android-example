package com.example.airbridgesampleapp
import android.app.Application
import co.ab180.airbridge.Airbridge
import co.ab180.airbridge.AirbridgeOptionBuilder
import co.ab180.airbridge.AirbridgeLogLevel

// This is a custom Application class that serves as the entry point of the app.
class AndroidApplication : Application() {
    // Called when the application is first created.
    override fun onCreate() {
        super.onCreate()
        // YOUR_APP_NAME: input your app name
        // YOUR_APP_SDK_TOKEN: input your app token
        //val option = AirbridgeOptionBuilder("YOUR_APP_NAME", "YOUR_APP_SDK_TOKEN").build()
        val option = AirbridgeOptionBuilder("YOUR_APP_NAME", "YOUR_APP_SDK_TOKEN")
            .setLogLevel(AirbridgeLogLevel.DEBUG)
            .build()
        Airbridge.initializeSDK(this, option)

    }
}