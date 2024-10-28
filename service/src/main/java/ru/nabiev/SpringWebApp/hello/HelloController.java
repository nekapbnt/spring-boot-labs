package ru.nabiev.SpringWebApp.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class HelloController {
    public static ArrayList<String> arrayList;
    public static HashMap<Integer, String> hashMap;
    private Integer hashMapIndex = 0;

    @GetMapping("/hello")
    public String hello(@RequestParam (value = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s", name);
    }

    @GetMapping("/update-array")
    public String updateArrayList(@RequestParam(value = "s") String s) {
        if (arrayList == null || arrayList.isEmpty())
            arrayList = new ArrayList<String>();
        arrayList.add(s);
        return "";
    }

    @GetMapping("/show-array")
    public String showArrayList() {
        if (arrayList == null || arrayList.isEmpty())
            return null;
        else
            return arrayList.toString();
    }

    @GetMapping("/update-map")
    public String updateHashMap(@RequestParam(value = "s") String s) {
        if (hashMap == null || hashMap.isEmpty())
            hashMap = new HashMap<Integer, String>();
        hashMap.put(hashMapIndex, s);
        hashMapIndex++;
        return "";
    }

    @GetMapping("/show-map")
    public String showHashMap() {
        if (hashMap == null || hashMap.isEmpty())
            return null;
        else
            return hashMap.toString();
    }

    @GetMapping("/show-all-lenght")
    public String showAllLenght() {
        int arrayListSize = 0;
        int hashMapSize = 0;
        if (!(arrayList == null ||arrayList.isEmpty())) {
            arrayListSize = arrayList.size();
        }
        if (!(hashMap == null || hashMap.isEmpty())) {
            hashMapSize = hashMap.size();
        }
        return String.format("arraylist size is %d; \nhashmap size is %d", arrayListSize, hashMapSize);
    }
}
