package dk.shantech.composeoffers.repository

import com.shopgun.android.sdk.ShopGun
import com.shopgun.android.sdk.model.Catalog
import com.shopgun.android.sdk.network.ShopGunError
import com.shopgun.android.sdk.requests.LoaderRequest
import com.shopgun.android.sdk.requests.impl.CatalogListRequest
import javax.inject.Inject

class ShopGunRepository  @Inject constructor(val shopGun: ShopGun): Repository {

    fun setLocation(newLatitude: Double = 55.6310771, newLongitude: Double = 12.5771624) {
        shopGun.location.apply {
            latitude = newLatitude
            longitude = newLongitude
            isSensor = false
        }
    }

    fun setRadius(newRadius: Int) {
        shopGun.location.apply { radius = newRadius }
    }

    fun fetchCatalog(result: (result: CatalogResult) -> Unit, limit: Int = 24, offset: Int = 0) {
        result.invoke(CatalogResult.Loading)
        val r = CatalogListRequest(object : LoaderRequest.Listener<List<Catalog>> {
            override fun onRequestComplete(
                response: List<Catalog>?,
                errors: List<ShopGunError>
            ) {
                if (errors.isEmpty()) {
                    result.invoke(CatalogResult.Success(response?: emptyList()))
                } else {
                    result.invoke(CatalogResult.Error(errors = errors))
                }
            }

            override fun onRequestIntermediate(
                response: List<Catalog>,
                errors: List<ShopGunError>
            ) {}

        })
        r.loadStore(true)
        r.loadDealer(true)
        r.limit = limit
        r.offset = offset
        shopGun.add(r)
    }

    sealed class CatalogResult {
        data class Success(val catalogs: List<Catalog>): CatalogResult()
        data class Error(val errors: List<ShopGunError>): CatalogResult()
        object Loading: CatalogResult()
    }
}