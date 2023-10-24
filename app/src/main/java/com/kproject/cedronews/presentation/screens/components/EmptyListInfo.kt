package com.kproject.cedronews.presentation.screens.components


import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kproject.cedronews.R
import com.kproject.cedronews.presentation.theme.PreviewTheme

@Composable
fun EmptyListInfo(
    modifier: Modifier = Modifier,
    @DrawableRes iconResId: Int,
    title: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(8.dp),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = iconResId),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(120.dp)
        )
        Text(
            text = title,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 18.sp,
            fontWeight = Bold,
            modifier = Modifier.padding(all = 6.dp)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    PreviewTheme {
        EmptyListInfo(
            iconResId = R.drawable.outline_photo_24,
            title = "Nenhum dado foi carregado."
        )
    }
}