package com.zyl.ssm.utils;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {
	
	public static String toJson(Object object) {
		Gson gson = new Gson();

		try {
			return gson.toJson(object);
		} catch (Exception e) {
			return null;
		}
	}

	public static <T> T fromJson(String json, Class<T> classOfT) {
		Gson gson = new Gson();
		
		try {
			return gson.fromJson(json, classOfT);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static <T> T fromJson(String json, Type typeOfT) {
		Gson gson = new Gson();
		try {
			return gson.fromJson(json, typeOfT);
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
	/**
     * 解析客户端下发控制设备请求，Netty返回至Tomcat的Map<String,Integer>结果
     * @param responseInfo
     * @return
     */
    public static Map<String, Integer> fromJsonToMap(String responseInfo){
        Type type = new TypeToken<Map<String, Integer>>() {}.getType();
        Gson gson = new Gson();
        Map<String, Integer> responseEntity = gson.fromJson(responseInfo, type);
        return responseEntity;
    }
	
	/**
	 * 将map转换为对象
	 * @param map
	 * @param clazz
	 * @return
	 */
	public static <T> T convertObj(Map<String, String> map, Class<T> clazz){
		 return JsonUtil.fromJson(JsonUtil.toJson(map), clazz);
	}
	
	/*public static <T> Map<String, String> toMap(T t){
		Map<String, String> map = new HashMap<>();
		Field[] columns = t.getClass().getDeclaredFields();
		t.getClass().getFields();
		for (Field f : columns) {
			try {
				f.setAccessible(true);
				map.put(f.getName(), toJson(f.get(t)));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				System.out.println(e.toString());
				e.printStackTrace();
				return map;
			}
		}
		return map;
	}*/
	
	/**
	 * 讲对象的属性封装到map
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> toMap(Object obj){
		return fromJson(toJson(obj), Map.class);
	}
	
}