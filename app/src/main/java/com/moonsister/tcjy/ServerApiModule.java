package com.moonsister.tcjy;

import com.hickey.network.ServerApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pc on 2016/6/12.
 */
@Module
public class ServerApiModule {
    private ServerApi serverApi;

    public ServerApiModule(ServerApi serverApi) {
        this.serverApi = serverApi;
    }

    @Provides
    @Singleton
    public ServerApi provideServerApi() {
        return serverApi;
    }
}
