package kr.goldenmine.inumodelloader.inumodelloader.util;

import java.util.List;

public class SignInfo {
    private String type;
    private String signTextureType;
    private float overallMultiplier;
    private float translateX;
    private float translateY;
    private List<SignText> texts;

    public SignInfo(String type, String signTextureType, float overallMultiplier, float translateX, float translateY, List<SignText> texts) {
        this.type = type;
        this.signTextureType = signTextureType;
        this.overallMultiplier = overallMultiplier;
        this.translateX = translateX;
        this.translateY = translateY;
        this.texts = texts;
    }

    public float getOverallMultiplier() {
        return overallMultiplier;
    }

    public float getTranslateX() {
        return translateX;
    }

    public float getTranslateY() {
        return translateY;
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
