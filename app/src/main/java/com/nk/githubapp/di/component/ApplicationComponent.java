package com.nk.githubapp.di.component;

import android.app.Application;
import com.nk.githubapp.GithubApp;
import com.nk.githubapp.di.module.ApplicationModule;
import com.nk.githubapp.di.module.NetModule;
import com.nk.githubapp.di.scope.PerApplication;
import com.nk.githubapp.respository.Repository;
import dagger.Component;

/**
 * Created by Administrator on 2017/6/21.
 */
@PerApplication
@Component(modules = {ApplicationModule.class, NetModule.class})
public interface ApplicationComponent {

    Application application();

    GithubApp githubApp();

    Repository repository();

}
