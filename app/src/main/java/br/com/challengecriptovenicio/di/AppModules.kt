package br.com.challengecriptovenicio.di

import br.com.challengecriptovenicio.data.datasource.remote.ApiService
import br.com.challengecriptovenicio.data.datasource.remote.ApiServiceImpl
import br.com.challengecriptovenicio.data.repository.NodeRepositoryImpl
import br.com.challengecriptovenicio.domain.repository.NodeRepository
import br.com.challengecriptovenicio.domain.usecase.GetNodesUseCase
import br.com.challengecriptovenicio.presentation.viewmodel.NodeViewModel

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single<NodeRepository> { NodeRepositoryImpl(get()) }
}

val useCaseModule = module {
    single { GetNodesUseCase(get()) }
}

val viewModelModule = module {
    viewModel { NodeViewModel(get()) }
}

val networkModule = module {
    single {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                gson()
            }
        }
    }

    single<ApiService> { ApiServiceImpl(get()) }
}


val appModules = listOf(
    networkModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)
