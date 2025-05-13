package br.com.challengecriptovenicio.data.repository

import br.com.challengecriptovenicio.data.datasource.remote.ApiService
import br.com.challengecriptovenicio.data.mapper.toCardData
import br.com.challengecriptovenicio.data.model.CardData
import br.com.challengecriptovenicio.domain.repository.NodeRepository

class NodeRepositoryImpl(
    private val apiService: ApiService
) : NodeRepository {
    override suspend fun getNodes(): Result<List<CardData>> {
        return try {
            val result = apiService.getNodes()
            result.map { list -> list.map { it.toCardData() } }
        } catch (e: Exception) {
            Result.failure(e)

        }
    }
}