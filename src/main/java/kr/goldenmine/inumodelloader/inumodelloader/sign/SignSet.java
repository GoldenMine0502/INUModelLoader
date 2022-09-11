package kr.goldenmine.inumodelloader.inumodelloader.sign;

import kr.goldenmine.inumodelloader.inumodelloader.util.Align;
import kr.goldenmine.inumodelloader.inumodelloader.util.Point;
import kr.goldenmine.inumodelloader.inumodelloader.util.SignInfo;
import kr.goldenmine.inumodelloader.inumodelloader.util.SignText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class SignSet {
    private static HashMap<String, SignInfo> signInfoMap = new HashMap<>();

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static void loadCsvAll(List<String> lines) {
        List<String> logs = new ArrayList<>();

        lines.stream().skip(8).map(it -> it.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")).forEach(line -> {
            try {
                String signType = line[1];
                if(signType.length() > 0) {
                    String imageType = line[5];

                    int numberOfPoints = Integer.parseInt(line[6]);

                    float overallMultiplier = Float.parseFloat(line[2]);

                    float translateX = Float.parseFloat(line[3]);
                    float translateY = Float.parseFloat(line[4]);

                    List<SignText> texts = new ArrayList<SignText>();

                    for (int i = 0; i < numberOfPoints; i++) {
                        try {
                            int index = 6 * i + 7;

                            double x = Double.parseDouble(line[index]);
                            double y = Double.parseDouble(line[index + 1]);
                            String text = line[index + 2];
                            float multiplier = Float.parseFloat(line[index + 3]);

                            String colorCell = line[index + 4].replaceFirst("#", "");
                            int color = colorCell.length() == 8 ? Integer.parseInt(colorCell, 16) : ((Integer.parseInt(colorCell, 16) & 0x00FFFFFF) | (0x000000FF << 24));

                            Align align = Align.getAlignFromString(line[index + 5]);
                            SignText signText = new SignText(new Point(x, y), text, multiplier, color, align);

                            texts.add(signText);
                        } catch(Exception ex) {
                            logs.add("exception(adding)");
                        }
                    }

                    logs.add(signType);
//                    LOGGER.info("add sign type: " + signType + ", " + imageType + ", " + texts);
                    signInfoMap.put(signType, new SignInfo(signType, imageType, overallMultiplier, translateX, translateY, texts));
                } else {
                    logs.add("empty");
//                    LOGGER.info("empty: " + imageType + ", " + texts);
                }
            } catch (Exception ex) {
                logs.add("exception");
                ex.printStackTrace();
            }
        });

        LOGGER.info(join(logs));
    }

    public static String join(List<String> str) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < str.size(); i++) {
            sb.append(str.get(i));
            if(i != str.size() - 1)
                sb.append(", ");
        }

        return sb.toString();
    }

    public static SignInfo getSignInfo(String key) {
        return signInfoMap.get(key);
    }

    public static void addTexts(String key, SignInfo signInfo) {
        signInfoMap.put(key, signInfo);
    }

    public static HashMap<String, SignInfo> getSignInfoMap() {
        return signInfoMap;
    }
}
