package CryptoAnalyzer.TextObject;

import java.util.ArrayList;

public class TextObject {

    // базовый алфавит
    // "\";.,?:! АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя"
    private MetaData metaData;
    private final String DEFAULT_CRYPTO_ALPHABET = "ФъюявщкЯ;фЭА:абХЧНЦЖ\"ГЪИи!,шЕМЙпЬнВы.РЩУужШ ЗхьтСБ?ЛЫцчмЮгёдэзПКДсОйролТЁе";
    private boolean isInputTextEncrypted;
    private String currentMask;
    private ArrayList<Character> currentMaskCharsList;
    private int shift;
    private ArrayList<String> inputText = new ArrayList<>();
    private ArrayList<String> outputText = new ArrayList<>();
    private int numberOfEffectiveRows;
    private int numberOfWordsInInputText;
    private int numberOfTotalChars;
    private int numberOfCharPossibleToEncrypt;
    public TextObject(){

    }
    public String getDEFAULT_CRYPTO_ALPHABET() {
        return DEFAULT_CRYPTO_ALPHABET;
    }
    public boolean isInputTextEncrypted() {
        return isInputTextEncrypted;
    }
    public void setInputTextEncrypted(boolean inputTextEncrypted) {
        isInputTextEncrypted = inputTextEncrypted;
    }
    public String getCurrentMask() {
        return currentMask;
    }
    public void setCurrentMask(String currentMask) {
        this.currentMask = currentMask;
        this.currentMaskCharsList = new ArrayList<>(currentMask.chars().mapToObj(x->(char)x).toList());
    }
    public ArrayList<Character> getCurrentMaskCharsList() {
        return currentMaskCharsList;
    }
    public int getShift() {
        return shift;
    }
    public void setShift(int shift) {
        this.shift = shift;
    }
    public ArrayList<String> getInputText() {
        return inputText;
    }
    public ArrayList<String> getOutputText() {
        return outputText;
    }
    public void setInputText(ArrayList<String> inputText) {
        this.inputText = new ArrayList<>(inputText);
    }
    public void setOutputText(ArrayList<String> outputText) {
        this.outputText = new ArrayList<>(outputText);
    }
    public int getNumberOfEffectiveRows() {
        return numberOfEffectiveRows;
    }
    public void setNumberOfEffectiveRows(int numberOfEffectiveRows) {
        this.numberOfEffectiveRows = numberOfEffectiveRows;
    }
    public int getNumberOfWordsInInputText() {
        return numberOfWordsInInputText;
    }
    public void setNumberOfWordsInInputText(int numberOfWordsInInputText) {
        this.numberOfWordsInInputText = numberOfWordsInInputText;
    }
    public int getNumberOfTotalChars() {
        return numberOfTotalChars;
    }
    public void setNumberOfTotalChars(int numberOfTotalChars) {
        this.numberOfTotalChars = numberOfTotalChars;
    }
    public int getNumberOfCharPossibleToEncrypt() {
        return numberOfCharPossibleToEncrypt;
    }
    public void setNumberOfCharPossibleToEncrypt(int numberOfCharPossibleToEncrypt) {
        this.numberOfCharPossibleToEncrypt = numberOfCharPossibleToEncrypt;
    }
}
