package aleshka.developement.calendarapp.di

import aleshka.developement.calendarapp.domain.view_models.PlansViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    /*
    Create viewModels here
    */

    viewModel {
        PlansViewModel(get())
    }

}
