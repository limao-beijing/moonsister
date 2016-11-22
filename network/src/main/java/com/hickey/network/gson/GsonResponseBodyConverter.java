/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hickey.network.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;

import okhttp3.ResponseBody;
import okio.BufferedSource;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;
    private final Gson gson;
    private final TypeToken<?> type;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter, TypeToken<?> type) {
        this.adapter = adapter;
        this.gson = gson;
        this.type = type;

    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String s = null;
        try {
            BufferedSource source = value.source();
            s = source.readUtf8();


            return adapter.fromJson(s);
//            new
//            return adapter.fromJson(value.charStream());
        } catch (Exception e) {

            try {
                String className = type.getType().toString();
                JSONObject object = new JSONObject(s);
                String code = object.getString("code");
                String msg = object.getString("msg");

                String clz = className.substring(className.indexOf("com"));
                Class<?> aClass = Class.forName(clz);
                Field data = aClass.getDeclaredField("data");


                Class<?> superclass = aClass.getSuperclass();
                Field codeData = null;
                Field msgData = null;
                if (superclass != null && superclass != Object.class) {
                    codeData = superclass.getDeclaredField("code");
                    msgData = superclass.getDeclaredField("msg");
                } else {
                    codeData = aClass.getDeclaredField("code");
                    msgData = aClass.getDeclaredField("msg");
                }

                Object o = aClass.newInstance();


                data.setAccessible(true);
                data.set(o, null);
                codeData.setAccessible(true);
                codeData.set(o, code);
                msgData.setAccessible(true);
                msgData.set(o, msg);


                return (T) o;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
//            return gson.fromJson(s, (Class<T>) BaseBean.class);
        } finally {
            value.close();
        }
        return null;
    }
}
