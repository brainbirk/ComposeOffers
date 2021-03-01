package dk.shantech.composeoffers.di

import com.shopgun.android.sdk.ShopGun
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dk.shantech.composeoffers.repository.ShopGunRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideShopGunRepository(shopGun: ShopGun): ShopGunRepository {
        return ShopGunRepository(shopGun)
    }
}

