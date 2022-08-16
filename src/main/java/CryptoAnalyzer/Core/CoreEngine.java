package CryptoAnalyzer.Core;

import CryptoAnalyzer.EncryptDecrypt.EncryptDecryptService;
import CryptoAnalyzer.FilesInputOutput.*;
import CryptoAnalyzer.MainWindow.*;
import CryptoAnalyzer.TextObject.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
public class CoreEngine {
    Session session;
    MainWindow currentMainWindow;
    TextObject textObject;
    MainWindowCSInterface mainWindowContollerService;
    TextEngineService textEngineService;
    EncryptDecryptService encryptDecryptService;
    TextAnalysisService textAnalysisService;
    FilesInputOutputInterface filesInputOutputService;
    public CoreEngine(Session session){
        this.session = session;
        this.currentMainWindow = session.getMainWindow();
        this.textObject = session.getTextObject();
        this.mainWindowContollerService = session.getMainWindowContollerService();
        this.textEngineService = session.getTextEngineService();
        this.encryptDecryptService = session.getEncryptDecryptService();
        this.textAnalysisService = session.getTextAnalysisService();
        this.filesInputOutputService = session.getFilesInputOutputService();
        mainWindowContollerService.setStartMode(currentMainWindow);
        initialMainWindow();
        textEngineService.initialTextObject(textObject);
        textEngineService.refreshRightTextAreas(currentMainWindow,textObject);
        textEngineService.refreshLeftTextAreas(currentMainWindow,textObject);
    }
    private void initialMainWindow (){
        addMenuBar();
        setAllActions();
        currentMainWindow.setVisible(true);
    }
    private void setAllActions(){
        setActionJButtons();
        setSliderAction();
        setLeftTextAreaResetJButton();
        setRightTextAreaResetJButton();
    }
    private void setLeftTextAreaResetJButton(){
        boolean resetOnlyRightTextArea = false;
        currentMainWindow.getResetLeftTextArea().addActionListener(e->resetAction(resetOnlyRightTextArea));
    }
    private void setRightTextAreaResetJButton(){
        boolean resetOnlyRightTextArea = true;
        currentMainWindow.getResetRightTextArea().addActionListener(e->resetAction(resetOnlyRightTextArea));
    }
    private void resetAction(boolean resetOnlyRightTextArea){
        if (!resetOnlyRightTextArea) {
            textObject = new TextObject();
            textEngineService.initialTextObject(textObject);
            mainWindowContollerService.setStartMode(currentMainWindow);
        }
        currentMainWindow.getShiftSlider().setValue(1);
        textEngineService.resetOutputPartTextObject(textObject);
        textEngineService.refreshTextAreas(currentMainWindow,textObject);
    }
    private void setSliderAction(){
        currentMainWindow.getShiftSlider().addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int value = ((JSlider)e.getSource()).getValue();
                textObject.setShift(value);
                if(currentMainWindow.getRightTextArea().getText().length()>0){
                    encryptDecryptService.encrypt(textObject);
                }
                textEngineService.refreshRightTextAreas(currentMainWindow,textObject);
            }
        });
    }
    private void setActionJButtons(){
        currentMainWindow.getEncrypt().addActionListener(e->encryptJButtonAction());
        currentMainWindow.getAutoEnсryption().addActionListener(e->radioButtonsEncryptAutoAction());
        currentMainWindow.getManualEncryption().addActionListener(e->radioButtonsEncryptManualAction());
//        currentMainWindow.getFrequencyAnalyticBarChart().addActionListener(e->frequencyBarChartAction());
        currentMainWindow.getSaveFile().addActionListener(e->saveFileAction());
        currentMainWindow.getDecrypt().addActionListener(e->decryptAction());
    }
    private void decryptAction(){
        if(currentMainWindow.getManualDecryption().isSelected()){
            encryptDecryptService.encrypt(textObject);
            textEngineService.refreshRightTextAreas(currentMainWindow, textObject);
            mainWindowContollerService.setEnabledShiftSlider(currentMainWindow, true);
        }
        if(currentMainWindow.getBruteForceDecryption().isSelected()){
            int getShift = textAnalysisService.bruteForceDecrypt(textObject,encryptDecryptService);
            if(getShift==0){
                JOptionPane.showMessageDialog(currentMainWindow,"Текст не зашифрован");
                return;
            }
            textObject.setShift(getShift);
            encryptDecryptService.encrypt(textObject);
            textEngineService.refreshRightTextAreas(currentMainWindow,textObject);
        }

    }
    private void encryptJButtonAction(){
        if(currentMainWindow.getLeftTextArea().getText().length()==0){
            JOptionPane.showMessageDialog(currentMainWindow,"Текст для шифрования отсутствует");
            return;
        }
        if (currentMainWindow.getAutoEnсryption().isSelected()) {
            textObject.setShift((int) (Math.random() * 73 + 1));
        }
        encryptDecryptService.encrypt(textObject);
        textEngineService.refreshRightTextAreas(currentMainWindow, textObject);
    }
    private void radioButtonsEncryptAutoAction(){
        mainWindowContollerService.setEnabledShiftSlider(currentMainWindow,false);
    }
    private void radioButtonsEncryptManualAction(){
        mainWindowContollerService.setEnabledShiftSlider(currentMainWindow, true);
    }
    private void addMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createJMenuFile());
        menuBar.add(createJMenuDescription());
        currentMainWindow.setJMenuBar(menuBar);
    }
    private JMenu createJMenuDescription(){
        JMenu jMenu = new JMenu("О программе");
        JMenuItem showDescription = new JMenuItem(new ShowDescriptionAction());
        jMenu.add(showDescription);
        return jMenu;
    }
    private JMenu createJMenuFile(){
        JMenu jMenu = new JMenu("Файл");
        JMenuItem open = new JMenuItem(new OpenFileAction());
        JMenuItem exit = new JCheckBoxMenuItem(new ExitAction());
        jMenu.add(open);
        jMenu.addSeparator();
        jMenu.add(exit);
        return jMenu;
    }
    private class ShowDescriptionAction extends AbstractAction{
        ShowDescriptionAction(){putValue((NAME), "Описание программы");}

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame programmDescriptionFrame = new JFrame("CryptoAnalyzer v.1.18");
            programmDescriptionFrame.setResizable(false);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension descriptionFrameSize = new Dimension(600,700);
            programmDescriptionFrame.setBounds(screenSize.width/3,10,descriptionFrameSize.width,descriptionFrameSize.height);
            JTextArea descriptionTextArea = new JTextArea();
            descriptionTextArea.setEditable(false);
            ArrayList<String> descriptionText = new ArrayList<>(filesInputOutputService.loadDescription());
            for(String s:descriptionText){
                descriptionTextArea.append(s);
            }
            JScrollPane descriptionTextAreaJScrollPane = new JScrollPane(descriptionTextArea);
            programmDescriptionFrame.add(descriptionTextAreaJScrollPane);
            programmDescriptionFrame.setVisible(true);
        }
    }
    private class OpenFileAction extends AbstractAction{
        OpenFileAction(){
            putValue((NAME),"Загрузить файл");
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String srcPath = filesInputOutputService.getPath(currentMainWindow, FilesInputOutputService.FileOperation.LOAD);
            if((!srcPath.endsWith(".txt"))&&(!srcPath.endsWith(".encrypted"))) {
                JOptionPane.showMessageDialog(currentMainWindow,"Неправильный формат файла");
                return;
            } else {
                textEngineService.resetTextObject(textObject);
                textEngineService.refreshTextAreas(currentMainWindow,textObject);
                mainWindowContollerService.setStartMode(currentMainWindow);
                mainWindowContollerService.setEnableFirstPageTabbledPane(currentMainWindow,true);
                filesInputOutputService.loadFile(srcPath,textObject);
                textAnalysisService.inputTextAnalyzer(textObject);
            }
            if(srcPath.endsWith(".encrypted")){
                textObject.setInputTextEncrypted(true);
                mainWindowContollerService.setEnableDecryptGroup(currentMainWindow, true);
            }else {
                textObject.setInputTextEncrypted(false);
                mainWindowContollerService.setEnableEncryptGroup(currentMainWindow,true);
            }
            textEngineService.refreshLeftTextAreas(currentMainWindow,textObject);
        }
    }
    private class ExitAction extends AbstractAction{
        ExitAction(){
            putValue((NAME), "Выход");
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    private void saveFileAction() {
        if(currentMainWindow.getRightTextArea().getText().isEmpty()){
            JOptionPane.showMessageDialog(currentMainWindow,"Нет данных для сохранения");
            return;
        }
        if(!textObject.isInputTextEncrypted()){
            filesInputOutputService.saveFile(currentMainWindow, FilesInputOutputService.FileOperation.SAVE_ENCRYPTED,textObject);
        } else{
            filesInputOutputService.saveFile(currentMainWindow, FilesInputOutputService.FileOperation.SAVE_DECRYPTED,textObject);
            }
        }
}
