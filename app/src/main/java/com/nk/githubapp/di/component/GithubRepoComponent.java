package com.nk.githubapp.di.component;

import com.nk.githubapp.ui.activity.MainActivity;
import com.nk.githubapp.di.module.ActivityModule;
import com.nk.githubapp.di.module.GithubRepoModule;
import com.nk.githubapp.di.scope.PerActivity;
import dagger.Component;

/**
 * Created by Administrator on 2017/6/21.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = { ActivityModule.class, GithubRepoModule.class})
public interface GithubRepoComponent {

    void inject(MainActivity mainActivity);
}
