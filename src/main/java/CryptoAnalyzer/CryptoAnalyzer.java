package CryptoAnalyzer;

import CryptoAnalyzer.Core.*;

public class CryptoAnalyzer {
    public static final double CURRENT_VERSION = 1.18;

    public static void main(String[] args) {
        Session currentSession = new Session();
        new CoreEngine(currentSession);
    }
}
