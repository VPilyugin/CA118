package CryptoAnalyzer.TextObject;

import CryptoAnalyzer.MainWindow.MainWindow;

public interface TextEngineServiceInterface {
    void initialTextObject(TextObject textObject);
    void resetOutputPartTextObject(TextObject textObject);
    void resetInputPartTextObject(TextObject textObject);
    void resetTextObject(TextObject textObject);
    void refreshTextAreas(MainWindow mainWindow, TextObject textObject);
    void refreshRightTextAreas(MainWindow mainWindow, TextObject textObject);
    void refreshLeftTextAreas(MainWindow mainWindow, TextObject textObject);
    void showTextUpperLeftTextArea(MainWindow mainWindow, TextObject textObject);
    void showTextUpperRightFirstTextArea(MainWindow mainWindow, TextObject textObject);
    void showTextUpperRightSecondTextArea(MainWindow mainWindow, TextObject textObject);
    void showTextLeftTextArea(MainWindow mainWindow, TextObject textObject);
    void showTextRightTextArea(MainWindow mainWindow,TextObject textObject);

}
