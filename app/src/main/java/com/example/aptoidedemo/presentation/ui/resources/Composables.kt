package com.example.aptoidedemo.presentation.ui.resources


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.RenderVectorGroup
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale.Companion.FillWidth
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.aptoidedemo.R
import kotlinx.coroutines.Dispatchers


@Composable
fun MyAlertDialog(
    title: String = stringResource(id = R.string.dialog_title),
    subTitle: String = stringResource(id = R.string.dialog_message),
    btnTitle: String = stringResource(id = R.string.btn_dialog),
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = title) },
        text = { Text(text = subTitle) },
        confirmButton = { // 6
            Button(onClick = onDismiss) {
                Text(
                    text = btnTitle,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    )
}

@Composable
fun AppCard(
    photo: String,
    title: String,
    rating: String,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val vectorPainter = rememberVectorPainterWithAlpha()
        val imageRequest = ImageRequest.Builder(context)
            .data(photo)
            .dispatcher(Dispatchers.IO)
            .build()

        Box(
            modifier = Modifier
                .weight(1f)
                .border(1.dp, color = MaterialTheme.colorScheme.onPrimary),
            contentAlignment = Alignment.Center
        ) {
            SubcomposeAsyncImage(
                model = imageRequest,
                contentDescription = null,
                contentScale = FillWidth,
                loading = { MaxSizeLoading() },
                error = {
                    Image(
                        painter = vectorPainter,
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        }

        Column(
            modifier = Modifier
                .weight(2f)
                .padding(start = 10.dp),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onPrimary
            )
            RatingCard(modifier = Modifier.fillMaxWidth(), rating = rating)
        }
    }
}

@Composable
fun DetailsCard(
    icon: String,
    photo: String,
    downloads: Long,
    name: String,
    storeName: String,
    rating: String,
    onClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            val context = LocalContext.current
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val vectorPainter = rememberVectorPainterWithAlpha()
                val imageRequest = ImageRequest.Builder(context)
                    .data(icon)
                    .dispatcher(Dispatchers.IO)
                    .build()

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .border(1.dp, color = MaterialTheme.colorScheme.onPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    SubcomposeAsyncImage(
                        model = imageRequest,
                        contentDescription = null,
                        contentScale = FillWidth,
                        loading = { MaxSizeLoading() },
                        error = {
                            Image(
                                painter = vectorPainter,
                                contentDescription = null,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(2f)
                        .padding(start = 10.dp),
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "App name: $name",
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Store name: $storeName",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
            InfoCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                rating = rating,
                downloads = adaptLongNumber(downloads)
            )

            val vectorPainter = rememberVectorPainterWithAlpha()
            val imageRequest = ImageRequest.Builder(context)
                .data(photo)
                .dispatcher(Dispatchers.IO)
                .build()

            Box(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .border(1.dp, color = MaterialTheme.colorScheme.onPrimary),
                contentAlignment = Alignment.Center
            ) {
                SubcomposeAsyncImage(
                    model = imageRequest,
                    contentDescription = null,
                    contentScale = FillWidth,
                    loading = { MaxSizeLoading() },
                    error = {
                        Image(
                            painter = vectorPainter,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                )
            }

            Button(
                onClick = onClick,
                modifier = Modifier.padding(top = 10.dp)
            ) { Text(text = "Download") }
        }
    }
}


@Composable
fun InfoCard(rating: String, downloads: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Text(text = "Rating: $rating", color = MaterialTheme.colorScheme.onPrimary)

            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "star",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Text(text = "Downloads: $downloads", color = MaterialTheme.colorScheme.onPrimary)

    }
}

@Composable
fun RatingCard(rating: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "star",
            tint = MaterialTheme.colorScheme.primary
        )
        Text(text = rating, color = MaterialTheme.colorScheme.onPrimary)
    }
}


@Composable
fun rememberVectorPainterWithAlpha(
    color: Color = Color.White,
    alpha: Float = 0.5f,
    imageVector: ImageVector = Icons.Default.Warning
): VectorPainter {
    return rememberVectorPainter(
        defaultWidth = imageVector.defaultWidth,
        defaultHeight = imageVector.defaultHeight,
        viewportWidth = imageVector.viewportWidth,
        viewportHeight = imageVector.viewportHeight,
        name = imageVector.name,
        tintColor = color.copy(alpha = alpha),
        tintBlendMode = imageVector.tintBlendMode,
        autoMirror = imageVector.autoMirror,
        content = { _, _ -> RenderVectorGroup(group = imageVector.root) }
    )
}

private fun adaptLongNumber(value: Long): String {
    return when {
        value >= 10000000 -> "+1M"
        value >= 5000000 -> "+500k"
        value >= 1000000 -> "+100k"
        value >= 10000 -> "+10k"
        value >= 1000 -> "+1k"
        value > 100 -> "+100"
        else -> value.toString()
    }
}

@Preview
@Composable
private fun RatingCardPreviwew() {
    RatingCard(rating = "4.5")
}

@Preview
@Composable
private fun InfoCardPreviwew() {
    InfoCard(rating = "4.5", downloads = "2000")
}

@Preview
@Composable
private fun AppCardPreviwew() {
    Surface(onClick = { /*TODO*/ }, modifier = Modifier.width(400.dp)) {
        AppCard(photo = "dfgh", title = "Name Of App", rating = "4.5") {}
    }
}