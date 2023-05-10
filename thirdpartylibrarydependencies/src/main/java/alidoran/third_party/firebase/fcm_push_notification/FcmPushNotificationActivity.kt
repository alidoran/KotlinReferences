package alidoran.third_party.firebase.fcm_push_notification

import alidoran.third_party.R
import alidoran.third_party.app_status.AppStatusHelp
import alidoran.third_party.databinding.ActivityFcmPushNotificationBinding
import alidoran.third_party.firebase.fcm_push_notification.services.ServiceLower26
import alidoran.third_party.firebase.fcm_push_notification.services.ServiceLower26.ActionType.START_BG_SERVICE
import alidoran.third_party.firebase.fcm_push_notification.services.ServiceLower26.ActionType.START_FG_SERVICE
import alidoran.third_party.firebase.fcm_push_notification.services.ServiceLower26.ActionType.STOP_BG_SERVICE
import alidoran.third_party.firebase.fcm_push_notification.services.ServiceLower26.ActionType.STOP_FG_SERVICE
import alidoran.third_party.firebase.fcm_push_notification.services.ServiceUpper26
import alidoran.third_party.firebase.fcm_push_notification.services.ServiceUpper26.ActionType.START_BG_SERVICE_26
import alidoran.third_party.firebase.fcm_push_notification.services.ServiceUpper26.ActionType.START_FG_SERVICE_26
import alidoran.third_party.firebase.fcm_push_notification.services.ServiceUpper26.ActionType.STOP_BG_SERVICE_26
import alidoran.third_party.firebase.fcm_push_notification.services.ServiceUpper26.ActionType.STOP_FG_SERVICE_26
import android.Manifest
import android.app.ActivityManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
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

3- manifest:
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

    enum class ServiceStatus{
        Background,
        Foreground,
        NotRunning
    }

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            initEventUpper26(binding)
        } else
            initEventLower26(binding)

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

    private fun initEventLower26(binding: ActivityFcmPushNotificationBinding) = with(binding) {
        val intent = Intent(this@FcmPushNotificationActivity, ServiceLower26::class.java)

        btnStartBgServiceBg.setOnClickListener {
            intent.putExtra("ActionType", START_BG_SERVICE)
            startService(intent)
        }

        btnStartBgServiceFg.setOnClickListener {
            intent.putExtra("ActionType", START_BG_SERVICE)
            startService(intent)
        }

        btnStopBgService.setOnClickListener {
            intent.putExtra("ActionType", STOP_BG_SERVICE)
            startService(intent)
        }

        btnStartFgService.setOnClickListener {
            intent.putExtra("ActionType", START_FG_SERVICE)
            startService(intent)
        }

        btnStopFgService.setOnClickListener {
            intent.putExtra("ActionType", STOP_FG_SERVICE)
            startService(intent)
        }

        btnServiceStatus.setOnClickListener {
            val serviceToastMessage = when (isServiceRunning()) {
                ServiceStatus.Foreground ->
                    "Service is running on foreground"
                ServiceStatus.Background ->
                    "Service is running on Background"
                ServiceStatus.NotRunning ->
                    "Service isn't running"
            }
            Toast.makeText(
                this@FcmPushNotificationActivity,
                serviceToastMessage,
                Toast.LENGTH_LONG
            ).show()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun initEventUpper26(binding: ActivityFcmPushNotificationBinding) = with(binding) {
        val intent = Intent(this@FcmPushNotificationActivity, ServiceUpper26::class.java)

        btnStartBgServiceBg.setOnClickListener {
            intent.putExtra("ActionType", START_BG_SERVICE_26)
            startForegroundService(intent)
        }

        btnStartBgServiceFg.setOnClickListener {
            intent.putExtra("ActionType", START_BG_SERVICE_26)
            startService(intent)
        }

        btnStopBgService.setOnClickListener {
            intent.putExtra("ActionType", STOP_BG_SERVICE_26)
            startForegroundService(intent)
        }

        btnStartFgService.setOnClickListener {
            intent.putExtra("ActionType", START_FG_SERVICE_26)
            startService(intent)
        }

        btnStopFgService.setOnClickListener {
            intent.putExtra("ActionType", STOP_FG_SERVICE_26)
            startForegroundService(intent)
        }

        btnServiceStatus.setOnClickListener {
            val serviceToastMessage = when (isServiceRunning()) {
                ServiceStatus.Foreground ->
                    "Service is running on foreground"
                ServiceStatus.Background ->
                    "Service is running on Background"
                ServiceStatus.NotRunning ->
                    "Service isn't running"
            }
            Toast.makeText(
                this@FcmPushNotificationActivity,
                serviceToastMessage,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun isServiceRunning(): ServiceStatus {
        val manager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (ServiceUpper26::class.java.name == service.service.className) {
                return if  (service.foreground) ServiceStatus.Foreground
                else ServiceStatus.Background
            }
        }
        return ServiceStatus.NotRunning
    }
}