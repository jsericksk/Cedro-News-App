package com.kproject.cedronews.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.kproject.cedronews.R
import com.kproject.cedronews.domain.model.NewsModel
import com.kproject.cedronews.domain.model.fakeNewsList
import com.kproject.cedronews.presentation.screens.components.CenterTopBar
import com.kproject.cedronews.presentation.screens.components.CustomImage
import com.kproject.cedronews.presentation.screens.components.EmptyListInfo
import com.kproject.cedronews.presentation.theme.CompletePreview
import com.kproject.cedronews.presentation.theme.PreviewTheme

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onNavigateToNewsReaderScreen: (newsId: Int) -> Unit
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val newsPagingItems = homeViewModel.news.collectAsLazyPagingItems()
    HomeScreenContent(
        uiState = uiState,
        onUiEvent = homeViewModel::onUiEvent,
        newsPagingItems = newsPagingItems,
        onNavigateToNewsReaderScreen = onNavigateToNewsReaderScreen
    )

    SideEffect {
        homeViewModel.onUiEvent(HomeUiEvent.KeepSplashOnScreenChanged)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenContent(
    uiState: HomeUiState,
    onUiEvent: (HomeUiEvent) -> Unit,
    newsPagingItems: LazyPagingItems<NewsModel>,
    onNavigateToNewsReaderScreen: (newsId: Int) -> Unit,
) {
    val themeIconResId = if (uiState.isDarkMode) {
        R.drawable.outline_light_mode_24
    } else {
        R.drawable.outline_dark_mode_24
    }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        topBar = {
            CenterTopBar(
                title = stringResource(id = R.string.app_name),
                actions = {
                    IconButton(onClick = { onUiEvent.invoke(HomeUiEvent.ChangeAppTheme) }) {
                        Icon(
                            painter = painterResource(id = themeIconResId),
                            contentDescription = null
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { paddingValues ->
        MainContent(
            onNavigateToNewsReaderScreen = onNavigateToNewsReaderScreen,
            newsPagingItems = newsPagingItems,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        )
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    newsPagingItems: LazyPagingItems<NewsModel>,
    onNavigateToNewsReaderScreen: (newsId: Int) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        when (newsPagingItems.loadState.refresh) {
            is LoadState.Loading -> {
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillParentMaxSize(),
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(80.dp)
                        )
                    }
                }
            }
            is LoadState.NotLoading -> {
                items(count = newsPagingItems.itemCount) { index ->
                    val news = newsPagingItems[index]
                    news?.let {
                        NewsListItem(
                            news = news,
                            onClick = {
                                onNavigateToNewsReaderScreen.invoke(news.id)
                            }
                        )
                    }
                }
            }
            is LoadState.Error -> {
                item {
                    EmptyListInfo(
                        iconResId = R.drawable.outline_error_24,
                        title = stringResource(id = R.string.error_loading_news),
                        modifier = Modifier.fillParentMaxSize()
                    )
                }
            }
        }

        when (newsPagingItems.loadState.append) {
            is LoadState.Loading -> {
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }
            is LoadState.Error -> {
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    ) {
                        Text(
                            text = stringResource(id = R.string.error_loading_more_news),
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(4.dp))
                        Button(
                            onClick = { newsPagingItems.retry() }
                        ) {
                            Text(
                                text = stringResource(id = R.string.button_retry)
                            )
                        }
                    }
                }
            }
            else -> {}
        }
    }
}

@Composable
private fun NewsList(
    modifier: Modifier = Modifier,
    newsList: List<NewsModel>,
    onNavigateToNewsReaderScreen: (newsId: Int) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(
            items = newsList,
            key = { index, news -> news.id }
        ) { index, news ->
            NewsListItem(
                news = news,
                onClick = {
                    onNavigateToNewsReaderScreen.invoke(news.id)
                }
            )
        }
    }
}

@Composable
private fun NewsListItem(
    modifier: Modifier = Modifier,
    news: NewsModel,
    onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                onClick.invoke()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(14.dp),
        ) {
            CustomImage(
                imageModel = news.imageUrl,
                modifier = Modifier
                    .size(100.dp)
                    .clip(MaterialTheme.shapes.small)
            )

            Spacer(Modifier.width(12.dp))

            Column {
                Text(
                    text = news.title,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(Modifier.height(6.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = news.category,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.secondary,
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(4.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = news.date,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }

                Spacer(Modifier.height(4.dp))

                Text(
                    text = news.elapsedTime,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

@CompletePreview
@Composable
private fun Preview() {
    PreviewTheme {
        NewsList(
            newsList = fakeNewsList,
            onNavigateToNewsReaderScreen = {}
        )
    }
}