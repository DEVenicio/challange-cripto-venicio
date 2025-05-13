package br.com.challengecriptovenicio.presentation.viewmodel

import br.com.challengecriptovenicio.data.model.CardData
import br.com.challengecriptovenicio.domain.usecase.GetNodesUseCase
import br.com.challengecriptovenicio.presentation.state.UiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NodeViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private val useCase = mockk<GetNodesUseCase>()
    private lateinit var viewModel: NodeViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `uiState should be Success when useCase returns success`() = runTest {
        val fakeData = listOf(mockk<CardData>())
        coEvery { useCase.invoke() } returns Result.success(fakeData)

        viewModel = NodeViewModel(useCase)

        // advance the coroutines until they complete
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assert(state is UiState.Success)
        assertEquals(fakeData, (state as UiState.Success).cards)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `uiState should be Error when useCase returns failure`() = runTest {
        coEvery { useCase.invoke() } returns Result.failure(Exception("Erro"))

        viewModel = NodeViewModel(useCase)

        advanceUntilIdle()

        val state = viewModel.uiState.value
        assert(state is UiState.Error)
        assert((state as UiState.Error).message.contains("Tivemos um problema"))
    }
}