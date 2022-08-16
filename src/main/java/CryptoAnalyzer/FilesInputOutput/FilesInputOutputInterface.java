package CryptoAnalyzer.FilesInputOutput;

import CryptoAnalyzer.MainWindow.MainWindow;
import CryptoAnalyzer.TextObject.TextObject;

import java.util.ArrayList;

public interface FilesInputOutputInterface {
    String getPath(MainWindow currentWindow, FileOperation fileOperation);
    void loadFile(String stringPath, TextObject textObject);
    ArrayList<String> loadDescription();
    void saveFile(MainWindow mainWindow, FileOperation fileOperation, TextObject textObject);
    public enum FileOperation {
        SAVE_ENCRYPTED, SAVE_DECRYPTED, LOAD
    }
}
