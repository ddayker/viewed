package com.dayker.viewed.details.feature.details.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dayker.details.R
import com.dayker.viewed.authentication.domain.module.User
import com.dayker.viewed.core.ui.components.animatedBorder
import com.dayker.viewed.details.feature.details.DetailsScreenEvent

@Composable
internal fun UserInfo(
    modifier: Modifier = Modifier,
    user: User,
    isExpanded: Boolean,
    onEvent: (DetailsScreenEvent) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onEvent(DetailsScreenEvent.OnClickUserInfo)
            }
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .animatedBorder(
                    borderColors = listOf(
                        Color(66, 133, 244),
                        Color(234, 66, 53),
                        Color(251, 180, 48),
                        Color(52, 168, 83)
                    ),
                    backgroundColor = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(8.dp),
                    borderWidth = 2.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(user.profilePictureUrl)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.google_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .padding(15.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Text(
                        text = user.username ?: user.email
                        ?: stringResource(R.string.you_are_logged_in),
                        modifier = Modifier.padding(start = 5.dp, end = 15.dp),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.labelMedium.copy(fontSize = 16.sp),
                        overflow = TextOverflow.Ellipsis
                    )
                }
                AnimatedVisibility(visible = isExpanded) {
                    Column {
                        Divider(
                            modifier = Modifier
                                .padding(start = 15.dp, end = 15.dp, bottom = 20.dp)
                                .height(2.dp),
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                        if (!user.username.isNullOrEmpty() && !user.email.isNullOrEmpty()) {
                            Text(
                                text = user.email ?: stringResource(R.string.you_are_logged_in),
                                modifier = Modifier.padding(start = 20.dp, bottom = 10.dp),
                                color = MaterialTheme.colorScheme.outline,
                                style = MaterialTheme.typography.labelMedium.copy(fontSize = 15.sp)
                            )
                        }
                        Button(
                            onClick = {
                                onEvent(DetailsScreenEvent.OnSignOutClicked)
                            },
                            modifier = Modifier.padding(start = 20.dp, bottom = 10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                contentColor = MaterialTheme.colorScheme.background
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.sign_out),
                                style = MaterialTheme.typography.labelMedium.copy(fontSize = 14.sp)
                            )
                        }
                    }
                }
            }
        }
    }
}
