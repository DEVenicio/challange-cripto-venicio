package br.com.challengecriptovenicio.domain.model

data class NodeDTO (
    val publicKey : String?,
    val alias: String?,
    val channels : Long?,
    val capacity : Long?,
    val firstSeen : Long?,
    val  updatedAt : Long?,
    val city : Map<String, String>?,
    val country: Map<String, String>?,
)