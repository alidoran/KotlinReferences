package alidoran.third_party.apis.rest.retrofit_java;

import javax.inject.Singleton;

import alidoran.third_party.apis.rest.retrofit_java.di.RetroModule;
import dagger.Component;

@Singleton
@Component(modules = {RetroModule.class})
public interface RetroComponent {
    void inject(RetroJavaViewModel retroJavaViewModel);
}
