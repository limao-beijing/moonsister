package com.moonsister.tcjy.utils.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * Created by jb on 2016/7/3.
 */
public class MyJsonDeserializer<T> implements JsonDeserializer<T> {
    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        TypeToken<?> typeToken = TypeToken.get(typeOfT);
        final JsonObject obj = json.getAsJsonObject(); //our original full json string
        final JsonElement serviceElement = obj.get("data");
        try {
            Class<?> aClass = Class.forName(typeToken.getType().toString());

            Field data = aClass.getDeclaredField("data");
            data.setAccessible(true);
            data.set( aClass.newInstance(),"");
            Object o = aClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        //here we provide the functionality to handle the naughty element. It seems emtpy string is returned as a JsonPrimitive... so one option
        if (serviceElement instanceof JsonPrimitive) {
            //it was empty do something
        }

        return null; //provide the functionality to take in the parsed data
    }


}
