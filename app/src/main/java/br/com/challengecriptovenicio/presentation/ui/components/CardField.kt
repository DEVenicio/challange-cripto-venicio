package br.com.challengecriptovenicio.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun <T> CardFieldRow(
    modifier: Modifier = Modifier,
    title: String? = null,
    value: T? = null
    ) {

    Row(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .fillMaxWidth()
            .wrapContentHeight()

    ) {
           title?.let { Text(modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp), text =  "$it : ") }
           value?.let { Text(modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp), text = it.toString()) }
    }

}

@Preview(showBackground = true)
@Composable
private fun CardFieldPreview() {
    CardFieldRow(Modifier,"publicKey", "01283094281904389028")
}