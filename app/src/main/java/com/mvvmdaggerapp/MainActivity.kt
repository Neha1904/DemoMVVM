package com.mvvmdaggerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mvvmdaggerapp.factory.PointViewModelFactory
import dagger.Binds
import dagger.Component
import dagger.Module

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}

@Component(modules = [PointModule::class])
interface PointComponent {
    fun inject (fragment: PointFragment)
}

@Module
abstract class PointModule {
    @Binds
    abstract fun bindViewModelFactory(factory:PointViewModelFactory):ViewModelProvider.Factory
}
