package com.dayker.viewed.watched.feature.moviesearch.presentation.components


import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dayker.viewed.watched.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    query: String,
) {
    var searchValue by remember {
        mutableStateOf(query)
    }
    val interactionSource = remember { MutableInteractionSource() }
    val disabledTextSelectionColors = TextSelectionColors(
        handleColor = Color.Transparent,
        backgroundColor = Color.Transparent
    )
    CompositionLocalProvider(LocalTextSelectionColors provides disabledTextSelectionColors) {
        BasicTextField(
            value = searchValue,
            onValueChange = {
                searchValue = it
                onValueChange(it)
            },
            modifier = modifier
                .fillMaxWidth(),
            singleLine = true,
            interactionSource = interactionSource,
            textStyle = MaterialTheme.typography.displayLarge.copy(
                fontSize = 21.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground)
        ) { textField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = searchValue,
                visualTransformation = VisualTransformation.None,
                innerTextField = textField,
                singleLine = true,
                enabled = true,
                interactionSource = interactionSource,
                contentPadding = OutlinedTextFieldDefaults.contentPadding(
                    top = 10.dp,
                    bottom = 8.dp
                ),
                placeholder = {
                    Text(
                        modifier = Modifier
                            .padding(1.dp),
                        text = stringResource(R.string.search),
                        style = MaterialTheme.typography.displayLarge.copy(fontSize = 20.sp)
                    )
                },
                container = {
                    OutlinedTextFieldDefaults.ContainerBox(
                        enabled = true,
                        isError = false,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            disabledBorderColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                            focusedTextColor = MaterialTheme.colorScheme.onBackground,
                            disabledTextColor = MaterialTheme.colorScheme.onBackground,
                            cursorColor = Color.Unspecified
                        ),
                        interactionSource = interactionSource,
                        shape = RoundedCornerShape(12.dp),
                        unfocusedBorderThickness = 3.dp,
                        focusedBorderThickness = 3.dp,
                    )
                }
            )
        }
    }
}