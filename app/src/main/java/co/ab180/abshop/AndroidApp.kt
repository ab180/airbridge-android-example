package co.ab180.abshop

import android.app.Application
import co.ab180.abshop.data.Constants
import co.ab180.airbridge.Airbridge
import co.ab180.airbridge.AirbridgeConfig
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class DefaultGlideApp : AppGlideModule()

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Constants.init(this)
        initAirbridge()
    }

    fun initAirbridge() {
        val builder = AirbridgeConfig.Builder("APP_NAME", "APP_TOKEN")
        Airbridge.init(this, builder.build())
    }
}