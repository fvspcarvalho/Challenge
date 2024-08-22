package com.example.aptoidedemo.presentation.ui.resources


import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.RenderVectorGroup
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.FillBounds
import androidx.compose.ui.layout.ContentScale.Companion.FillHeight
import androidx.compose.ui.layout.ContentScale.Companion.FillWidth
import androidx.compose.ui.layout.ContentScale.Companion.Fit
import androidx.compose.ui.layout.ContentScale.Companion.Inside
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.imageLoader
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.aptoidedemo.R
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher

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
                .border(1.dp, color = Color.White),
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
        )  {
            Text(modifier = Modifier.fillMaxWidth(), text = title, maxLines = 1, overflow = TextOverflow.Ellipsis)
            RatingCard(modifier = Modifier.fillMaxWidth(), rating = rating)
        }
    }
}

@Composable
fun DetailsCard(
    icon: String,
    photo: String,
    downloads: String,
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
                        .border(1.dp, color = Color.White),
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
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Store name: $storeName",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            InfoCard(
                modifier = Modifier.fillMaxWidth(),
                rating = rating,
                downloads = downloads
            )

            val vectorPainter = rememberVectorPainterWithAlpha()
            val imageRequest = ImageRequest.Builder(context)
                .data(photo)
                .dispatcher(Dispatchers.IO)
                .build()

            Box(
                modifier = Modifier.border(1.dp, color = Color.White),
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

            Button(onClick = onClick) { Text(text = "Download") }
        }
    }
}


@Composable
fun InfoCard(rating: String, downloads: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Rating: $rating")

        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "star"
        )

        VerticalDivider()

        Text(text = "Downloads: $downloads")

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
            contentDescription = "star"
        )
        Text(text = rating)
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