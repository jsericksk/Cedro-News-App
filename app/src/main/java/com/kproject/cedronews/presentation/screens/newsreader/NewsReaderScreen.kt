package com.kproject.cedronews.presentation.screens.newsreader

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kproject.cedronews.R
import com.kproject.cedronews.commom.ResultState
import com.kproject.cedronews.presentation.screens.components.CenterTopBar
import com.kproject.cedronews.presentation.screens.components.CustomImage
import com.kproject.cedronews.presentation.screens.components.EmptyListInfo
import com.kproject.cedronews.presentation.screens.newsreader.model.NewsContent
import com.kproject.cedronews.presentation.screens.newsreader.model.fakeNewsContent
import com.kproject.cedronews.presentation.theme.CompletePreview
import com.kproject.cedronews.presentation.theme.PreviewTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsReaderScreen(
    onNavigateBack: () -> Unit
) {
    val newsReader = koinViewModel<NewsReaderViewModel>()
    val uiState by newsReader.uiState.collectAsStateWithLifecycle()
    NewsReaderScreenContent(
        uiState = uiState,
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewsReaderScreenContent(
    uiState: NewsReaderUiState,
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterTopBar(
                title = stringResource(id = R.string.news),
                navigationIcon = Icons.Filled.ArrowBack,
                navigationIconClick = onNavigateBack
            )
        },
    ) { paddingValues ->
        MainContent(
            uiState = uiState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        )
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    uiState: NewsReaderUiState,
) {
    val dataState = uiState.dataState
    Column(modifier = modifier) {
        when (dataState) {
            ResultState.Loading -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(60.dp)
                    )
                }
            }
            is ResultState.Success -> {
                dataState.result?.let { newsContent ->
                    NewsContent(newsContent = newsContent)
                }
            }
            is ResultState.Error -> {
                EmptyListInfo(
                    iconResId = R.drawable.outline_error_24,
                    title = stringResource(id = R.string.error_loading_news_content),
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun NewsContent(
    modifier: Modifier = Modifier,
    newsContent: NewsContent
) {
    SelectionContainer {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = newsContent.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(6.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_access_time_24),
                    contentDescription = null
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = newsContent.date.lowercase(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }

            if (newsContent.imageUrl.isNotBlank()) {
                Spacer(Modifier.height(10.dp))
                CustomImage(
                    imageModel = newsContent.imageUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(MaterialTheme.shapes.small)
                )
            }

            if (newsContent.subtitle.isNotBlank()) {
                Spacer(Modifier.height(6.dp))
                Text(
                    text = newsContent.subtitle,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Italic
                )
            }

            Spacer(Modifier.height(12.dp))

            Text(
                text = newsContent.content,
                fontSize = 16.sp
            )
        }
    }
}

@CompletePreview
@Composable
private fun Preview() {
    PreviewTheme {
        MainContent(
            uiState = NewsReaderUiState(
                dataState = ResultState.Success(data = fakeNewsContent)
            )
        )
    }
}