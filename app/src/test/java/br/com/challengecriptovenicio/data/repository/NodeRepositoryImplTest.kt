package br.com.challengecriptovenicio.data.repository

import br.com.challengecriptovenicio.data.datasource.remote.ApiService
import br.com.challengecriptovenicio.data.mapper.toCardData
import br.com.challengecriptovenicio.domain.model.NodeDTO
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NodeRepositoryImplTest {

    private val apiService = mockk<ApiService>()
    private val repository = NodeRepositoryImpl(apiService)

    @Test
    fun `getNodes should return Success when API returns data`() = runTest {
        val dto = NodeDTO(
            publicKey = "324242421",
            alias = "alias-fake",
            channels = 123243,
            capacity = 123245,
            firstSeen = 12313,
            updatedAt = 12324,
            city =  mapOf("en" to "New York", "ptBR" to "São Paulo"),
            country =  mapOf("en" to "EUA", "ptBR" to "Brasil")
        )
        val expected = dto.toCardData()

        coEvery { apiService.getNodes() } returns Result.success(listOf(dto))

        val result = repository.getNodes()

        assert(result.isSuccess)
        assertEquals(listOf(expected), result.getOrNull())
    }


    @Test
    fun `getNodes should return Failure when API throws exception`() = runTest {
        val exception = RuntimeException("API error")
        coEvery { apiService.getNodes() } throws exception

        val result = repository.getNodes()

        assert(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

}