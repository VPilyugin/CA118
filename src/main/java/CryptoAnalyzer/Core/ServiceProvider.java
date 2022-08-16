package CryptoAnalyzer.Core;

import CryptoAnalyzer.EncryptDecrypt.*;
import CryptoAnalyzer.FilesInputOutput.FilesInputOutputInterface;
import CryptoAnalyzer.MainWindow.*;
import CryptoAnalyzer.TextObject.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.ServiceLoader;
public class ServiceProvider {
    Properties properties;
    public static final ServiceProvider INSTANCE = new ServiceProvider();
    private ServiceProvider(){
    }
    public TextAnalysisService getTAService(){
        return new TextAnalysisService();
    }
    public FilesInputOutputInterface getFIOService(){
        ServiceLoader<FilesInputOutputInterface> serviceLoader = ServiceLoader.load(FilesInputOutputInterface.class);
        return serviceLoader.iterator().next();
    }
    public TextEngineService getTEService(){
        String typeTEService = Config.getProperty(Config.TEXT_ENGINE_SERVICE);
        if("BASIC".equals(typeTEService)){
            return new TextEngineService();
        }else {
            return new TextEngineService();
        }
    }
    public MainWindowCSInterface getMWCService(){
        String clazz = Config.getProperty(Config.MAIN_WINDOW_CONTROLLER_SERVICE);
        MainWindowCSInterface mainWindowCSInterface;
        try{
            Class<?> mwcs = Class.forName(clazz);
            mainWindowCSInterface = (MainWindowCSInterface) mwcs.getDeclaredConstructor().newInstance();
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                InvocationTargetException e){
            e.printStackTrace();
            return new MainWindowContollerService();
        }
        return mainWindowCSInterface;
    }
    public EncryptDecryptService getEDService(){
        String clazz = Config.getProperty(Config.ENCRYPT_DECRYPT_ENGINE);
        Object CurrentEDEngine;
        Method edm = null;
        try{
            Class<?> eds = Class.forName(clazz);
            CurrentEDEngine = eds.getDeclaredConstructor().newInstance();
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                InvocationTargetException e){
            e.printStackTrace();
            CurrentEDEngine = new EncryptDecryptEngine();
        }
        Method[] methods = CurrentEDEngine.getClass().getMethods();
        for(Method method:methods){
            EncryptDecryptMethod encryptDecryptMethod = method.getAnnotation(EncryptDecryptMethod.class);
            if(encryptDecryptMethod!=null){
                edm = method;
            }
        }
        if(edm==null){
            try {
                edm = CurrentEDEngine.getClass().getDeclaredMethod("encrypt");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                try {
                    CurrentEDEngine = new EncryptDecryptEngine();
                    edm = CurrentEDEngine.getClass().getDeclaredMethod("encrypt");
                } catch (NoSuchMethodException ex) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }
        return new EncryptDecryptService(CurrentEDEngine,edm);
    }
}
