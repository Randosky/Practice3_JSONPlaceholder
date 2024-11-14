package com.ovinkin.practice3_jsonplaceholder.di

import com.ovinkin.practice3_jsonplaceholder.data.mapper.JSONPlaceholderResponseToEntityMapper
import com.ovinkin.practice3_jsonplaceholder.data.repository.JSONPlaceholderRepository
import com.ovinkin.practice3_jsonplaceholder.domain.repository.IJSONPlaceholderRepository
import com.ovinkin.practice3_jsonplaceholder.presentation.datastore.PostsDataStore
import com.ovinkin.practice3_jsonplaceholder.presentation.mapper.JSONPlaceholderUIMapper
import com.ovinkin.practice3_jsonplaceholder.presentation.viewModel.CommentsViewModel
import com.ovinkin.practice3_jsonplaceholder.presentation.viewModel.PostsViewModel
import com.ovinkin.practice3_jsonplaceholder.presentation.viewModel.UsersViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val rootModule = module {
    single { PostsDataStore(androidContext()) }
    single<IJSONPlaceholderRepository> { JSONPlaceholderRepository(get(), get()) }
    factory { JSONPlaceholderUIMapper() }
    factory { JSONPlaceholderResponseToEntityMapper() }
    viewModel { PostsViewModel(get(), get(), get()) }
    viewModel { CommentsViewModel(get(), get()) }
    viewModel { UsersViewModel(get(), get()) }
}