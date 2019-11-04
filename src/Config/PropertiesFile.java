package Config;


import com.company.Main;
//import com.company.starterClass;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesFile extends Main {
    public HashMap<String, String> readPropertiesFile(String fileInputPath) {
        Properties prop = new Properties();
        HashMap<String, String> identifiers = new HashMap<String, String>();
        //ArrayList<Object> identifiers = new ArrayList<>();
        try {
            InputStream input = new FileInputStream(fileInputPath);
            prop.load(input);
            prop.forEach((key, value) -> identifiers.put(key.toString(), value.toString()));
            return identifiers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return identifiers;
    }

    public static void writePropertiesFile() {
        Properties prop = new Properties();
        try {
            OutputStream output = new FileOutputStream("C:\\Users\\E606434\\Documents\\Forex Bank\\Automated Test\\src\\Config\\config.properties");
            prop.setProperty("Browser", "Chrome");
            prop.store(output, "as");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

