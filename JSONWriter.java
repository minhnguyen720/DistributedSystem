package Map;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONWriter {
    public static void main(String[] args) {

        // JSON object
        var jsonObject = new JSONObject();
        var otherArr = new JSONArray();

        // putting data into json object
        jsonObject.put("id", 14485);
        jsonObject.put("age", 21);

        Map willie = new LinkedHashMap<>(1);
        willie.put("name", "willie");
        otherArr.add(willie);

        Map jason = new LinkedHashMap<>(1);
        jason.put("name", "jason");
        otherArr.add(jason);

        jsonObject.put("name", otherArr);
        
        // for accounts
        var jsonArr = new JSONArray();

        Map map = new LinkedHashMap<>(2);
        map.put("accountId", 1234);
        map.put("balance", 10000);
        jsonArr.add(map);

        map = new LinkedHashMap<>(2);
        map.put("accountId", 4567);
        map.put("balance", 25000);
        jsonArr.add(map);

        // putting map into json obj
        jsonObject.put("accounts", jsonArr);

        // get jsonString
        String jsonString = jsonObject.toJSONString();
        System.out.println(jsonString);
    }
}
