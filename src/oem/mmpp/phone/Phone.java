package mmpp.phone;

import java.util.HashMap;
import java.util.Map;

public final class Phone {
    private static final Map<String, String> properties = new HashMap<>();
    static {
        properties.put("MIN", "010-1234-5678");
        properties.put("cdma.callStatus", "false");
        properties.put("phone.RSSILevel", "2");
        properties.put("phone.batteryLevel", "4");
        properties.put("phone.LCD.colorDepth", "16");
        properties.put("phone.LCD.height", "240");
        properties.put("phone.LCD.width", "320");
        properties.put("phone.serial1.baudrate", "9600");
        properties.put("phone.serial2.baudrate", "9600");
        properties.put("phone.serial1.mode", "8N1");
        properties.put("phone.serial2.mode", "8N1");
        properties.put("system.compiledDate", "2025-01-11");
        properties.put("network.browser.SID", "12345");
        properties.put("network.browser.initialURL", "http://example.com");
    }

    public static int getBASELAT(){
        return 0;
    }

    public static int getBASELONG(){
        return 0;
    }

    public static String getProperty(String key){
        return properties.getOrDefault(key, null);
    }

    public static void invokeWAPBrowser(String url){

    }

    public static void placeCall(String phoneNumber){

    }
}