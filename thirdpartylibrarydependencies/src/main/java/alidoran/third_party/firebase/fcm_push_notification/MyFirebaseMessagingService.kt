package alidoran.third_party.firebase.fcm_push_notification

import alidoran.third_party.R
import alidoran.third_party.ThirdPartyActivity
import alidoran.third_party.apis.rest.retro_standard.getWeatherServiceLiveData
import alidoran.third_party.app_status.AppStatusHelp
import alidoran.third_party.databinding.ActivityFcmPushNotificationBinding
import alidoran.third_party.firebase.fcm_push_notification.services.ServiceLower26
import alidoran.third_party.firebase.fcm_push_notification.services.ServiceUpper26
import alidoran.third_party.firebase.fcm_push_notification.services.ServiceUpper26.ActionType.START_BG_FG_SERVICE_26
import alidoran.third_party.firebase.fcm_push_notification.services.ServiceUpper26.ActionType.STOP_BG_SERVICE_26
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/* Postman data message
URL:
Post
https://fcm.googleapis.com/fcm/send

Body:
{
"to": "cVALtG-aRUeWeyhbgDn3wk:APA91bEW8aeWh43d8FK50j722hJioVQReIvIv2wAUfdKn5sesZd53ZmcYiMzyxGgCeO1H9J3sbbQ85KjtNbsVi4dLsS-wXIk3UJFicFfvRLDAoUvUoYRt9ZwUEQ0NVKFymti8e8CYgRZ",
"collapse_key": "type_a",
"data": {
    "notificationTitle": "title",
    "notificationBody": "body",
    "autoCallApi": false,
    "AutoStartActivity": true,
    "showNotification": false
},
"priority":"high"
}

Headers:
Authorization = key=AAAAs4--0hE:APA91bHtoxIbJHHGFzN5xGCuB_4IvukoU-bGvRsm67dO0WwZpMVS1UupOA5vWY4cOxKsjJaOB9X3hEPX4fEpkCJ7VrXr-_RWahgSolQgx0NvzvwjkJdxiS64zjFHMhHxfydcaMzntF68
*/

class MyFirebaseMessagingService : FirebaseMessagingService() {
    // region START receive_message
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("AliDoran", "onDoranMessageReceived")
        AppStatusHelp(applicationContext).getIsAppInBackground()
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        remoteMessage.data.values.let {
            if (it.isNotEmpty()) {
                handleDataMessage(remoteMessage)
            }
            Log.d("Ali Doran", "onDoranDataReceived: $it")
        }
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // endregion

    // region START on_new_token
    /**
     * Called if the FCM registration token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the
     * FCM registration token is initially generated so this is where you would retrieve the token.
     */
    override fun onNewToken(token: String) {
        Log.d("TAG", "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
        sendRegistrationToServer(token)
    }
    private fun sendRegistrationToServer(token: String?) {
        // TODO: Implement this method to send token to your app server.
        Log.d("TAG", "sendRegistrationTokenToServer($token)")
    }
    // endregion

    private suspend fun callApiOnFcm() {
        val response = getWeatherServiceLiveData().getWeatherApi2(q = "Tehran")
        val toastText = if (response.isSuccessful)
            "onDoranMakeApiCall: ${response.body()!!.location}"
        else
            "onDoranMakeApiCall: ${response.errorBody()}"
        Log.d("callApiOnFcm:", toastText)
        Toast.makeText(
            applicationContext,
            toastText,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun handleDataMessage(remoteMessage: RemoteMessage) {
        if (remoteMessage.data["autoCallApi"] == true.toString()) {
            checkBackgroundLocationPermission()
            MainScope().launch {
                callApiOnFcm()
            }
        }
        if (remoteMessage.data["AutoStartActivity"] == true.toString()) {
            val intent = Intent(this, ThirdPartyActivity::class.java)
                .addFlags(FLAG_ACTIVITY_NEW_TASK)
                .putExtra("TestExtra", "Test extra")
            startActivity(intent)
            Log.d("Ali Doran", "onDoranAutoStartActivityReceived")
        }
        if (remoteMessage.data["showNotification"] == true.toString()) {
            val title = remoteMessage.data["notificationTitle"] ?: ""
            val body = remoteMessage.data["notificationBody"] ?: ""
            showNotification(title, body)
            Log.d("Ali Doran", "onDoranShowNotificationReceived: $title $body")
        }
        if (remoteMessage.data["startService"] == true.toString()) {
            startServiceByDataMessage()
            Log.d("Ali Doran", "onDoranStartServiceReceived")
        }
        if (remoteMessage.data["stopService"] == true.toString()) {
            stopServiceByDataMessage()
            Log.d("Ali Doran", "onDoranStopServiceReceived")
        }
    }

    private fun startServiceByDataMessage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val i = Intent(this, ServiceUpper26::class.java)
            Log.d("TAG", "startServiceType: startForegroundService")
            i.putExtra("ActionType", START_BG_FG_SERVICE_26)
            startForegroundService(i)
        } else {
            val i = Intent(this, ServiceLower26::class.java)
            i.putExtra("ActionType", START_BG_FG_SERVICE_26)
            Log.d("TAG", "startServiceType: startService")
            startService(i)
        }
    }

    private fun stopServiceByDataMessage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val i = Intent(this, ServiceUpper26::class.java)
            Log.d("TAG", "startServiceType: startForegroundService")
            i.putExtra("ActionType", STOP_BG_SERVICE_26)
            stopService(i)
        } else {
            val i = Intent(this, ServiceLower26::class.java)
            i.putExtra("ActionType", STOP_BG_SERVICE_26)
            Log.d("TAG", "startServiceType: startService")
            startService(i)
        }
    }

    private fun showNotification(title: String, message: String) {
        val intent = Intent(this, ActivityFcmPushNotificationBinding::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0 /* Request code */, intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
            .setOngoing(true)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        val notification = notificationBuilder.build()
        notificationManager.notify(0 /* ID of notification */, notification)
    }

    private fun checkBackgroundLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this.baseContext,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}