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
public final class SaveUserUseCase_Factory implements Factory<SaveUserUseCase> {
  private final Provider<UserRepository> userRepositoryProvider;

  public SaveUserUseCase_Factory(Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public SaveUserUseCase get() {
    return newInstance(userRepositoryProvider.get());
  }

  public static SaveUserUseCase_Factory create(Provider<UserRepository> userRepositoryProvider) {
    return new SaveUserUseCase_Factory(userRepositoryProvider);
  }

  public static SaveUserUseCase newInstance(UserRepository userRepository) {
    return new SaveUserUseCase(userRepository);
  }
}
