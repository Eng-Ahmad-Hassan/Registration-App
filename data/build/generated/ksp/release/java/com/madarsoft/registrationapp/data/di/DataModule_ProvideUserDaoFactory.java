package com.madarsoft.registrationapp.data.di;

import com.madarsoft.registrationapp.data.local.AppDatabase;
import com.madarsoft.registrationapp.data.local.dao.UserDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
    "KotlinInternalInJava",
    "cast"
})
public final class DataModule_ProvideUserDaoFactory implements Factory<UserDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DataModule_ProvideUserDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public UserDao get() {
    return provideUserDao(databaseProvider.get());
  }

  public static DataModule_ProvideUserDaoFactory create(Provider<AppDatabase> databaseProvider) {
    return new DataModule_ProvideUserDaoFactory(databaseProvider);
  }

  public static UserDao provideUserDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideUserDao(database));
  }
}
