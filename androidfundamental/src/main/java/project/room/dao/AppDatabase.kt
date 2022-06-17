package project.room.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import project.room.model.Contact


@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                val mInstance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "room_database"
                    )
                    .allowMainThreadQueries()
                    .build()
                instance = mInstance
                mInstance
            }
        }
    }
}