package com.borscha.micka.playgroundproject.Activities.Activities.utilits;

import com.borscha.micka.playgroundproject.Activities.Activities.RecycleViews.BeaconRecycleView.Beacon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonHelper {
    public static Object toJSON(Object object) throws JSONException {
        if (object instanceof Map) {
            JSONObject json = new JSONObject();
            Map map = (Map) object;
            for (Object key : map.keySet()) {
                json.put(key.toString(), toJSON(map.get(key)));
            }
            return json;
        } else if (object instanceof Iterable) {
            JSONArray json = new JSONArray();
            for (Object value : ((Iterable)object)) {
                json.put(value);
            }
            return json;
        } else {
            return object;
        }
    }

    public static boolean isEmptyObject(JSONObject object) {
        return object.names() == null;
    }

    public static Map<String, Object> getMap(JSONObject object, String key) throws JSONException {
        return toMap(object.getJSONObject(key));
    }

    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap();
        Iterator keys = object.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            map.put(key, fromJson(object.get(key)));
        }
        return map;
    }

    public static List toList(JSONArray array) throws JSONException {
        List list = new ArrayList();
        for (int i = 0; i < array.length(); i++) {
            list.add(fromJson(array.get(i)));
        }
        return list;
    }

    private static Object fromJson(Object json) throws JSONException {
        if (json == JSONObject.NULL) {
            return null;
        } else if (json instanceof JSONObject) {
            return toMap((JSONObject) json);
        } else if (json instanceof JSONArray) {
            return toList((JSONArray) json);
        } else {
            return json;
        }
    }
    public static ArrayList<Beacon> JsonToArray(JSONArray jsonArray){
        ArrayList<Beacon> yourArray = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<Beacon>>(){}.getType());
        return yourArray;
    }

    public static Object convertJsonItem(Object o) throws JSONException {
        if (o == null) {
            return "null";
        }

        if (o instanceof JSONObject) {
            return getListFromJsonObject((JSONObject) o);
        }

        if (o instanceof JSONArray) {
            return getListFromJsonArray((JSONArray) o);
        }

        if (o.equals(Boolean.FALSE) || (o instanceof String &&
                ((String) o).equalsIgnoreCase("false"))) {
            return false;
        }

        if (o.equals(Boolean.TRUE) || (o instanceof String && ((String) o).equalsIgnoreCase("true"))) {
            return true;
        }

        if (o instanceof Number) {
            return o;
        }

        return o.toString();
    }

    @SuppressWarnings("unchecked")
    public static List<Object> getListFromJsonObject(JSONObject jObject) throws JSONException {
        List<Object> returnList = new ArrayList<Object>();
        Iterator<String> keys = jObject.keys();

        List<String> keysList = new ArrayList<String>();
        while (keys.hasNext()) {
            keysList.add(keys.next());
        }
        Collections.sort(keysList);

        for (String key : keysList) {
            List<Object> nestedList = new ArrayList<Object>();
            nestedList.add(key);
            nestedList.add(convertJsonItem(jObject.get(key)));
            returnList.add(nestedList);
        }

        return returnList;
    }
    public static List<Object> getListFromJsonArray(JSONArray jArray) throws JSONException {
        List<Object> returnList = new ArrayList<Object>();
        for (int i = 0; i < jArray.length(); i++) {
            returnList.add(convertJsonItem(jArray.get(i)));
        }
        return returnList;
    }
    public static List<String> getStringListFromJsonArray(JSONArray jArray) throws JSONException {
        List<String> returnList = new ArrayList<String>();
        for (int i = 0; i < jArray.length(); i++) {
            String val = jArray.getString(i);
            returnList.add(val);
        }
        return returnList;
    }
}