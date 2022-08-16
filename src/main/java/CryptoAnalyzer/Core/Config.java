package CryptoAnalyzer.Core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static final String TEXT_ENGINE_SERVICE = "TEService";
    public static final String MAIN_WINDOW_CONTROLLER_SERVICE = "MWCS";
    public static final String ENCRYPT_DECRYPT_ENGINE = "EDEngine";

    private static Properties properties = new Properties();

    public static String getProperty(String s){
        if(properties.isEmpty()){
            try (InputStream is = Config.class.getClassLoader()
                    .getResourceAsStream("CryptoAnalyzer.properties")){
                properties.load(is);
            }catch (IOException ex){
                ex.printStackTrace();
//                properties.put("TEService","BASIC");
//                properties.put("MWCS","CryptoAnalyzer.MainWindow.MainWindowContollerService");
//                properties.put("EDEngine","CryptoAnalyzer.EncryptDecrypt.EncryptDecryptEngine");
            }
        }
        return properties.getProperty(s);
    }
}
