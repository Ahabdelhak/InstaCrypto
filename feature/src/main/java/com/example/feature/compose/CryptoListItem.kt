/*
package com.example.feature.ui.coinHome

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature.data.DataProvider
import com.example.feature.data.model.Crypto
import com.example.feature.compose.theme.graySurface

@Composable
fun CryptoListItem(crypto: Crypto, navigateToDetails: (Crypto) -> Unit,) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = graySurface,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {
        Row(Modifier.clickable { navigateToDetails(crypto) }) {
            CryptoImage(crypto)
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = crypto.title, style = typography.h6)
                Text(text = crypto.price, style = typography.caption)
            }
        }
    }
}

@Composable
private fun CryptoImage(crypto: Crypto) {
    Image(
        painter = painterResource(id = crypto.CryptoImageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
}

@Preview
@Composable
fun PreviewCryptoItem() {
    val crypto = DataProvider.crypto
    CryptoListItem(crypto = crypto, navigateToDetails = {})
}
*/
