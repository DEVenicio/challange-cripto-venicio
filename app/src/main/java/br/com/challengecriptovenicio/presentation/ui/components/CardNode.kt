package br.com.challengecriptovenicio.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.challengecriptovenicio.data.model.CardData
import br.com.challengecriptovenicio.data.model.CardField
import br.com.challengecriptovenicio.presentation.extensions.toInstantAndFormat
import java.math.BigDecimal
import java.math.RoundingMode


@Composable
fun RainbowBorderCard(cardData: CardData) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Red, Color.Yellow, Color.Green,
                        Color.Blue, Color.Cyan, Color.Magenta
                    ),
                    start = Offset.Zero,
                    end = Offset.Infinite
                ),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(2.dp)
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFAFDFDFD)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
            ) {
                cardData.fields.forEach {
                    if (it.title == "Capacity") {

                        val valueBitcoin = (it.value as? Long)?.let {
                            BigDecimal(it).divide(BigDecimal("100000000"), 8, RoundingMode.HALF_UP)
                                .toPlainString()
                        }
                        CardFieldRow(title = it.title, value = "$valueBitcoin  BTC")
                    } else if (it.title == "FirstSeen" || it.title == "UpdatedAt") {
                        val dateConverted = it.value.toString().toLong().toInstantAndFormat()
                        CardFieldRow(title = it.title, value = dateConverted)
                    } else {
                        CardFieldRow(title = it.title, value = it.value)

                    }
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CardNodePreview() {

    val card = CardData(
        listOf(
            CardField("PublicKey", "939284798274290"),
            CardField("City", "São Paulo"),
            CardField("LastSeen", "939284798274290"),
            CardField("UpdateAt", "939284798274290"),
            CardField("Qualquer coisa", "qualquer coisa")
        )
    )

    RainbowBorderCard(cardData = card)

}