import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Convertor<T extends Savable> {
    private final static String separator = ";";

    public String getString(String old, List<T> objects){
        String temp = "";
        for(T t : objects){
            temp += getStr(t.getInfo());
        }
        return old+temp;
    }

    private String getStr(HashMap<Integer,String> map){
        String temp = "";
        for(int i = 0; i < map.size(); i++) {
            temp += map.get(i) + separator;
        }
        return temp + System.lineSeparator();
    }

    public static List<List<String>> createList(String str){
        if(StringUtils.isEmpty(str)) return null;
        List<List<String>> newList = new ArrayList<>();
        for(String s : str.split(System.lineSeparator())){

            newList.add(getRightList(s));
        }
        return newList;
    }

    private static List<String> getRightList(String s){
        String [] temp = s.split(separator);
        for(int i = 0; i < temp.length; i++){
            temp[i] = StringUtils.trim(temp[i]);
        }
        return Arrays.asList(temp);
    }
}
