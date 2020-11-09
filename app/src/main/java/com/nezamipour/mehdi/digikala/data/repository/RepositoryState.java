package com.nezamipour.mehdi.digikala.data.repository;

import com.nezamipour.mehdi.digikala.util.enums.ConnectionState;

public class RepositoryState<T> {

    private ConnectionState mConnectionState;
    private T mData;
    private Exception mException;

    public RepositoryState(ConnectionState connectionState, T data, Exception exception) {
        mConnectionState = connectionState;
        mData = data;
        mException = exception;
    }


}
