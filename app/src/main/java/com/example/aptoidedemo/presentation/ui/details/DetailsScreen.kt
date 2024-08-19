package com.example.aptoidedemo.presentation.ui.details

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.aptoidedemo.presentation.ui.resources.BaseScreen
import com.example.aptoidedemo.presentation.ui.resources.BasicTopBar

@Composable
fun DetailsScreen(modifier: Modifier = Modifier, viewModel: DetailsViewModel = hiltViewModel(), onBack: () -> Unit = {}) {
    when (val result = viewModel.detailsUiState) {
        is DetailsUiState.Loading -> {}
        is DetailsUiState.Success -> PhotosGridScreen(result.data, modifier = modifier.fillMaxWidth()) { onBack() }
        is DetailsUiState.Error -> {}
    }
}

/**
 * The home screen displaying photo grid.
 */
@Composable
fun PhotosGridScreen(
    photos: List<String>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onBack: () -> Unit,
) {
    BaseScreen(
        topBar = { BasicTopBar(title = "Apps") { onBack() } }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            modifier = modifier.padding(horizontal = 4.dp),
            contentPadding = contentPadding,
        ) {
            items(items = photos, key = { it }) { photo ->
                PhotoCard(
                    photo,
                    modifier = modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .aspectRatio(1.5f)
                )
            }
        }
    }
}

@Composable
fun PhotoCard(photo: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current).data(photo)
                .crossfade(true).build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}