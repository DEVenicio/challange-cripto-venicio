package br.com.challengecriptovenicio.domain.usecase

import br.com.challengecriptovenicio.data.model.CardData
import br.com.challengecriptovenicio.domain.repository.NodeRepository

class GetNodesUseCase(
    private val repository: NodeRepository
) {
    suspend operator fun invoke() : Result<List<CardData>> {
        return repository.getNodes()
    }
}