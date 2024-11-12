package alidoran.android.network

import android.app.Application
import android.content.Context
import android.os.Build
import android.telephony.PhoneStateListener
import android.telephony.TelephonyCallback
import android.telephony.TelephonyDisplayInfo
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import java.util.concurrent.Executors

//Thanks to tdcolvin

class Detector5GViewModel(application: Application): AndroidViewModel(application) {
    @RequiresApi(Build.VERSION_CODES.R)
    val telephonyType = callbackFlow {
        val telephonyManager = application.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        // The thread Executor used to run the listener. This governs how threads are created and
        // reused. Here we use a single thread.
        val exec = Executors.newSingleThreadExecutor()

        if (Build.VERSION.SDK_INT >= 31) {
            // SDK >= 31 uses TelephonyManager.registerTelephonyCallback() to listen for
            // TelephonyDisplayInfo changes.
            // It does not require any permissions.

            val callback = object : TelephonyCallback(), TelephonyCallback.DisplayInfoListener {
                override fun onDisplayInfoChanged(telephonyDisplayInfo: TelephonyDisplayInfo) {
                    trySend(networkType(telephonyDisplayInfo))
                }
            }
            telephonyManager.registerTelephonyCallback(exec, callback)

            awaitClose {
                telephonyManager.unregisterTelephonyCallback(callback)
                exec.shutdown()
            }
        }
        else @Suppress("OVERRIDE_DEPRECATION", "DEPRECATION") {
            // SDK 30 uses TelephonyManager.listen() to listen for TelephonyDisplayInfo changes.
            // It requires READ_PHONE_STATE permission.

            val callback =
            object : PhoneStateListener(exec) {
                override fun onDisplayInfoChanged(telephonyDisplayInfo: TelephonyDisplayInfo) {
                    trySend(networkType(telephonyDisplayInfo))
                }
            }
            telephonyManager.listen(callback, PhoneStateListener.LISTEN_DISPLAY_INFO_CHANGED)

            awaitClose {
                telephonyManager.listen(callback, 0)
                exec.shutdown()
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(500), null)


    @RequiresApi(Build.VERSION_CODES.R)
    fun networkType(telephony: TelephonyDisplayInfo?): String {
        val baseTypeString = when (telephony?.networkType) {
            TelephonyManager.NETWORK_TYPE_CDMA -> "CDMA"
            TelephonyManager.NETWORK_TYPE_1xRTT -> "1xRTT"
            TelephonyManager.NETWORK_TYPE_EDGE -> "EDGE"
            TelephonyManager.NETWORK_TYPE_EHRPD -> "eHRPD"
            TelephonyManager.NETWORK_TYPE_EVDO_0 -> "EVDO rev 0"
            TelephonyManager.NETWORK_TYPE_EVDO_A -> "EVDO rev A"
            TelephonyManager.NETWORK_TYPE_EVDO_B -> "EVDO rev B"
            TelephonyManager.NETWORK_TYPE_GPRS -> "GPRS"
            TelephonyManager.NETWORK_TYPE_GSM -> "GSM"
            TelephonyManager.NETWORK_TYPE_HSDPA -> "HSDPA"
            TelephonyManager.NETWORK_TYPE_HSPA -> "HSPA"
            TelephonyManager.NETWORK_TYPE_HSPAP -> "HSPA+"
            TelephonyManager.NETWORK_TYPE_HSUPA -> "HSUPA"
            TelephonyManager.NETWORK_TYPE_IWLAN -> "IWLAN"
            TelephonyManager.NETWORK_TYPE_LTE -> "LTE"
            TelephonyManager.NETWORK_TYPE_NR -> "NR (new radio) 5G"
            TelephonyManager.NETWORK_TYPE_TD_SCDMA -> "TD_SCDMA"
            TelephonyManager.NETWORK_TYPE_UMTS -> "UMTS"
            else -> "[Unknown]"
        }

        val overrideString = when (telephony?.overrideNetworkType) {
            TelephonyDisplayInfo.OVERRIDE_NETWORK_TYPE_NR_NSA -> "5G non-standalone"
            TelephonyDisplayInfo.OVERRIDE_NETWORK_TYPE_NR_ADVANCED -> "5G standalone (advanced)"
            TelephonyDisplayInfo.OVERRIDE_NETWORK_TYPE_LTE_ADVANCED_PRO -> "LTE Advanced Pro"
            TelephonyDisplayInfo.OVERRIDE_NETWORK_TYPE_LTE_CA -> "LTE (carrier aggregation)"
            else -> null
        }
        return overrideString ?: baseTypeString
    }
}