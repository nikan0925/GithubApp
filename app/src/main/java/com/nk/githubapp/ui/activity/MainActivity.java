package com.nk.githubapp.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.nk.githubapp.GithubApp;
import com.nk.githubapp.R;
import com.nk.githubapp.di.component.ApplicationComponent;
import com.nk.githubapp.di.component.DaggerGithubRepoComponent;
import com.nk.githubapp.di.component.GithubRepoComponent;
import com.nk.githubapp.di.module.ActivityModule;
import com.nk.githubapp.di.module.GithubRepoModule;
import com.nk.githubapp.http.entity.GithubUserBean;
import com.nk.githubapp.multitype.GithubRepoViewBinder;
import com.nk.githubapp.mvp.contract.GithubRepoContract;
import com.nk.githubapp.widget.DividerItemDecoration;
import javax.inject.Inject;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class MainActivity extends AppCompatActivity
    implements GithubRepoContract.View, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;

    private String[] nameList = { "JakeWharton", "ruanyf", "Jacksgong", "haoel", "programthink",
        "Trinea", "stormzhang", "drakeet", "ZhaoKaiQiang", "motianhuo", "hongyangAndroid" };

    @Inject
    GithubRepoContract.Presenter mPresenter;

    private MultiTypeAdapter mAdapter;
    private Items items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        injectDependences();
        mPresenter.attachView(this);
    }


    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refersh);

        refreshLayout.setRefreshing(true);
        refreshLayout.setOnRefreshListener(this);

        items = new Items();
        mAdapter = new MultiTypeAdapter();
        mAdapter.register(GithubUserBean.class, new GithubRepoViewBinder(this));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,
            DividerItemDecoration.VERTICAL_LIST);
        itemDecoration.setHeight(2);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }


    @Override protected void onResume() {
        super.onResume();
        for (int i = 0; i < nameList.length; i++) {
            mPresenter.refresh(nameList[i]);
        }
    }


    @Override protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }


    private void injectDependences() {
        ApplicationComponent applicationComponent
            = ((GithubApp) getApplication()).getApplicationComponent();
        GithubRepoComponent component = DaggerGithubRepoComponent.builder()
            .applicationComponent(applicationComponent)
            .activityModule(new ActivityModule(this))
            .githubRepoModule(new GithubRepoModule())
            .build();

        component.inject(this);
    }


    @Override public void showError(String errorstring) {
        refreshLayout.setRefreshing(false);
        Toast.makeText(this, errorstring, Toast.LENGTH_SHORT).show();
    }


    @Override public void showRepo(GithubUserBean reposListBean) {
        refreshLayout.setRefreshing(false);

        if (reposListBean != null) {
            items.add(reposListBean);
            mAdapter.setItems(items);
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override public void onRefresh() {
        items.clear();
        for (int i = 0; i < nameList.length; i++) {
            mPresenter.refresh(nameList[i]);
        }
    }
}
