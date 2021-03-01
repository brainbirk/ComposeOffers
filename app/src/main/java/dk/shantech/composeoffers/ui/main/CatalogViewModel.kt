package dk.shantech.composeoffers.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dk.shantech.composeoffers.repository.ShopGunRepository
import javax.inject.Inject


@HiltViewModel
class CatalogViewModel @Inject constructor(private val repository: ShopGunRepository): ViewModel() {

    val catalogResult: MutableState<ShopGunRepository.CatalogResult> = mutableStateOf(ShopGunRepository.CatalogResult.Loading)

    private val radius: MutableState<Int> = mutableStateOf(100000)

    init {
        with(repository) {
            setLocation()
            setRadius(700000)
        }
        fetchCatalogs()
    }

    private fun fetchCatalogs() {
        repository.fetchCatalog({catalogResult.value = it})
    }

    fun updateRadius(radiusChange: Int) {
        radius.value = radiusChange
    }
}