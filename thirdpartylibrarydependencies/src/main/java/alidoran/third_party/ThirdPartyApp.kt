package alidoran.third_party

import alidoran.third_party.app_status.DefaultLifecycleObserver
import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner

class ThirdPartyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        //LifeCycle
        ProcessLifecycleOwner.get().lifecycle.addObserver(DefaultLifecycleObserver(this))

        //Pendo
        var pendoAppKey =
            "eyJhbGciOiJSUzI1NiIsImtpZCI6IiIsInR5cCI6IkpXVCJ9.eyJkYXRhY2VudGVyIjoidXMiLCJrZXkiOiI5YzA1M2UzNjA2NjExYTM3ZmRiY2I5OTYyMjI0M2ZmMTYwMTE2YjU2OWVhMWNmZjk2MmUzY2YyYjdmYjg0MTBhZmRkYTU0YjI5NTNhMjUyOTE5MDgzMjEwZmMyZjVlNjE4YWFjZWNmMjg3ZjY1ODRkNDU5MmQ1ODA3YzliMjU3YTRmNTI1NzAzZGY3YjA5NDZmOGU1M2ViN2ZlNTY2ZWQ0LmY2YmM1MWFjOTI5Y2NjMmY4ODY0NTgwZmVlYzg4ZDhhLjgwMmQyNTE1NzBmMzFhY2I4OTE2ZDYxZjQwNTM0N2ZmNjc2MGJiMDg4NjYwZDhkODYzMTYwNGViMTZmN2Q3NjQifQ.pYL7HJYMovQAtT4NTvX3cu8Xr2UrCR8MatYzSZrMgXVr3ScwHaH4BAX42bY-w3UQXZ6M0eT3hfnDcS86BgIx1sbJZ3XcQ87mVRe4ugXo74brenZCsQZNKpctuv2NsykYBnKlAuxNSMUPq8TKLryKUw-l6LmZ3uTRrQvRUWt6QTs"

        Pendo.setup(
            this,
            pendoAppKey,
            null,
            null
        )
    }
}