package alidoran.third_party.courier

import alidoran.third_party.R
import alidoran.third_party.app_status.AppStatusHelp
import android.os.Bundle
import alidoran.third_party.databinding.ActivityCourierBinding
import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.courier.android.Courier
import com.courier.android.inbox.CourierInbox
import com.courier.android.models.CourierElement
import com.courier.android.models.CourierInboxChannel
import com.courier.android.models.markAsRead
import com.courier.android.models.markAsUnread
import com.courier.android.modules.addAuthenticationListener
import com.courier.android.modules.isUserSignedIn
import com.courier.android.modules.sendMessage
import com.courier.android.modules.setFCMToken
import com.courier.android.modules.signIn
import com.courier.android.modules.signOut
import com.courier.android.modules.userId
import com.courier.android.utils.requestNotificationPermission
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging


/*
Change manifest in this way

        <service
            android:name=".firebase.fcm_push_notification.MyFirebaseMessagingService"
            android:directBootAware="true"
            android:exported="false">
            <intent-filter>
                <action android:name="com.google.firebase.MESSAGING_EVENT" />
            </intent-filter>
        </service>
<!--        <service-->
<!--            android:name=".courier.CourierNotificationService"-->
<!--            android:exported="false">-->
<!--            <intent-filter>-->
<!--                <action android:name="com.google.firebase.MESSAGING_EVENT" />-->
<!--            </intent-filter>-->
<!--        </service>-->
 */



class CourierSampleActivity : AppCompatActivity()
//    : CourierActivity()
{
    lateinit var binding: ActivityCourierBinding
    val userId = "Z312"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourierBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signIn()
        initEvent()
        setInbox()
    }

    private fun initEvent() {
        binding.btnSendMessage.setOnClickListener {
            sendMessage()
        }
        binding.btnSignOut.setOnClickListener {
            signOut()
        }
        binding.btnSignIn.setOnClickListener {
            signIn()
        }
    }

    private fun otherMethods() {
        val listener = Courier.shared.addAuthenticationListener { userId ->
            print(userId ?: "No userId found")
        }
        listener.remove()
    }

    private fun signIn() {
        if (Courier.shared.userId.isNullOrEmpty() && Courier.shared.isUserSignedIn.not())
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(
                        ContentValues.TAG,
                        "Fetching FCM registration token failed",
                        task.exception
                    )
                    return@OnCompleteListener
                }
                AppStatusHelp(this).getIsAppInBackground()
                // Get new FCM registration token
                val token = task.result
                setToken(token)
                // Log and toast
                val msg = getString(R.string.msg_token_fmt, token)
                Log.d(ContentValues.TAG, msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            })

        if (Courier.shared.isUserSignedIn.not()
            || Courier.shared.userId.equals(userId).not()
        )
            Courier.shared.signIn(
                accessToken = "pk_prod_E97T6806QG4WMDKZF8ADM1S1JPPB",
                clientKey = "MmZlMTI3ZjItZGIxMS00YjdlLWJhYjMtZWIxMWM4YWViZmUw",
                userId = userId,
                onSuccess = {
                    requestNotificationPermission()
                    Log.d("TAG", "initCourier: ")
                },
                onFailure = { e ->
                    Log.d("TAG", "initCourier: ")
                }
            )
    }

    private fun signOut() {
        Courier.shared.signOut(
            onSuccess = {
                Log.d("TAG", "btnSignOut: Success")
            },
            onFailure = {
                Log.d("TAG", "btnSignOut: Failed")
            }
        )
    }

    private fun sendMessage() {
        Courier.shared.sendMessage(
            authKey = "pk_prod_E97T6806QG4WMDKZF8ADM1S1JPPB",
            userIds = listOf(userId),
            title = "Hey there 👋",
            body = "${binding.edtBodyMessage.text} 😁",
            channels = listOf(
                CourierInboxChannel(
                    elements = listOf(
                        CourierElement(
                            type = "action",
                            content = "Action Button",
                            data = mapOf(
                                "CUSTOM_ACTION_KEY" to "YOUR_CUSTOM_VALUE"
                            )
                        )
                    ),
                    data = mapOf(
                        "CUSTOM_MESSAGE_KEY" to "YOUR_CUSTOM_VALUE"
                    )
                )
            ),
            onSuccess = {
                Log.d("TAG", "sendMessage: Success")
            },
            onFailure = {
                Log.d("TAG", "sendMessage: Failed")
            }
        )
    }

    fun setToken(token: String) {
        Courier.shared.setFCMToken(
            token,
            onSuccess = {
                Log.d("TAG", "setToken:${token}")

            },
            onFailure = {
                Log.d("TAG", "setToken:${token}")
            }
        )
    }

    private fun setInbox() {
        val inbox: CourierInbox = binding.courierInbox

        inbox.setOnClickMessageListener { message, index ->
            Courier.log(message.toString())
            if (message.isRead) message.markAsUnread() else message.markAsRead()
        }

        inbox.setOnClickActionListener { action, message, index ->
            Courier.log(action.toString())
        }
    }

//    override fun onPushNotificationClicked(message: RemoteMessage) {
//        Log.d("TAG", "Message clicked:\n${message.data}")
//    }
//
//    override fun onPushNotificationDelivered(message: RemoteMessage) {
//        Log.d("TAG", "Message delivered:\n${message.data}")
//    }
}