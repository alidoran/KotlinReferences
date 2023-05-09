package alidoran.third_party.firebase.fcm_push_notification

import alidoran.third_party.R
import alidoran.third_party.app_status.AppStatusHelp
import alidoran.third_party.databinding.ActivityFcmPushNotificationBinding
import alidoran.third_party.firebase.fcm_push_notification.second_service.BackgroundSecondService
import alidoran.third_party.firebase.fcm_push_notification.second_service.BackgroundSecondService.ActionType.START_FOREGROUND
import alidoran.third_party.firebase.fcm_push_notification.second_service.BackgroundSecondService.ActionType.START_SERVICE
import alidoran.third_party.firebase.fcm_push_notification.second_service.BackgroundSecondService.ActionType.STOP_SERVICE
import android.Manifest
import android.app.ActivityManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging


/*
1-
buildscript {

repositories {
// Make sure that you have the following two repositories
google()  // Google's Maven repository
mavenCentral()  // Maven Central repository
}

dependencies {
...

// Add the dependency for the Google services Gradle plugin
classpath 'com.google.gms:google-services:4.3.14'
}
}

2- plugins {
id 'com.android.application'

// Add the Google services Gradle plugin
id 'com.google.gms.google-services'
...
}

4- manifest:
<service
android:name=".java.MyFirebaseMessagingService"
android:exported="false">
<intent-filter>
<action android:name="com.google.firebase.MESSAGING_EVENT" />
</intent-filter>
</service>
<!-- Set custom default icon. This is used when no icon is set for incoming notification messages.
See README(https://goo.gl/l4GJaQ) for more. -->
<meta-data
android:name="com.google.firebase.messaging.default_notification_icon"
android:resource="@drawable/ic_stat_ic_notification" />
<!-- Set color used with incoming notification messages. This is used when no color is set for the incoming
notification message. See README(https://goo.gl/6BKBk7) for more. -->
<meta-data
android:name="com.google.firebase.messaging.default_notification_color"
android:resource="@color/colorAccent" />
<meta-data
android:name="com.google.firebase.messaging.default_notification_channel_id"
android:value="@string/default_notification_channel_id" />
 */

class FcmPushNotificationActivity : AppCompatActivity() {
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(this, "Notifications permission granted", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(
                this, "FCM can't post notifications without POST_NOTIFICATIONS permission",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFcmPushNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent(binding)

//        checkPermission()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mNotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            var channel: NotificationChannel? =
                mNotificationManager.getNotificationChannel(getString(R.string.default_notification_channel_id))
            if (channel == null) {
                channel = NotificationChannel(
                    getString(R.string.default_notification_channel_id),
                    getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_HIGH
                )
            }
            mNotificationManager.createNotificationChannel(channel)
            mNotificationManager.areNotificationsEnabled()

        }

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            AppStatusHelp(this).getIsAppInBackground()
            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = getString(R.string.msg_token_fmt, token)
            Log.d(TAG, msg)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })
    }


    private fun initEvent(binding: ActivityFcmPushNotificationBinding) = with(binding) {
        val intent = Intent(this@FcmPushNotificationActivity, BackgroundSecondService::class.java)

        btnStopService.setOnClickListener {
            intent.putExtra("ActionType" , STOP_SERVICE)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent)
            }
        }

        btnServiceStatus.setOnClickListener {
            val serviceRunning = isServiceRunning(false)
            Toast.makeText(this@FcmPushNotificationActivity, "Service running is: $serviceRunning", Toast.LENGTH_LONG).show()
        }

        btnStartService.setOnClickListener {
            intent.putExtra("ActionType" , START_SERVICE)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent)
            }
        }

        btnStartForeground.setOnClickListener {
            intent.putExtra("ActionType" , START_FOREGROUND)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent)
            }
        }
    }

    //region overlay permission
    //this permission is need for automatic starting FM
    private fun checkOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )
                startActivity(intent)
            }
        }
    }
    //endregion

    private fun isServiceRunning(isForeground: Boolean): Boolean {
        val manager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (BackgroundSecondService::class.java.name == service.service.className) {
                return isForeground == service.foreground
            }
        }
        return false
    }
}