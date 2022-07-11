package alidoran.third_party.di.app_level_injection


import android.app.Application
import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

//This should be used on manifest too
class AppLevelInjectionApplication: Application() {

    lateinit var carAppLevelComponent : CarAppLevelComponent
    override fun onCreate() {
        super.onCreate()

        carAppLevelComponent = DaggerAppLevelInjectionApplication_CarAppLevelComponent.create()
    }

    fun carAppLevelComponent(): CarAppLevelComponent = carAppLevelComponent

    class EngineAppLevel @Inject constructor(){
        val type = "MyType"
    }

    @Singleton
    class CarAppLevel @Inject constructor(engineAppLevel: EngineAppLevel){
        init {
            println("App Level by ${engineAppLevel.type} was created")
        }
    }

    @Singleton
    @Component
    interface CarAppLevelComponent{
        fun getCar(): CarAppLevel
//        fun inject(appLevelActivity: AppLevelActivity)
    }
}