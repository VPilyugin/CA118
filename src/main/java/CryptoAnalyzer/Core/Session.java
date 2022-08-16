package CryptoAnalyzer.Core;

import CryptoAnalyzer.EncryptDecrypt.EncryptDecryptService;
import CryptoAnalyzer.FilesInputOutput.FilesInputOutputInterface;
import CryptoAnalyzer.MainWindow.*;
import CryptoAnalyzer.TextObject.*;
public class Session {
    private MainWindow mainWindow;
    private TextEngineService textEngineService;
    private TextAnalysisService textAnalysisService;
    private FilesInputOutputInterface filesInputOutputService;
    private EncryptDecryptService encryptDecryptService;
    private TextObject textObject;
    private MainWindowCSInterface mainWindowContollerService;
    public Session() {
        this.mainWindow = new MainWindow();
        this.textObject = new TextObject();
        this.textEngineService = ServiceProvider.INSTANCE.getTEService();
        this.textAnalysisService = ServiceProvider.INSTANCE.getTAService();
        this.filesInputOutputService = ServiceProvider.INSTANCE.getFIOService();
        this.encryptDecryptService = ServiceProvider.INSTANCE.getEDService();
        this.mainWindowContollerService = ServiceProvider.INSTANCE.getMWCService();
    }
    public MainWindow getMainWindow() {
        return mainWindow;
    }
    public TextEngineService getTextEngineService() {
        return textEngineService;
    }
    public TextAnalysisService getTextAnalysisService() {
        return textAnalysisService;
    }
    public FilesInputOutputInterface getFilesInputOutputService() {
        return filesInputOutputService;
    }
    public EncryptDecryptService getEncryptDecryptService() {
        return encryptDecryptService;
    }
    public TextObject getTextObject() {
        return textObject;
    }
    public MainWindowCSInterface getMainWindowContollerService() {
        return mainWindowContollerService;
    }
}
