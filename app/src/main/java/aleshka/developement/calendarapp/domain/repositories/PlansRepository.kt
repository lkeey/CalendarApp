package aleshka.developement.calendarapp.domain.repositories

import aleshka.developement.calendarapp.data.database.PlansDatabase
import aleshka.developement.calendarapp.data.models.PlanModel

class PlansRepository (
    private val database: PlansDatabase
) {
    fun getPlans()
        = database.getPlansDao().getPlans()

    suspend fun addPlan(plan: PlanModel)
        = database.getPlansDao().addPlan(plan)

    suspend fun deletePlan(plan: PlanModel)
            = database.getPlansDao().deletePlan(plan)
}
