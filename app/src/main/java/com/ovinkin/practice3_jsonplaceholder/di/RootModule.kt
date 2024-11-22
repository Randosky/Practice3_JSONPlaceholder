package com.ovinkin.practice3_jsonplaceholder.di

import com.ovinkin.practice3_jsonplaceholder.data.mapper.JSONPlaceholderResponseToEntityMapper
import com.ovinkin.practice3_jsonplaceholder.data.repository.JSONPlaceholderRepository
import com.ovinkin.practice3_jsonplaceholder.domain.repository.IJSONPlaceholderRepository
import com.ovinkin.practice3_jsonplaceholder.presentation.mapper.JSONPlaceholderUIMapper
import com.ovinkin.practice3_jsonplaceholder.presentation.viewModel.CommentsViewModel
import com.ovinkin.practice3_jsonplaceholder.presentation.viewModel.FavoritesViewModel
import com.ovinkin.practice3_jsonplaceholder.presentation.viewModel.PostsViewModel
import com.ovinkin.practice3_jsonplaceholder.presentation.viewModel.SettingsViewModel
import com.ovinkin.practice3_jsonplaceholder.presentation.viewModel.UsersViewModel
import com.ovinkin.practice3_jsonplaceholder.storage.cache.badgeCache.BadgeCache
import com.ovinkin.practice3_jsonplaceholder.storage.database.dao.PostsDataBase
import com.ovinkin.practice3_jsonplaceholder.storage.database.mapper.DataBasePostsMapper
import com.ovinkin.practice3_jsonplaceholder.storage.datastore.PostsDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val rootModule = module {
    single { PostsDataStore(androidContext()) }
    single { PostsDataBase.getDatabase(androidContext()) }
    single { BadgeCache() }
    single<IJSONPlaceholderRepository> { JSONPlaceholderRepository(get(), get()) }
    factory { JSONPlaceholderUIMapper() }
    factory { JSONPlaceholderResponseToEntityMapper() }
    factory { DataBasePostsMapper() }
    viewModel { CommentsViewModel(get(), get()) }
    viewModel { UsersViewModel(get(), get()) }
    viewModel { PostsViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { SettingsViewModel(get(), get()) }
    viewModel { FavoritesViewModel(get(), get()) }
}