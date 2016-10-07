package com.buddysearch.android.presentation.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;

import com.buddysearch.android.presentation.R;
import com.buddysearch.android.presentation.databinding.ActivitySplashBinding;
import com.buddysearch.android.presentation.di.component.ActivityComponent;
import com.buddysearch.android.presentation.mvp.presenter.SplashPresenter;
import com.buddysearch.android.presentation.mvp.view.SplashView;
import com.buddysearch.android.presentation.mvp.view.impl.SplashViewImpl;
import com.buddysearch.android.library.presentation.mvp.view.impl.ViewImpl;

import javax.inject.Inject;

import dagger.Lazy;

public class SplashActivity extends BaseDaggerActivity<SplashView, SplashPresenter, ActivitySplashBinding> {

    @Inject
    Lazy<SplashPresenter> splashPresenter;

    @Override
    public void onLoadFinished() {
        super.onLoadFinished();
        initSwipeToRefresh();
    }

    @Override
    protected SplashView initView() {
        return new SplashViewImpl(this) {

            @Override
            public void navigateToLogin() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void navigateToUsers() {
                startActivity(new Intent(SplashActivity.this, UsersActivity.class));
                finish();
            }
        };
    }

    @Override
    protected Lazy<SplashPresenter> initPresenter() {
        return splashPresenter;
    }

    @Override
    protected ActivitySplashBinding initBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_splash);
    }

    @Override
    protected void injectActivityComponent(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    private void initSwipeToRefresh() {
        ((ViewImpl) view).initSwipeToRefresh(binding.swipeToRefresh, presenter);
    }
}
