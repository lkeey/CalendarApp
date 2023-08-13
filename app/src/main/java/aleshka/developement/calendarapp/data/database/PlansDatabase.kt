package aleshka.developement.calendarapp.data.database

import aleshka.developement.calendarapp.data.dao.PlansDao
import aleshka.developement.calendarapp.data.models.PlanModel
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [PlanModel::class],
    version = 6
)
abstract class PlansDatabase : RoomDatabase() {
    abstract fun getPlansDao() : PlansDao

    companion object {

        @Volatile
        private var instance: PlansDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            PlansDatabase::class.java,
            "plans_db.db"
        )
        .fallbackToDestructiveMigration()
        .build()

    }
}