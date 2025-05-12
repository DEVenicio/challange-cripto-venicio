package br.com.challengecriptovenicio.data.model

data class CardData(val fields: List<CardField>)

data class CardField(
    val title: String?,
    val value: Any?
)