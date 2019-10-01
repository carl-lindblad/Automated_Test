package Config;


import com.company.Main;
import com.company.starterClass;
import com.company.identifyCustomer;
import java.io.*;
import java.util.Properties;

public class PropertiesFile extends Main{
    public static void readPropertiesFile(){
        Properties prop = new Properties();
        try {
            InputStream input = new FileInputStream("C:\\Users\\E606434\\Documents\\Forex Bank\\Automated Test\\src\\Config\\login.properties");
            prop.load(input);

            starterClass.usernameLogin = prop.getProperty("UserValue");
            identifyCustomer.pin = prop.getProperty("typicalSwede");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void writePropertiesFile(){
        Properties prop = new Properties();
        try {
            OutputStream output = new FileOutputStream("C:\\Users\\E606434\\Documents\\Forex Bank\\Automated Test\\src\\Config\\config.properties");
            prop.setProperty("Browser","Chrome");
            prop.store(output, "as");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
