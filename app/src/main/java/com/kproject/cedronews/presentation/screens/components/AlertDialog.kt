package com.kproject.cedronews.presentation.screens.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.sp
import com.kproject.cedronews.R
import com.kproject.cedronews.presentation.theme.CompletePreview
import com.kproject.cedronews.presentation.theme.PreviewTheme

@Composable
fun SimpleAlertDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    @DrawableRes iconResId: Int? = null,
    title: String? = null,
    message: String,
    cancelable: Boolean = true,
    showButtonCancel: Boolean = true,
    okButtonTitle: String = stringResource(id = R.string.button_ok),
    cancelButtonTitle: String = stringResource(id = R.string.button_cancel),
    onClickButtonOk: () -> Unit,
    onClickButtonCancel: () -> Unit = {}
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { if (cancelable) onDismiss.invoke() },
            icon = {
                iconResId?.let {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = iconResId),
                        contentDescription = null
                    )
                }
            },
            title = {
                title?.let {
                    Text(
                        text = title,
                        fontSize = 20.sp
                    )
                }
            },
            text = {
                Text(
                    text = message,
                    fontSize = 16.sp
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDismiss.invoke()
                        onClickButtonOk.invoke()
                    }
                ) {
                    Text(
                        text = okButtonTitle,
                        fontSize = 16.sp
                    )
                }
            },
            dismissButton = {
                if (showButtonCancel) {
                    TextButton(
                        onClick = {
                            onDismiss.invoke()
                            onClickButtonCancel.invoke()
                        }
                    ) {
                        Text(
                            text = cancelButtonTitle,
                            fontSize = 16.sp
                        )
                    }
                }
            },
        )
    }
}

@CompletePreview
@Composable
private fun Preview() {
    PreviewTheme {
        SimpleAlertDialog(
            showDialog = true,
            onDismiss = {},
            title = "Tema do Aplicativo",
            message = "Selecione o tema do aplicativo.",
            onClickButtonOk = {}
        )
    }
}