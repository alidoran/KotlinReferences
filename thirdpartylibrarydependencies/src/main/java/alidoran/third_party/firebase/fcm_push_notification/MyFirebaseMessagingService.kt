package alidoran.third_party.firebase.fcm_push_notification

import alidoran.third_party.R
import alidoran.third_party.ThirdPartyActivity
import alidoran.third_party.apis.rest.retro_standard.getWeatherServiceLiveData
import alidoran.third_party.app_status.AppStatusHelp
import alidoran.third_party.databinding.ActivityFcmPushNotificationBinding
import alidoran.third_party.firebase.fcm_push_notification.second_service.BackgroundSecondService
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyFirebaseMessagingService : FirebaseMessagingService() {

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
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
        android.os.Debug.waitForDebugger()
        val i = Intent(this, BackgroundSecondService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            startForegroundService(i)
        else
            startService(i)


        val a = checkBackgroundLocationPermission()
        Log.d("TAG", "onMessageReceivedDoran:$a")



        checkBackgroundLocationPermission()

        //region call API on service
        GlobalScope.launch(Dispatchers.IO) {
            val isProgramOnBackground = ForegroundCheckTask().execute(applicationContext).get()
            val response = getWeatherServiceLiveData().getWeatherApi2(q = "Tehran")
            if (response.isSuccessful)
                Log.d(ContentValues.TAG, "onDoranMakeApiCall: ${response.body()!!.location}")
            else
                Log.d(ContentValues.TAG, "onDoranMakeApiCall: ${response.errorBody()}")
        }
        //endregion

        //region startActivity on service automatically
        val intent = Intent(this, ThirdPartyActivity::class.java)
            .addFlags(FLAG_ACTIVITY_NEW_TASK)
            .putExtra("TestExtra", "Test extra")
//        startActivity(intent)
        //endregion

        remoteMessage.notification?.body?.let {
            sendNotification(it)
            Log.d("Ali Doran", "onDoranNotificationReceived: $it")
        }

        remoteMessage.data.values.let {
            if (it.isNotEmpty())
                sendNotification(it.toString())
            Log.d("Ali Doran", "onDoranDataReceived: $it")
        }
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
// [END receive_message]

// [START on_new_token]
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
// [END on_new_token]

    /**
     * Schedule async work using WorkManager.
     */
    private fun scheduleJob() {
        // [START dispatch_job]
        val work = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
        WorkManager.getInstance(this).beginWith(work).enqueue()
        // [END dispatch_job]
    }

    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private fun handleNow() {
        Log.d("TAG", "Short lived task is done.")
    }

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM registration token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private fun sendRegistrationToServer(token: String?) {
        // TODO: Implement this method to send token to your app server.
        Log.d("TAG", "sendRegistrationTokenToServer($token)")
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private fun sendNotification(messageBody: String) {
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
            .setContentTitle("My Title")
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

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

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }

    private fun checkBackgroundLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this.baseContext,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }


    /* Postman
    Body
    {
    "to": "dP2L-ooBQF6B6wzkHqFgRx:APA91bEXCgSncNIPtzCJYkOruWwbjPHpkJW1kUhLJ3OkcqHsQKMj_RjdBu5DTasQ5bXOGV_dO47JcLKqpMMq3y6N4PCF6o5x5KOp4YP8k-d1vFvK3TKgeftr8JO1joQUbi5kttHOC6Xx",
    "collapse_key": "type_a",
    "data": {
                "body": "New announcement assigned",
        "OrganizationId": "2",
        "priority": "high",
        "content_available": true,
        "bodyText": "New Announcement assigned",
        "organization": "Elementary school"
    }
}

header

Authorization = key=AAAAs4--0hE:APA91bHtoxIbJHHGFzN5xGCuB_4IvukoU-bGvRsm67dO0WwZpMVS1UupOA5vWY4cOxKsjJaOB9X3hEPX4fEpkCJ7VrXr-_RWahgSolQgx0NvzvwjkJdxiS64zjFHMhHxfydcaMzntF68



    */
}