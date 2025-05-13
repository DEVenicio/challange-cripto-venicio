package br.com.challengecriptovenicio.domain.repository

import br.com.challengecriptovenicio.data.model.CardData


interface NodeRepository {
    suspend fun getNodes() : Result<List<CardData>>
}