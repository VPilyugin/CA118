package CryptoAnalyzer.EncryptDecrypt;

import CryptoAnalyzer.TextObject.TextObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
public class EncryptDecryptService {
    Object encryptDecryptEngine;
    Method encryptDecryptMethod;
    public EncryptDecryptService(Object encryptDecryptEngine, Method encryptDecryptMethod){
        this.encryptDecryptEngine = encryptDecryptEngine;
        this.encryptDecryptMethod = encryptDecryptMethod;
    }
    public void encrypt(TextObject textObject){
        try {
            encryptDecryptMethod.invoke(encryptDecryptEngine,textObject);
        } catch (IllegalAccessException | InvocationTargetException e ) {
            e.printStackTrace();
            new EncryptDecryptEngine().encrypt(textObject);
        }
    }
}
