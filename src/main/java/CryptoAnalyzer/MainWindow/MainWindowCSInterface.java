package CryptoAnalyzer.MainWindow;

public interface MainWindowCSInterface {
    void setStartMode(MainWindow mainWindow);
    void setEditableAllTextAreas(MainWindow mainWindow, Boolean status);
    void setEnableDecryptGroup(MainWindow mainWindow, Boolean status);
    void setEnableEncryptGroup(MainWindow mainWindow, Boolean status);
    void setEnableFrequencyAnalyticBarChart(MainWindow mainWindow, Boolean status);
    void setEnableFirstPageTabbledPane(MainWindow mainWindow, Boolean status);
    void setEnabledSecondPageTabbledPane(MainWindow mainWindow, Boolean status);
    void setEnabledThirdPageTabbledPane(MainWindow mainWindow, Boolean status);
    void setEnabledShiftSlider(MainWindow mainWindow, Boolean status);
}
