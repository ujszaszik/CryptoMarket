package com.swissborg.cryptomarket.compose.screen.filteredlist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.swissborg.cryptomarket.compose.keyboard.KeyboardStyle
import com.swissborg.cryptomarket.compose.keyboard.LocalKeyboardManager
import com.swissborg.cryptomarket.extension.empty
import com.swissborg.cryptomarket.resources.Colors
import com.swissborg.cryptomarket.resources.Dimens
import com.swissborg.cryptomarket.resources.Icons

@Composable
fun FilterSearchView(
    onSearchRequest: (String) -> Unit,
    onResetRequest: () -> Unit
) {

    var searchText by rememberSaveable { mutableStateOf(String.empty) }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val keyboardManager = LocalKeyboardManager.current
    val keyboardStyle = KeyboardStyle(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Search,
        keyboardAction = {
            onSearchRequest(searchText)
            focusManager.clearFocus()
            keyboardManager.hide()
        }
    )

    TextField(
        value = searchText,
        onValueChange = { value ->
            searchText = value
        },
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        textStyle = TextStyle(
            color = Colors.appBlue,
            fontSize = Dimens.highlightTextSize
        ),
        leadingIcon = { Icons.SearchViewLeadingIcon() },
        trailingIcon = {
            if (searchText.isNotEmpty()) {
                IconButton(
                    onClick = {
                        searchText = String.empty
                        onResetRequest()
                        focusManager.clearFocus()
                        keyboardManager.hide()
                    }
                ) { Icons.SearchViewCloseIcon() }
            }
        },
        keyboardOptions = keyboardStyle.keyboardOptions,
        keyboardActions = keyboardStyle.keyboardActions,
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Colors.appBlue,
            cursorColor = Colors.appBlue,
            leadingIconColor = Colors.appBlue,
            trailingIconColor = Colors.gray,
            backgroundColor = Colors.white,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}