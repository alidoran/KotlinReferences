package alidoran.apis.rest_retrofit_java;

import javax.inject.Singleton;

import alidoran.apis.rest_retrofit_java.di.RetroModule;
import dagger.Component;

@Singleton
@Component(modules = {RetroModule.class})
public interface RetroComponent {
    void inject(RestRetroJavaViewModel restRetroJavaViewModel);
}
