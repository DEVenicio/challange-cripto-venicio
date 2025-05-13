package br.com.challengecriptovenicio.data.datasource.remote

import br.com.challengecriptovenicio.domain.model.NodeDTO


interface ApiService {
    suspend fun getNodes(): Result<List<NodeDTO>>
}
