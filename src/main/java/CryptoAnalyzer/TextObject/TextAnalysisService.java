package CryptoAnalyzer.TextObject;

import CryptoAnalyzer.EncryptDecrypt.EncryptDecryptService;

import java.util.*;

public class TextAnalysisService {

    // базовый алфавит
    // "\";.,?:! АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя"

    private static final String LOWERCASE_LETTERS_CONSONANTS = "бвгджзклмнпрстфхцчшщ";
    private static final String CAPITAL_LETTERS_CONSONANTS = "БВГДЖЗКЛМНПРСТФХЦЧШЩ";
    private static final String LOWERCASE_LETTERS_VOWELS = "аеёийоуыэюя";
    private static final String CAPITAL_LETTERS_VOWELS = "АЕЁИЙОУЫЭЮЯ";
    private static final String SPECIAL_ADDITIONAL_LETTERS = "ЪъЬь";
    private static final int INITIAL_VALIDATOR = 100;
    private static final int REJECTED_META_OBJECT = -1;

    public TextAnalysisService(){

    }
    public void inputTextAnalyzer(TextObject textObject){
        int totalNumChars = 0;
        int validNumChars = 0;
        int numberOfEffectiveRows = 0;
        ArrayList<Character> maskList = textObject.getCurrentMaskCharsList();
        for(String inputLine:textObject.getInputText()){
            if(inputLine.isBlank()) continue;
            numberOfEffectiveRows++;
            for (int i = 0; i < inputLine.length(); i++) {
                if(maskList.contains(inputLine.charAt(i))){
                    validNumChars++;
                }
                totalNumChars++;
            }
        }
        textObject.setNumberOfTotalChars(totalNumChars);
        textObject.setNumberOfCharPossibleToEncrypt(validNumChars);
        textObject.setNumberOfEffectiveRows(numberOfEffectiveRows);
    }
    public int bruteForceDecrypt(TextObject textObject, EncryptDecryptService encryptDecryptService){
        int resultShift = 0;
        List<MetaData> metaDataList = new ArrayList<>();
        for (int i = 0; i < textObject.getCurrentMask().length(); i++) {
            textObject.setShift(i);
            encryptDecryptService.encrypt(textObject);
            MetaData metaData = new MetaData();
            metaData.setShift(i);
            fillInMetaData(metaData,textObject);
            checkMetaData(metaData);
            metaDataList.add(metaData);
        }
        resultShift = decryptBF(metaDataList);
        return resultShift;
    }
    private int decryptBF(List<MetaData> metaDataList){
        TreeMap<Integer,MetaData> resultSet = new TreeMap<>();
        for(MetaData metaData:metaDataList){
            resultSet.put(metaData.getNumberOfValidElements(),metaData);
        }
        int lastKey = resultSet.lastKey();
        return resultSet.get(lastKey).getShift();
    }
    private void checkMetaData(MetaData metaData){
        int numberofTotalElements = 0;
        int numberOfValidElements = 0;
        for(MetaData.MetaDataElement metaDataElement: metaData.getMetaDataElementList()){
            numberofTotalElements++;
            if(metaDataElement.getValidator()>0){
                numberOfValidElements++;
            }
        }
        metaData.setNumberOfTotalElements(numberofTotalElements);
        metaData.setNumberOfValidElements(numberOfValidElements);
    }
    private void fillInMetaData(MetaData metaData, TextObject textObject){
        for(String inputString:textObject.getOutputText()){
            if(inputString.isEmpty()){
                continue;
            }
            String[] words = inputString.split("[;.,?:! ]");
            for (int i = 0; i < words.length; i++) {
                MetaData.MetaDataElement metaDataElement = new MetaData.MetaDataElement();
                fillInMetaDataElement(metaDataElement,words[i]);
                metaData.addMetaObjectElement(metaDataElement);
            }
        }
    }
    private void fillInMetaDataElement(MetaData.MetaDataElement metaDataElement, String word){
        int validator = INITIAL_VALIDATOR;
        if (word.length()<3) {
            metaDataElement.setValidator(REJECTED_META_OBJECT);
            return;
        };
        String lowerCaseExample = word.toLowerCase();
        for (int i = 0; i < word.length()-2; i++) {
            if(LOWERCASE_LETTERS_CONSONANTS.contains(String.valueOf(lowerCaseExample.charAt(i)))&&
                    LOWERCASE_LETTERS_CONSONANTS.contains(String.valueOf(lowerCaseExample.charAt(i+1)))&&
                    LOWERCASE_LETTERS_CONSONANTS.contains(String.valueOf(lowerCaseExample.charAt(i+2)))){
                validator = REJECTED_META_OBJECT;
                metaDataElement.setValidator(validator);
            }
            if(LOWERCASE_LETTERS_VOWELS.contains(String.valueOf(lowerCaseExample.charAt(i)))&&
                    LOWERCASE_LETTERS_VOWELS.contains(String.valueOf(lowerCaseExample.charAt(i+1)))&&
                    LOWERCASE_LETTERS_VOWELS.contains(String.valueOf(lowerCaseExample.charAt(i+2)))){
                validator = REJECTED_META_OBJECT;
                metaDataElement.setValidator(validator);
            }
        }
        for (int i = 1; i < word.length(); i++) {
            if(CAPITAL_LETTERS_CONSONANTS.contains(String.valueOf(word.charAt(i)))||
                CAPITAL_LETTERS_VOWELS.contains(String.valueOf(word.charAt(i)))){
                validator = REJECTED_META_OBJECT;
                metaDataElement.setValidator(validator);
            }
        }
    }




}
