package com.moonsister.tcjy;

import android.app.Application;

import com.hickey.network.ServerApi;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by pc on 2016/6/12.
 */
@Singleton
@Component(modules = {AppModule.class, ServerApiModule.class})
public interface AppComponent {
    Application getApplication();

    ServerApi getServerApi();
}
