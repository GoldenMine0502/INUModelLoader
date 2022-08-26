package kr.goldenmine.inumodelloader.inumodelloader.sign;

import kr.goldenmine.inumodelloader.inumodelloader.util.Align;
import kr.goldenmine.inumodelloader.inumodelloader.util.Point;
import kr.goldenmine.inumodelloader.inumodelloader.util.SignInfo;
import kr.goldenmine.inumodelloader.inumodelloader.util.SignText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class SignSet {
    private static HashMap<String, SignInfo> signInfoMap = new HashMap<>();

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    static {

    }

    public static String getStableCellStringValue(Cell cell) {
        String value;
        try {
            value = cell.getStringCellValue();
        } catch (IllegalStateException ex) {
            value = String.valueOf(cell.getNumericCellValue());
            if (value.endsWith(".0"))
                value = value.substring(0, value.length() - 2);
        }

        return value;
    }

    private static List<String> splitComma(String str) {
        boolean on = false;

        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == '\"')
                on = !on;

            if (ch == ',' && !on) {
                list.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(ch);
            }
        }

        return list;
    }

    public static void loadAll(List<String> csv) {
        csv.stream().skip(6).forEach(it -> {
            List<String> split = splitComma(it);

            try {
                String signType = split.get(1);
                String imageType = split.get(2);

                int numberOfPoints = Integer.parseInt(split.get(3));

                List<SignText> texts = new ArrayList<SignText>();

                for (int i = 0; i < numberOfPoints; i++) {
                    int index = 6 * i + 4;

                    double x = Double.parseDouble(split.get(index));
                    double y = Double.parseDouble(split.get(index + 1));
                    String text = split.get(index + 2);
                    float multiplier = Float.parseFloat(split.get(index + 3));

                    String colorCell = split.get(index + 4).replaceFirst("#", "");
                    int color = colorCell.length() == 8 ? Integer.parseInt(colorCell, 16) : ((Integer.parseInt(colorCell, 16) & 0x00FFFFFF) | (0x000000FF << 24));

                    Align align = Align.getAlignFromString(split.get(index + 5));
                    SignText signText = new SignText(new Point(x, y), text, multiplier, color, align);

                    texts.add(signText);
                }

                LOGGER.info("add sign type: " + signType + ", " + imageType + ", " + texts);
//                        System.out.println("add sign type: " + signType + ", " + imageType + ", " + texts);
                signInfoMap.put(signType, new SignInfo(signType, imageType, texts));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
    }

    public static void loadAll(InputStream inputStream) throws IOException {
        LOGGER.info("loading signtypes...");
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        LOGGER.info("b");
        XSSFSheet sheet = workbook.getSheetAt(0);
        LOGGER.info("c");
        try {
            if (inputStream != null) {

                Iterator<Row> rows = sheet.iterator();
                LOGGER.info("d");

                // skip 5회
                for (int i = 0; i < 5; i++) rows.next();
                LOGGER.info("e");


                while (rows.hasNext()) {
                    Row row = rows.next();

                    try {
                        String signType = getStableCellStringValue(row.getCell(1));
                        String imageType = getStableCellStringValue(row.getCell(2));

                        int numberOfPoints = (int) row.getCell(3).getNumericCellValue();

                        List<SignText> texts = new ArrayList<SignText>();

                        for (int i = 0; i < numberOfPoints; i++) {
                            int index = 6 * i + 4;

                            double x = row.getCell(index).getNumericCellValue();
                            double y = row.getCell(index + 1).getNumericCellValue();
                            String text = getStableCellStringValue(row.getCell(index + 2));
                            float multiplier = (float) row.getCell(index + 3).getNumericCellValue();

                            String colorCell = getStableCellStringValue(row.getCell(index + 4)).replaceFirst("#", "");
                            int color = colorCell.length() == 8 ? Integer.parseInt(colorCell, 16) : ((Integer.parseInt(colorCell, 16) & 0x00FFFFFF) | (0x000000FF << 24));

                            Align align = Align.getAlignFromString(getStableCellStringValue(row.getCell(index + 5)));
                            SignText signText = new SignText(new Point(x, y), text, multiplier, color, align);

                            texts.add(signText);
                        }

                        if (signType.length() > 0) {
                            System.out.println("add sign type: " + signType + ", " + imageType + ", " + texts);
                            signInfoMap.put(signType, new SignInfo(signType, imageType, texts));
                        } else {
                            System.out.println("empty: " + imageType + ", " + texts);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                inputStream.close();
            } else {
                throw new RuntimeException("no assets");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error loading excel file");
            Arrays.stream(e.getStackTrace()).forEach(it -> {
                LOGGER.info(it.toString());
            });
        }
        LOGGER.info("loaded signtypes");
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
