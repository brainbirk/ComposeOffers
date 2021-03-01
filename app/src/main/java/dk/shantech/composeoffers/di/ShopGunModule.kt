package dk.shantech.composeoffers.di

import android.content.Context
import android.util.Log
import com.shopgun.android.sdk.ShopGun
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dk.shantech.composeoffers.OffersApplication

@Module
@InstallIn(SingletonComponent::class)
object ShopGunModule {

    @Provides
    fun provideShopGunInstance(@ApplicationContext application: Context): ShopGun {
        if (!ShopGun.isInstantiated()) {
            Log.d("ShopGun", "Initializing ShopGun SDK")
            ShopGun.Builder(application as OffersApplication?).setDevelop(true).setInstance()
        }
        return ShopGun.getInstance()
    }
}