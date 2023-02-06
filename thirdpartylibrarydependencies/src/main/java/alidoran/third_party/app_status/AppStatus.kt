package alidoran.third_party.app_status

import android.content.Context
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class DefaultLifecycleObserver(val context: Context) : DefaultLifecycleObserver {

    override fun onStart(owner: LifecycleOwner) {
        super.onResume(owner)
        AppStatusHelp(context).setIsAppInBackground(false)
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        AppStatusHelp(context).setIsAppInBackground(true)
    }
}