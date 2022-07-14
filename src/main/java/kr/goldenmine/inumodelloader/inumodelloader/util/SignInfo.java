package kr.goldenmine.inumodelloader.inumodelloader.util;

import java.util.List;

public class SignInfo {
    private String type;
    private String signTextureType;
    private List<SignText> texts;

    public SignInfo(String type, String signTextureType, List<SignText> texts) {
        this.type = type;
        this.signTextureType = signTextureType;
        this.texts = texts;
    }

    public String getType() {
        return type;
    }

    public String getSignTextureType() {
        return signTextureType;
    }

    public List<SignText> getTexts() {
        return texts;
    }
}
