package CryptoAnalyzer.TextObject;

import CryptoAnalyzer.MainWindow.MainWindow;

import java.util.ArrayList;
public class TextEngineService implements TextEngineServiceInterface {
    public TextEngineService() {
    }
    public void initialTextObject(TextObject textObject){
        textObject.setCurrentMask(textObject.getDEFAULT_CRYPTO_ALPHABET());
        textObject.setShift(1);
        textObject.setInputTextEncrypted(false);
    }
    public void resetOutputPartTextObject(TextObject textObject){
        textObject.setOutputText(new ArrayList<>());
        textObject.setShift(1);
    }
    public void resetInputPartTextObject(TextObject textObject){
        textObject.setInputText(new ArrayList<>());
        resetOutputPartTextObject(textObject);
        textObject.setInputTextEncrypted(false);
        textObject.setNumberOfEffectiveRows(0);
        textObject.setNumberOfWordsInInputText(0);
        textObject.setNumberOfTotalChars(0);
        textObject.setNumberOfCharPossibleToEncrypt(0);
    }
    public void resetTextObject(TextObject textObject){
        resetInputPartTextObject(textObject);
    }
    public void refreshTextAreas(MainWindow mainWindow, TextObject textObject){
        refreshRightTextAreas(mainWindow, textObject);
        refreshLeftTextAreas(mainWindow,textObject);
    }
    public void refreshRightTextAreas(MainWindow mainWindow, TextObject textObject){
        showTextUpperRightFirstTextArea(mainWindow,textObject);
        showTextUpperRightSecondTextArea(mainWindow,textObject);
        mainWindow.getShiftSlider().setValue(textObject.getShift());
        showTextRightTextArea(mainWindow,textObject);
    }
    public void refreshLeftTextAreas(MainWindow mainWindow, TextObject textObject){
        showTextLeftTextArea(mainWindow,textObject);
        showTextUpperLeftTextArea(mainWindow,textObject);
    }
    public void showTextUpperLeftTextArea(MainWindow mainWindow, TextObject textObject){
        if(textObject.isInputTextEncrypted()||textObject.getInputText().size()==0){
            mainWindow.getUpperLeftTextArea().setText("");
        }else{
            mainWindow.getUpperLeftTextArea().setText("Общее число символов в файле: "+
                    textObject.getNumberOfTotalChars()+
                    "   Из них можно зашифровать: "+
                    textObject.getNumberOfCharPossibleToEncrypt());
        }
    }
    public void showTextUpperRightFirstTextArea(MainWindow mainWindow, TextObject textObject){
        mainWindow.getUpperRightFirstTextArea().setText("mask:"+ textObject.getCurrentMask());
    }
    public void showTextUpperRightSecondTextArea(MainWindow mainWindow, TextObject textObject){
        mainWindow.getUpperRightSecondTextArea().setText(
                "                                                                        Mask lenght: " +
                textObject.getCurrentMask().length() + "  Current shift: " + textObject.getShift());
    }
    public void showTextLeftTextArea(MainWindow mainWindow, TextObject textObject){
        mainWindow.getLeftTextArea().selectAll();
        mainWindow.getLeftTextArea().replaceSelection("");
        for (String s:textObject.getInputText()){
            mainWindow.getLeftTextArea().append(s);
            mainWindow.getLeftTextArea().append("\n");
        }
        mainWindow.getLeftTextAreaScrollPane().revalidate();
        mainWindow.repaint();
    }
    public void showTextRightTextArea(MainWindow mainWindow,TextObject textObject){
        mainWindow.getRightTextArea().selectAll();
        mainWindow.getRightTextArea().replaceSelection("");
        for(String s:textObject.getOutputText()){
            mainWindow.getRightTextArea().append(s);
            mainWindow.getRightTextArea().append("\n");
        }
        mainWindow.getRightTextAreaScrollPane().revalidate();
        mainWindow.repaint();
    }
}
