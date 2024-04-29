package android.test.coroutine;

import com.example.commonlibrary.fake_endpoint.FakeEndpoint;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class CoroutineModule_ProvideFakeEndpointFactory implements Factory<FakeEndpoint> {
  @Override
  public FakeEndpoint get() {
    return provideFakeEndpoint();
  }

  public static CoroutineModule_ProvideFakeEndpointFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FakeEndpoint provideFakeEndpoint() {
    return Preconditions.checkNotNullFromProvides(CoroutineModule.INSTANCE.provideFakeEndpoint());
  }

  private static final class InstanceHolder {
    private static final CoroutineModule_ProvideFakeEndpointFactory INSTANCE = new CoroutineModule_ProvideFakeEndpointFactory();
  }
}
