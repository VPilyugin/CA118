package CryptoAnalyzer.FilesInputOutput;

import CryptoAnalyzer.MainWindow.MainWindow;
import CryptoAnalyzer.TextObject.TextObject;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FilesInputOutputService implements FilesInputOutputInterface {
    public FilesInputOutputService(){

    }
    @Override
    public String getPath(MainWindow currentWindow, FileOperation fileOperation){
        String returnPath = "";
        String description = "*.txt";
        String extensionTxt = "txt";
        String extensionEncrypted = "encrypted";
        JFileChooser fileChooser = new JFileChooser();
        if(fileOperation == FileOperation.LOAD){
            fileChooser.setDialogTitle("Выбор *.txt или *.encrypted файла");
        } else if (fileOperation == FileOperation.SAVE_ENCRYPTED){
            fileChooser.setDialogTitle("Выбор *.encrypted файла");
            description = "*.encrypted";
            extensionTxt = "encrypted";
        } else {
            fileChooser.setDialogTitle("Выбор *.txt файла для сохранения");
            extensionEncrypted = "txt";
        }
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter(description,extensionTxt,extensionEncrypted);
        fileChooser.setFileFilter(extensionFilter);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if(fileChooser.showOpenDialog(currentWindow)==JFileChooser.APPROVE_OPTION){
            returnPath = String.valueOf(fileChooser.getSelectedFile());
        }
        return returnPath;
    }
    @Override
    public void loadFile(String stringPath, TextObject textObject){
        ArrayList<String> loadedStringsArrayList = new ArrayList<>();
        if(Files.notExists(Path.of(stringPath))) return;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(stringPath))){
            while (bufferedReader.ready()){
                loadedStringsArrayList.add(bufferedReader.readLine());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        textObject.setInputText(loadedStringsArrayList);
    }
    @Override
    public ArrayList<String> loadDescription(){
        ArrayList<String> description = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/description.txt"))){
            while (bufferedReader.ready()){
                String inputLine = bufferedReader.readLine();
                description.add(inputLine);
                description.add("\n");
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return description;
    }
    @Override
    public void saveFile(MainWindow mainWindow,FileOperation fileOperation,TextObject textObject) {
        String outputFile = "";
        if(!textObject.isInputTextEncrypted()){
            outputFile = getPath(mainWindow, FileOperation.SAVE_ENCRYPTED);
            if(!(outputFile.endsWith(".encrypted"))){
                outputFile +=".encrypted";
            }
        } else{
            outputFile = getPath(mainWindow, FileOperation.SAVE_DECRYPTED);
            if(!(outputFile.endsWith(".txt"))){
                outputFile+=".txt";
            }
        }
        if(Files.exists(Path.of(outputFile))){
            int choosedOption = JOptionPane.showConfirmDialog(mainWindow, "Файл существует, перезаписать?");
            if(choosedOption!=0){
                JOptionPane.showMessageDialog(mainWindow,"Файл сохранить не удалось");
                return;
            }
        } else try {
            Files.createFile(Path.of(outputFile));
        } catch (IOException e){
            e.printStackTrace();
        }
        ArrayList<String> stringsToSave = textObject.getOutputText();
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))){
            for(String s:stringsToSave){
                bufferedWriter.write(s+"\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(mainWindow,"Файл успешно записан в " + outputFile );
    }
}
