package com.madarsoft.registrationapp.domain.usecase;

import com.madarsoft.registrationapp.domain.repository.UserRepository;
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
    "KotlinInternalInJava",
    "cast"
})
public final class GetAllUsersUseCase_Factory implements Factory<GetAllUsersUseCase> {
  private final Provider<UserRepository> userRepositoryProvider;

  public GetAllUsersUseCase_Factory(Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public GetAllUsersUseCase get() {
    return newInstance(userRepositoryProvider.get());
  }

  public static GetAllUsersUseCase_Factory create(Provider<UserRepository> userRepositoryProvider) {
    return new GetAllUsersUseCase_Factory(userRepositoryProvider);
  }

  public static GetAllUsersUseCase newInstance(UserRepository userRepository) {
    return new GetAllUsersUseCase(userRepository);
  }
}
