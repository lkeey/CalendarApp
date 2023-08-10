package aleshka.developement.calendarapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "plans"
)
data class PlanModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val date: String
)
