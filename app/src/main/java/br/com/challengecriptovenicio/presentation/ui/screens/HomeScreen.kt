package br.com.challengecriptovenicio.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.com.challengecriptovenicio.presentation.state.UiState
import br.com.challengecriptovenicio.presentation.ui.components.RainbowBorderCard
import br.com.challengecriptovenicio.presentation.viewmodel.NodeViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier,
    nodeViewModel: NodeViewModel = koinViewModel()
) {
    val uiState by nodeViewModel.uiState.collectAsState()

    var hasLoadedOnce by remember { mutableStateOf(false) }

    val isRefreshing = uiState is UiState.Loading && hasLoadedOnce

    val showCenterLoading = uiState is UiState.Loading && !hasLoadedOnce




    val pullRefreshState =  rememberPullRefreshState(
       refreshing = isRefreshing,
       onRefresh = {
           nodeViewModel.loadCards()
    }
   )


    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(state = pullRefreshState)
    ) {
        when (uiState) {

            is UiState.Loading -> {
                if (showCenterLoading) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                    hasLoadedOnce = true
                }
            }

            is UiState.Success -> {

                val cards = (uiState as UiState.Success).cards
                    LazyColumn {
                    items(cards) { card ->
                        RainbowBorderCard(card)
                    }
                }

            }

            is UiState.Error -> {

                val message = (uiState as UiState.Error).message
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Erro: $message", color = Color.Red)

                }
            }
        }

            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
    }
}


