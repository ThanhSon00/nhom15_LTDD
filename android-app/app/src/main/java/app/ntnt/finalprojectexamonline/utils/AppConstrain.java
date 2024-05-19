package app.ntnt.finalprojectexamonline.utils;

import android.content.Context;
import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AppConstrain {
    public static MultipartBody.Part toPart(Context context, Uri uri){
        if (uri == null)
            return null;
        String IMAGE_PATH = RealPathUtil.getRealPath(context, uri);
        File file = new File(IMAGE_PATH);
        byte[] fileBytes;
        try (FileInputStream inputStream = new FileInputStream(file)) {
            fileBytes = new byte[(int) file.length()];
            inputStream.read(fileBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        RequestBody requestFile = RequestBody.create(file, MediaType.parse("image/*"));
        return MultipartBody.Part.createFormData("image", file.getName(), requestFile);

    }

    public static Object toObject(Object object, Class<?> S ){
        Gson gson = new Gson();
        LinkedTreeMap<String, Object> objectLinkedTreeMap = (LinkedTreeMap<String, Object>) object;
        return gson.fromJson(gson.toJson(objectLinkedTreeMap), S);
    }

    public static List<Object> objectList (Object object, Class<?> S){
        Gson gson = new Gson();
        ArrayList<LinkedTreeMap<String, Object>> datas = (ArrayList<LinkedTreeMap<String, Object>>) object;
        List<Object> objects = new ArrayList<>();
        for (LinkedTreeMap<String, Object> i : datas){
            objects.add(gson.fromJson(gson.toJson(i), S));
        }
        return objects;
    }
}
