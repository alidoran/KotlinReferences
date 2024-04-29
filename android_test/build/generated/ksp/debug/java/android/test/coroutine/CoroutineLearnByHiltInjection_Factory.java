package android.test.coroutine;

import com.example.commonlibrary.fake_endpoint.FakeEndpoint;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class CoroutineLearnByHiltInjection_Factory implements Factory<CoroutineLearnByHiltInjection> {
  private final Provider<FakeEndpoint> fakeEndpointProvider;

  public CoroutineLearnByHiltInjection_Factory(Provider<FakeEndpoint> fakeEndpointProvider) {
    this.fakeEndpointProvider = fakeEndpointProvider;
  }

  @Override
  public CoroutineLearnByHiltInjection get() {
    return newInstance(fakeEndpointProvider.get());
  }

  public static CoroutineLearnByHiltInjection_Factory create(
      Provider<FakeEndpoint> fakeEndpointProvider) {
    return new CoroutineLearnByHiltInjection_Factory(fakeEndpointProvider);
  }

  public static CoroutineLearnByHiltInjection newInstance(FakeEndpoint fakeEndpoint) {
    return new CoroutineLearnByHiltInjection(fakeEndpoint);
  }
}
