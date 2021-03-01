package dk.shantech.composeoffers.ui.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shopgun.android.sdk.model.Catalog
import com.shopgun.android.sdk.network.ShopGunError
import dev.chrisbanes.accompanist.glide.GlideImage
import dk.shantech.composeoffers.extension.getWeekDay
import dk.shantech.composeoffers.extension.isDateInThePast
import dk.shantech.composeoffers.ui.theme.ComposeOffersTheme
import dk.shantech.composeoffers.ui.theme.White
import java.util.*

@Composable
fun CatalogList(catalogs: List<Catalog>) {
    LazyColumn {
        items(catalogs) { catalog ->
            CatalogCard(
                logoUrl = catalog.images.thumb,
                brandingName = catalog.branding.name,
                from = catalog.runFrom,
                to = catalog.runTill
            )
        }
    }
}

@Composable
fun CatalogCard(logoUrl: String, brandingName: String, from: Date, to: Date) {
    Card(
        border = BorderStroke(1.dp, White),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageLoader(url = logoUrl)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                CatalogTitle(brandingName)
               CatalogTime(from = from, to = to)
            }
        }
    }
    Spacer(modifier = Modifier.height(2.dp))
}

@Composable
private fun CatalogTitle(brandingName: String) {
    Text(
        text = brandingName,
        fontSize = 22.sp,
    )
}

@Preview
@Composable
fun PreviewCatalogTitle() {
    ComposeOffersTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            CatalogTitle(brandingName = "Netto")
        }
    }
}

@Composable
fun ImageLoader(url: String) {
    Box(modifier = Modifier
        .height(64.dp)
        .width(64.dp)
        .padding(8.dp),
        contentAlignment = Center
    ) {
        GlideImage(
            data = url,
            contentDescription = "My content description",
            fadeIn = true,
            loading = {
                Box(Modifier.matchParentSize()) {
                    CircularProgressIndicator(Modifier.align(Center), Color.Black)
                }
            }
        )
    }
}


@Composable
fun EmptyCatalogList() {
//    var slider: Float by remember { mutableStateOf(0.4f)}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "No catalogs available", fontSize = 24.sp)
        Text(text = "Try changing the SDK location, or increase the radius.")
        //Slider(value = slider, onValueChange = { slider = it }, ma)
    }
}

@Preview
@Composable
fun PreviewEmptyList() {
    ComposeOffersTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            EmptyCatalogList()
        }
    }
}

@Composable
fun Loading() {
//    var slider: Float by remember { mutableStateOf(0.4f)}
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
fun PreviewLoading() {
    ComposeOffersTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            Loading()
        }
    }
}

@Composable
fun CatalogError(shopGunError: ShopGunError) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        val title = if(shopGunError.isApi) "Api Error" else "SDK Error"
        Text(text = title, fontSize = 24.sp)
        Text(text = shopGunError.details)
    }
}


@Composable
fun CatalogTime(from: Date, to: Date) {
    if (from.isDateInThePast()) {
        Text(text = "Til og med ${to.getWeekDay()}")
    } else {
        Text(text = "Fra og med ${from.getWeekDay()}")
    }

}