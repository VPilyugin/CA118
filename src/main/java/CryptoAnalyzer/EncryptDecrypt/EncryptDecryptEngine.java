package CryptoAnalyzer.EncryptDecrypt;

import CryptoAnalyzer.TextObject.TextObject;

import java.util.ArrayList;
public class EncryptDecryptEngine {
    public EncryptDecryptEngine(){

    }
    @EncryptDecryptMethod
    public void encrypt(TextObject textObject){
        String mask = textObject.getCurrentMask()+textObject.getCurrentMask();
        ArrayList<Character> maskList = new ArrayList<>(mask.chars().mapToObj(x->(char)x).toList());
        int shift = textObject.getShift();
        ArrayList<String> inputList = new ArrayList<>(textObject.getInputText());
        ArrayList<String> outputList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (String currentString : inputList){
            for (int i = 0; i < currentString.length(); i++) {
                char currentChar = currentString.charAt(i);
                for (int j = 0; j < textObject.getCurrentMask().length(); j++) {
                    int x = Character.compare(currentChar,maskList.get(j));
                    if(x==0){
                        currentChar = maskList.get(j+shift);
                        break;
                    }
                }
                stringBuilder.append(currentChar);
            }
            outputList.add(stringBuilder.toString());
            stringBuilder.delete(0,stringBuilder.length());
        }
        textObject.setOutputText(outputList);
    }
}
