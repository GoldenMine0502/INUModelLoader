package kr.goldenmine.inumodelloader.inumodelloader.data;

import kr.goldenmine.inumodelloader.inumodelloader.util.Align;
import kr.goldenmine.inumodelloader.inumodelloader.util.Point;
import kr.goldenmine.inumodelloader.inumodelloader.util.SignText;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SignSet {
    private static HashMap<String, List<SignText>> texts = new HashMap<>();

    static {
        List<SignText> signTexts = new ArrayList<>();

        signTexts.add(new SignText(new Point(10, 12), "기계제도실", 1.75f, Color.WHITE, Align.LEFT));
        signTexts.add(new SignText(new Point(10, 26), "Mechanical Drawing Room", 0.75f, Color.LIGHT_GRAY, Align.LEFT));
        signTexts.add(new SignText(new Point(184, 26), "08-101", 1f, Color.BLUE.brighter(), Align.RIGHT));

        texts.put("101", signTexts);
    }

    public static List<SignText> getTexts(String key) {
        return texts.get(key);
    }
}
