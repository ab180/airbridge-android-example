package co.ab180.abshop

import android.app.Application
import co.ab180.abshop.data.Constants
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class DefaultGlideApp : AppGlideModule()

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Constants.init(this)
    }
}