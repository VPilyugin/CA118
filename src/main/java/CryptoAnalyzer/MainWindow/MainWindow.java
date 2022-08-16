package CryptoAnalyzer.MainWindow;

import CryptoAnalyzer.CryptoAnalyzer;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
public class MainWindow extends JFrame{
    private JTextArea leftTextArea = new JTextArea();
    private JTextArea leftTextAreaSecondTab = new JTextArea();
    private JTextArea leftTextAreaThirdTab = new JTextArea();
    private JTextArea rightTextArea = new JTextArea();
    private JTextArea upperRightFirstTextArea = new JTextArea();
    private JTextArea upperRightSecondTextArea = new JTextArea();
    private JTextArea upperLeftTextArea = new JTextArea();
    private JScrollPane leftTextAreaScrollPane;
    private JScrollPane leftTextAreaSecondTabScrollPane;
    private JScrollPane leftTextAreaThirdTabScrollPane;
    private JScrollPane rightTextAreaScrollPane;
    private JSlider shiftSlider;
    private JButton encrypt = new JButton("ЗАШИФРОВАТЬ");
    private JButton decrypt = new JButton("РАСШИФРОВАТЬ");
    private JButton resetLeftTextArea = new JButton("СБРОС");
    private JButton resetRightTextArea = new JButton("ОЧИСТКА");
    private JButton frequencyAnalyticBarChart = new JButton("ЧАСТОТНЫЙ АНАЛИЗ");
    private JButton saveFile = new JButton("СОХРАНИТЬ");
    private JRadioButton autoEnсryption = new JRadioButton("авто режим",true);
    private JRadioButton manualEncryption = new JRadioButton("ручной режим");
    private JRadioButton manualDecryption = new JRadioButton("Ручная дешифровка", true);
    private JRadioButton bruteForceDecryption = new JRadioButton("Brute force");
    private JRadioButton statisticDecryption = new JRadioButton("Аналитическая");
    private JTabbedPane leftTextAreaTabbedPane = new JTabbedPane();
    public MainWindow(){
        super("CryptoAnalyzer v." + CryptoAnalyzer.CURRENT_VERSION);
        setLayout(null);
        Dimension mainWindowSize = new Dimension(1200,800);
        Dimension currentScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((currentScreenSize.width-mainWindowSize.width)/2,0,mainWindowSize.width,mainWindowSize.height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initializationAllElements();
        addAllElements();
    }
    private void initializationAllElements(){
        initializationLeftTextArea();
        initializationRightTextArea();
        initializationFirstUpperRightTextArea();
        initializationSecondUpperRightTextArea();
        initializationUpperLeftTextArea();
        leftTextAreaScrollPane = new JScrollPane(leftTextArea);
        leftTextAreaSecondTabScrollPane = new JScrollPane(leftTextAreaSecondTab);
        leftTextAreaThirdTabScrollPane = new JScrollPane(leftTextAreaThirdTab);
        initializationLeftTextAreaScrollPane();
        rightTextAreaScrollPane = new JScrollPane(rightTextArea);
        initializationRightTextAreaScrollPane();
        initializationLeftTextAreaTabbedPane();
        initializationShiftSlider();
        initializationButtons();
    }
    private void  addAllElements(){
        add(saveFile);
        add(frequencyAnalyticBarChart);
        add(upperLeftTextArea);
        add(leftTextAreaTabbedPane);
        add(resetLeftTextArea);
        add(resetRightTextArea);
        add(manualEncryption);
        add(autoEnсryption);
        add(encrypt);
        add(decrypt);
        add(manualDecryption);
        add(bruteForceDecryption);
        add(statisticDecryption);
        add(shiftSlider);
        add(upperRightFirstTextArea);
        add(upperRightSecondTextArea);
        add(rightTextAreaScrollPane);
    }
    public JRadioButton getManualDecryption() {
        return manualDecryption;
    }
    public JRadioButton getBruteForceDecryption() {
        return bruteForceDecryption;
    }
    public JRadioButton getStatisticDecryption() {
        return statisticDecryption;
    }
    public JButton getSaveFile() {
        return saveFile;
    }
    public JTextArea getUpperLeftTextArea(){
        return upperLeftTextArea;
    }
    public JButton getFrequencyAnalyticBarChart() {
        return frequencyAnalyticBarChart;
    }
    public JTabbedPane getLeftTextAreaTabbedPane(){
        return leftTextAreaTabbedPane;
    }
    public JTextArea getLeftTextAreaSecondTab() {
        return leftTextAreaSecondTab;
    }
    public JTextArea getLeftTextAreaThirdTab() {
        return leftTextAreaThirdTab;
    }
    public JButton getDecrypt() {
        return decrypt;
    }
    public JButton getResetLeftTextArea() {
        return resetLeftTextArea;
    }
    public JButton getResetRightTextArea() {
        return resetRightTextArea;
    }
    public JButton getEncrypt() {
        return encrypt;
    }
    public JRadioButton getAutoEnсryption() {
        return autoEnсryption;
    }
    public JRadioButton getManualEncryption() {
        return manualEncryption;
    }
    public JSlider getShiftSlider(){return shiftSlider;}
    public JTextArea getUpperRightSecondTextArea(){
        return upperRightSecondTextArea;
    }
    public JTextArea getUpperRightFirstTextArea() {
        return upperRightFirstTextArea;
    }
    public JTextArea getRightTextArea() {
        return rightTextArea;
    }
    public JScrollPane getRightTextAreaScrollPane() {
        return rightTextAreaScrollPane;
    }
    public JTextArea getLeftTextArea() {
        return leftTextArea;
    }
    public JScrollPane getLeftTextAreaScrollPane() {
        return leftTextAreaScrollPane;
    }
    private void initializationButtons(){
        saveFile.setBounds(1030,700,150,25);
        resetLeftTextArea.setBounds(10,700,150,25);
        resetRightTextArea.setBounds(680,700,150,25);
        frequencyAnalyticBarChart.setBounds(10,660,500,25);
        ButtonGroup encryptRadioButtonGroup = new ButtonGroup();
        ButtonGroup decryptRadioButtonGroup = new ButtonGroup();
        manualDecryption.setBounds(520,300,150,25);
        bruteForceDecryption.setBounds(520,320,150,25);
        statisticDecryption.setBounds(520,340,150,25);
        decrypt.setBounds(520,250,150,25);
        encrypt.setBounds(520,120,150,25);
        autoEnсryption.setBounds(540,170,110,15);
        manualEncryption.setBounds(540,190,110,15);
        decryptRadioButtonGroup.add(manualDecryption);
        decryptRadioButtonGroup.add(bruteForceDecryption);
        decryptRadioButtonGroup.add(statisticDecryption);
        encryptRadioButtonGroup.add(autoEnсryption);
        encryptRadioButtonGroup.add(manualEncryption);
    }
    private void initializationUpperLeftTextArea(){
        upperLeftTextArea.setBounds(10,5,495,18);
        upperLeftTextArea.setBorder(BorderFactory.createLoweredBevelBorder());
        upperLeftTextArea.setFont(new Font("Serif", Font.PLAIN, 10));
        upperLeftTextArea.setText("");
    }
    private void initializationLeftTextArea(){
        leftTextArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        leftTextArea.setBackground(Color.WHITE);
        leftTextArea.setText("");
        leftTextAreaSecondTab.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        leftTextAreaSecondTab.setBackground(Color.WHITE);
        leftTextAreaSecondTab.setText("");
        leftTextAreaThirdTab.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        leftTextAreaThirdTab.setBackground(Color.WHITE);
        leftTextAreaThirdTab.setText("");
    }
    private void initializationRightTextArea(){
        rightTextArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        rightTextArea.setBackground(Color.WHITE);
        rightTextArea.setText("");
    }
    private void initializationFirstUpperRightTextArea(){
        upperRightFirstTextArea.setBounds(680,5,495,18);
        upperRightFirstTextArea.setBorder(BorderFactory.createLoweredBevelBorder());
        upperRightFirstTextArea.setFont(new Font("Serif", Font.ITALIC, 10));
    }
    private void initializationSecondUpperRightTextArea(){
        upperRightSecondTextArea.setBounds(680,28,495,18);
        upperRightSecondTextArea.setBorder(BorderFactory.createLoweredBevelBorder());
        upperRightSecondTextArea.setFont(new Font("Serif", Font.PLAIN,10));
    }
    private void initializationLeftTextAreaScrollPane(){
        leftTextAreaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        leftTextAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        leftTextAreaSecondTabScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        leftTextAreaSecondTabScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        leftTextAreaThirdTabScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        leftTextAreaThirdTabScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }
    private void initializationLeftTextAreaTabbedPane(){
        leftTextAreaTabbedPane.setBounds(10,30,500,620);
        leftTextAreaTabbedPane.addTab("Основной текст", leftTextAreaScrollPane);
        leftTextAreaTabbedPane.addTab("Контрольный текст", leftTextAreaSecondTabScrollPane);
        leftTextAreaTabbedPane.addTab("Статистика", leftTextAreaThirdTabScrollPane);
    }
    private void initializationRightTextAreaScrollPane(){
        rightTextAreaScrollPane.setBounds(680,50,500,600);
        rightTextAreaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        rightTextAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }
    private void initializationShiftSlider(){
        BoundedRangeModel model = new DefaultBoundedRangeModel(1,1,1,74 );
        shiftSlider = new JSlider(model);
        shiftSlider.setOrientation(JSlider.HORIZONTAL);
        shiftSlider.setBounds(680,650,495,40);
        shiftSlider.setMajorTickSpacing(10);
        shiftSlider.setMinorTickSpacing(1);
        shiftSlider.setPaintTicks(true);
    }
}
