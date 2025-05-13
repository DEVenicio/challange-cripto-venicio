package br.com.challengecriptovenicio.data.mapper

import br.com.challengecriptovenicio.data.model.CardData
import br.com.challengecriptovenicio.data.model.CardField
import br.com.challengecriptovenicio.domain.model.NodeDTO

fun NodeDTO.toCardData() : CardData {

    val fields = buildList {
        publicKey?.let { add(CardField("PublicKey", it)) }
        alias?.let { add(CardField("Alias", it)) }
        channels?.let { add(CardField("Channels", it)) }
        capacity?.let { add(CardField("Capacity", it)) }
        firstSeen?.let {
            add(CardField("FirstSeen", it)) }
        updatedAt?.let { add(CardField("UpdatedAt", it)) }
        val cityValue = city?.get("pt-BR") ?: city?.get("en")
        if (cityValue != null) {
            add(CardField("City", cityValue))
        }

        val countryValue = country?.get("pt-BR") ?: country?.get("en")
        if (countryValue != null) {
            add(CardField("Country", countryValue))
        }

    }

    return CardData(fields = fields)

}