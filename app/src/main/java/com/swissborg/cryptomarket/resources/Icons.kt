package com.swissborg.cryptomarket.resources

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.swissborg.cryptomarket.R
import com.swissborg.cryptomarket.extension.empty

object Icons {

    @Composable
    fun ApplicationIcon() {
        Image(
            painter = painterResource(R.drawable.app_logo),
            contentDescription = String.empty,
            modifier = Modifier
                .padding(Dimens.paddingDefault)
                .size(Dimens.splashImageSize)
        )
    }

    @Composable
    fun SearchViewLeadingIcon() {
        Icon(
            Icons.Default.Search,
            contentDescription = String.empty,
            modifier = Modifier
                .padding(Dimens.paddingDefault)
                .size(Dimens.searchViewIconSize)
        )
    }

    @Composable
    fun SearchViewCloseIcon() {
        Icon(
            Icons.Default.Close,
            contentDescription = String.empty,
            modifier = Modifier
                .padding(Dimens.paddingDefault)
                .size(Dimens.searchViewIconSize)
        )
    }
}