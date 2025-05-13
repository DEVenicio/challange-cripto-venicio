package br.com.challengecriptovenicio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.challengecriptovenicio.domain.usecase.GetNodesUseCase
import br.com.challengecriptovenicio.presentation.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NodeViewModel(
   private val getNodesUseCase: GetNodesUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState : StateFlow<UiState> = _uiState

    init {
        loadCards()
    }


     fun loadCards() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            val result = getNodesUseCase()

            _uiState.value = result.fold(
                onSuccess = { data -> UiState.Success(data) },
                onFailure =  { UiState.Error("Tivemos um problema :( \n Tente novamente mais tarde") }
            )
        }
    }

}