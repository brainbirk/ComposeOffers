package dk.shantech.composeoffers

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import dk.shantech.composeoffers.repository.ShopGunRepository.CatalogResult.*
import dk.shantech.composeoffers.ui.main.*
import dk.shantech.composeoffers.ui.theme.ComposeOffersTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeOffersTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    CatalogScreen()
                }
            }
        }
    }

    @Composable
    fun CatalogScreen(catalogsViewModel: CatalogViewModel = viewModel()) {
        when (val catalogResult = catalogsViewModel.catalogResult.value) {
            is Loading -> {
                Loading()
            }
            is Success -> {
                val catalogs = catalogResult.catalogs
                if (catalogs.isEmpty()) {
                    EmptyCatalogList()
                } else {
                    CatalogList(catalogs = catalogs)
                }
            }
            is Error -> {
                val errors = catalogResult.errors
                CatalogError(errors[0])
            }
        }
    }
}



