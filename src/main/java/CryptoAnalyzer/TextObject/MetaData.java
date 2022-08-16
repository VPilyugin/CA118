package CryptoAnalyzer.TextObject;

import java.util.ArrayList;
import java.util.List;

public class MetaData {
    private int shift = 0;
    private List<MetaDataElement> metaDataElementList = new ArrayList<>();
    private int numberOfTotalElements = 0;
    private int numberOfValidElements = 0;

    public int getNumberOfTotalElements() {
        return numberOfTotalElements;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public void setNumberOfTotalElements(int numberOfTotalElements) {
        this.numberOfTotalElements = numberOfTotalElements;
    }

    public int getNumberOfValidElements() {
        return numberOfValidElements;
    }

    public void setNumberOfValidElements(int numberOfValidElements) {
        this.numberOfValidElements = numberOfValidElements;
    }

    public List<MetaDataElement> getMetaDataElementList() {
        return metaDataElementList;
    }

    public void addMetaObjectElement(MetaDataElement metaDataElement) {
        this.metaDataElementList.add(metaDataElement);
    }

    public static class MetaDataElement {
        private int validator = 100;

        public int getValidator() {
            return validator;
        }

        public void setValidator(int validator) {
            this.validator = validator;
        }

        public void addValidator(int increment) {
            this.validator+=increment;
        }


    }
}
