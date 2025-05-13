package br.com.challengecriptovenicio.domain.usecase

import br.com.challengecriptovenicio.data.model.CardData
import br.com.challengecriptovenicio.domain.repository.NodeRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetNodesUseCaseTest {
    private lateinit var repository: NodeRepository
    private lateinit var useCase: GetNodesUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetNodesUseCase(repository)
    }


   @Test
    fun `should return list nodeCards when repository return success` () = runTest {

       val mockNode = mockk<CardData>()
       val mockListNode = listOf(mockNode)
       coEvery { repository.getNodes() } returns Result.success(mockListNode)

       val result = useCase.invoke()

       println(result)

       assert(result.isSuccess)
       assertEquals(mockListNode, result.getOrNull())
   }

    @Test
    fun `should return failure when repository returns error`() = runTest {
        val exception = RuntimeException("Erro API Timeout")

        coEvery { repository.getNodes() } returns Result.failure(exception)

        val result = useCase.invoke()

        assert(result.isFailure)
        assertEquals("Erro API Timeout", result.exceptionOrNull()?.message)
    }


}