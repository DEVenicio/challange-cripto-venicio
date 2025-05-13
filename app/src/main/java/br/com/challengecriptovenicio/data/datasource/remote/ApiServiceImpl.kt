package br.com.challengecriptovenicio.data.datasource.remote

import br.com.challengecriptovenicio.domain.model.NodeDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ApiServiceImpl(private val client: HttpClient) : ApiService {
    override suspend fun getNodes(): Result<List<NodeDTO>> {
        return try {
            val response =
                client.get("https://mempool.space/api/v1/lightning/nodes/rankings/connectivity") {
                    contentType(ContentType.Application.Json)
                }
            Result.success(response.body())

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
