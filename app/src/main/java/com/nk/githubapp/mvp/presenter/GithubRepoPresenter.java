package com.nk.githubapp.mvp.presenter;

import com.nk.githubapp.http.entity.GithubUserBean;
import com.nk.githubapp.mvp.contract.GithubRepoContract;
import com.nk.githubapp.respository.Repository;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/6/21.
 */

public class GithubRepoPresenter implements GithubRepoContract.Presenter {

    private GithubRepoContract.View mView;
    private CompositeSubscription mCompositeSubscription;
    private Repository mRespository;


    public GithubRepoPresenter(Repository repository) {
        this.mRespository = repository;
    }


    @Override
    public void attachView(GithubRepoContract.View view) {
        this.mView = view;
        mCompositeSubscription = new CompositeSubscription();
    }


    @Override public void onStop() {
        if (mCompositeSubscription != null && mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
    }


    @Override public void refresh(String name) {
        Subscription subscription = mRespository.getUserRepos(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn(new Func1<Throwable, GithubUserBean>() {
                @Override public GithubUserBean call(Throwable throwable) {
                    String error = "error ";
                    if (throwable != null) {
                        error = throwable.getMessage();
                    }

                    mView.showError(error);
                    return null;
                }
            })
            .subscribe(new Action1<GithubUserBean>() {
                @Override public void call(GithubUserBean reposListBean) {
                    if (reposListBean != null) {
                        mView.showRepo(reposListBean);
                    }
                }
            });
        mCompositeSubscription.add(subscription);
    }
}
