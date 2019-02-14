package giampy.simon.fotobioreattore;

class ValuesController {

    static final String IMAGE_ID = "IMAGE_IDENTIFIER";
    static final String DESCRIPTION_ID = "DESCRIPTION_IDENTIFIER";
    static final String TEXT_ID = "TEXT_IDENTIFIER";

    static final String FEATURE1 = "FEATURE1_NAME";
    static final String FEATURE2 = "FEATURE2_NAME";
    static final String FEATURE3 = "FEATURE3_NAME";
    static final String FEATURE1_VALUE = "FEATURE1_VALUE";
    static final String FEATURE2_VALUE = "FEATURE2_VALUE";
    static final String FEATURE3_VALUE = "FEATURE3_VALUE";
    static final String INDEX_OF_COSTI = "INDEX_OF_COSTI";

    static final String ENABLED_CHART = "ENABLED CHART";

    private String typeOfContainer;
    private String typeOfAlghe;
    private String typeOfProduct;

    ValuesController() {

    }


    public String getTypeOfContainer() {
        return typeOfContainer;
    }

    void setTypeOfContainer(String typeOfContainer) {
        this.typeOfContainer = typeOfContainer;
    }

    public String getTypeOfAlghe() {
        return typeOfAlghe;
    }

    public void setTypeOfAlghe(String typeOfAlghe) {
        this.typeOfAlghe = typeOfAlghe;
    }

    public String getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(String typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }
}
