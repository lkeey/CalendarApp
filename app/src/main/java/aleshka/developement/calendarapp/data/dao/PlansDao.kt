package aleshka.developement.calendarapp.data.dao

import aleshka.developement.calendarapp.models.PlanModel
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlansDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlan(plan: PlanModel): Long

    @Delete
    suspend fun deletePlan(plan: PlanModel)

    // unnecessary to use coroutines because it get live data
    @Query("SELECT * FROM plans")
    fun getPlans(): LiveData<List<PlanModel>>

}
