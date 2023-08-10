package aleshka.developement.calendarapp.repositories

import aleshka.developement.calendarapp.data.database.PlansDatabase
import aleshka.developement.calendarapp.models.PlanModel

class PlansRepository (
    private val database: PlansDatabase
) {
    fun getPlans()
        = database.getPlansDao().getPlans()

    suspend fun addPlans(plan: PlanModel)
        = database.getPlansDao().addPlan(plan)
}
