package kr.goldenmine.inumodelloader.inumodelloader.sign;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvRecurse;
import com.opencsv.exceptions.CsvException;
import kr.goldenmine.inumodelloader.inumodelloader.util.Align;
import kr.goldenmine.inumodelloader.inumodelloader.util.Point;
import kr.goldenmine.inumodelloader.inumodelloader.util.SignInfo;
import kr.goldenmine.inumodelloader.inumodelloader.util.SignText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.*;

public class SignSet {
    private static HashMap<String, SignInfo> signInfoMap = new HashMap<>();

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static void loadCsvAll(List<String> lines) {
        CSVParser parser = new CSVParser();

        List<String> logs = new ArrayList<>();

        lines.stream().skip(8).map(it -> {
            try {
                return parser.parseLine(it);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).forEach(it -> {
            try {
                String signType = it[1];
                if(signType.length() > 0) {
                    String imageType = it[2];

                    int numberOfPoints = Integer.parseInt(it[3]);

                    List<SignText> texts = new ArrayList<SignText>();

                    for (int i = 0; i < numberOfPoints; i++) {
                        int index = 6 * i + 4;

                        double x = Double.parseDouble(it[index]);
                        double y = Double.parseDouble(it[index + 1]);
                        String text = it[index + 2];
                        float multiplier = Float.parseFloat(it[index + 3]);

                        String colorCell = it[index + 4].replaceFirst("#", "");
                        int color = colorCell.length() == 8 ? Integer.parseInt(colorCell, 16) : ((Integer.parseInt(colorCell, 16) & 0x00FFFFFF) | (0x000000FF << 24));

                        Align align = Align.getAlignFromString(it[index + 5]);
                        SignText signText = new SignText(new Point(x, y), text, multiplier, color, align);

                        texts.add(signText);
                    }

                    logs.add(signType);
//                    LOGGER.info("add sign type: " + signType + ", " + imageType + ", " + texts);
                    signInfoMap.put(signType, new SignInfo(signType, imageType, texts));
                } else {
                    logs.add("empty");
//                    LOGGER.info("empty: " + imageType + ", " + texts);
                }
            } catch (Exception ex) {
                logs.add("exception");
//                ex.printStackTrace();
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
