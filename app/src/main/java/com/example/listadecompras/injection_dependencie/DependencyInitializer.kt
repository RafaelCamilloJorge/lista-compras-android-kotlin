package com.example.listadecompras.injection_dependencie

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

import com.example.listadecompras.feature.login.LoginViewModel
import com.example.listadecompras.feature.manage_item.ManageItemViewModel
import com.example.listadecompras.feature.manage_list.ManageListViewModel
import com.example.listadecompras.feature.register.RegisterViewModel
import com.example.listadecompras.feature.shopping_lists.ShoppingListViewModel
import com.example.listadecompras.repositories.ListRepository
import com.example.listadecompras.repositories.LoginRepository

class DependencyInitializer {
    val appModule = module {
        //Repositories
        single { LoginRepository() }
        single { ListRepository() }
        //ViewModels
        viewModel { LoginViewModel(get()) }
        viewModel { ManageListViewModel(get()) }
        viewModel { ManageItemViewModel() }
        viewModel { RegisterViewModel(get()) }
        viewModel { ShoppingListViewModel(get()) }
    }
}
