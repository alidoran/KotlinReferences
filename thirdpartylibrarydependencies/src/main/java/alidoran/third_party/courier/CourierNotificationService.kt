package alidoran.third_party.courier

import android.annotation.SuppressLint
import com.courier.android.notifications.presentNotification
import com.courier.android.service.CourierService
import com.google.firebase.messaging.RemoteMessage

/* Postman data message
URL:
Post
https://api.courier.com/send

Body:
{
  "message": {
    "content": {
      "title": "mT2",
      "body": "mB2"
    },
    "to": {
      "user_id": "Z307"
    }
  }
}

Headers:
Authorization = Bearer pk_prod_E97T6806QG4WMDKZF8ADM1S1JPPB
*/

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class CourierNotificationService : CourierService(){
    override fun showNotification(message: RemoteMessage) {
        super.showNotification(message)

        // TODO: This is where you will customize the notification that is shown to your users
        // The function below is used to get started quickly.
        // `message.presentNotification(...)` is used to get started quickly and not for production use.
        // More information on how to customize an Android notification here:
        // https://developer.android.com/develop/ui/views/notifications/build-notification

        message.presentNotification(
            context = this,
            handlingClass = CourierSampleActivity::class.java,
            icon = android.R.drawable.ic_dialog_info
        )
    }
}