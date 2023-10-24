package com.kproject.cedronews.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kproject.cedronews.R
import com.kproject.cedronews.presentation.theme.PreviewTheme
import com.kproject.cedronews.presentation.theme.SimplePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterTopBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: ImageVector? = null,
    navigationIconClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            navigationIcon?.let {
                IconButton(onClick = navigationIconClick) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = null
                    )
                }
            }
        },
        actions = actions,
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SimplePreview
@Composable
private fun Preview() {
    PreviewTheme {
        Column {
            CenterTopBar(
                title = stringResource(id = R.string.app_name),
                navigationIcon = Icons.Default.Menu
            )
            Spacer(Modifier.height(24.dp))
            CenterTopBar(
                title = stringResource(id = R.string.app_name),
                navigationIcon = Icons.Default.Menu,
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    }
}