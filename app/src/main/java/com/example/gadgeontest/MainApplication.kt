package com.example.gadgeontest

import android.app.Application
import android.content.Context
import com.example.gadgeontest.data.network.MyApi
import com.example.gadgeontest.data.network.NetworkConnectionInterceptor
import com.example.gadgeontest.data.repositories.UserRepositories
import com.example.gadgeontest.viewmodel.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Created by Noushad N on 06-06-2022.
 */
class MainApplication  : Application(), KodeinAware {
    companion object {
        lateinit var instance: MainApplication
            private set
        val appContext: Context
            get() {
                return instance.applicationContext
            }
    }

    init {
        instance = this
    }
    override fun onCreate() {
        super.onCreate()

    }
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MainApplication))
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { UserRepositories(instance()) }
        bind() from provider { ViewModelFactory(instance()) }

    }

}