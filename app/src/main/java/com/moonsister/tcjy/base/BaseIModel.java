package com.moonsister.tcjy.base;

import java.util.List;

/**
 * Created by pc on 2016/6/14.
 */
public interface BaseIModel {

    public interface onLoadDateSingleListener<T> {
        /**
         * 数据加载成功
         *
         * @param t
         * @param
         */
        void onSuccess(T t, DataType dataType);

        /**
         * 数据加载失败
         *
         * @param msg
         */
        void onFailure(String msg);


    }

    public interface onDownFileleListener<T> {
        /**
         * 成功
         *
         * @param
         */
        void onDownFileleSuccess();

        /**
         * 数据加载失败
         *
         * @param msg
         */
        void onDownFileleFailure(String msg);

        /**
         * 下载进度
         *
         * @param progress
         * @param total
         * @param done
         */
        void onDownFileleProgress(long progress, long total, boolean done);
    }

    /**
     * 列表数据
     *
     * @param <T>
     */
    public interface onLoadListDateListener<T> {
        /**
         * 数据加载成功
         *
         * @param
         * @param
         */
        void onSuccess(List<T> t, DataType dataType);

        /**
         * 数据加载失败
         *
         * @param msg
         */
        void onFailure(String msg);


    }

    public enum DataType {
        DATA_ZERO(0), DATA_ONE(1), DATA_TWO(2), DATA_THREE(3), DATA_FOUR(4);
        private int value;

        private DataType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
