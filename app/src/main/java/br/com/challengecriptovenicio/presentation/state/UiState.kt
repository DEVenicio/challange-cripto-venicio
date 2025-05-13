package br.com.challengecriptovenicio.presentation.state

import br.com.challengecriptovenicio.data.model.CardData

sealed class UiState {
    object Loading : UiState()
    data class Success(val cards: List<CardData>) : UiState()
    data class Error(val message: String) : UiState()
}