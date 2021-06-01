package workshop.g_s.simplechat.ui

import android.app.Application
import timber.log.Timber

/**
 * Created by Gal Shimron on 01/06/2021.
 */
class SimpleChatApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}