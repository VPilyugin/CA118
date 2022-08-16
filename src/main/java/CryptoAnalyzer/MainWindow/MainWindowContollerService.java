package CryptoAnalyzer.MainWindow;

public class MainWindowContollerService implements MainWindowCSInterface {
    public MainWindowContollerService(){

    }
    public void setStartMode(MainWindow mainWindow){
        setEditableAllTextAreas(mainWindow, false);
        setEnableDecryptGroup(mainWindow,false);
        setEnableEncryptGroup(mainWindow, false);
        setEnableFrequencyAnalyticBarChart(mainWindow, false);
        setEnableFirstPageTabbledPane(mainWindow,false);
        setEnabledSecondPageTabbledPane(mainWindow, false);
        setEnabledThirdPageTabbledPane(mainWindow, false);
        setEnabledShiftSlider(mainWindow,false);
    }
    public void setEditableAllTextAreas(MainWindow mainWindow, Boolean status){
        mainWindow.getUpperRightFirstTextArea().setEditable(status);
        mainWindow.getUpperRightSecondTextArea().setEditable(status);
        mainWindow.getRightTextArea().setEditable(status);
        mainWindow.getUpperLeftTextArea().setEditable(status);
        mainWindow.getLeftTextArea().setEditable(status);
        mainWindow.getLeftTextAreaSecondTab().setEditable(status);
        mainWindow.getLeftTextAreaThirdTab().setEditable(status);
    }
    public void setEnableDecryptGroup(MainWindow mainWindow, Boolean status){
        mainWindow.getDecrypt().setEnabled(status);
        mainWindow.getManualDecryption().setEnabled(status);
        mainWindow.getManualDecryption().setSelected(true);
        mainWindow.getBruteForceDecryption().setEnabled(status);
        mainWindow.getStatisticDecryption().setEnabled(status);
    }
    public void setEnableEncryptGroup(MainWindow mainWindow, Boolean status){
        mainWindow.getEncrypt().setEnabled(status);
        mainWindow.getManualEncryption().setEnabled(status);
        mainWindow.getAutoEnсryption().setSelected(true);
        mainWindow.getAutoEnсryption().setEnabled(status);
    }
    public void setEnableFrequencyAnalyticBarChart(MainWindow mainWindow, Boolean status){
        mainWindow.getFrequencyAnalyticBarChart().setEnabled(status);
    }
    public void setEnableFirstPageTabbledPane(MainWindow mainWindow, Boolean status){
        mainWindow.getLeftTextAreaTabbedPane().setEnabledAt(0,status);
    }
    public void setEnabledSecondPageTabbledPane(MainWindow mainWindow, Boolean status){
        mainWindow.getLeftTextAreaTabbedPane().setEnabledAt(1,status);
    }
    public void setEnabledThirdPageTabbledPane(MainWindow mainWindow, Boolean status){
        mainWindow.getLeftTextAreaTabbedPane().setEnabledAt(2,status);
    }
    public void setEnabledShiftSlider(MainWindow mainWindow, Boolean status){
        mainWindow.getShiftSlider().setEnabled(status);
    }
}
