package kr.goldenmine.inumodelloader.inumodelloader.sign;

import kr.goldenmine.inumodelloader.inumodelloader.util.Align;
import kr.goldenmine.inumodelloader.inumodelloader.util.Point;
import kr.goldenmine.inumodelloader.inumodelloader.util.SignInfo;
import kr.goldenmine.inumodelloader.inumodelloader.util.SignText;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SignSet {
    private static HashMap<String, SignInfo> signInfoMap = new HashMap<>();

    static {

    }

    public static String getStableCellStringValue(Cell cell) {
        String value;
        try {
            value = cell.getStringCellValue();
        } catch(IllegalStateException ex) {
            value = String.valueOf(cell.getNumericCellValue());
            if(value.endsWith(".0"))
                value = value.substring(0, value.length() - 2);
        }

        return value;
    }

    public static void loadAll(InputStream sheetInputStream) {
        try(InputStream inputStream = sheetInputStream) {
            if(inputStream != null) {
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                XSSFSheet sheet = workbook.getSheetAt(0);

                Iterator<Row> rows = sheet.iterator();

                // skip 5회
                for(int i = 0; i < 5; i++) rows.next();

                while(rows.hasNext()) {
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

                        System.out.println("add sign type: " + signType + ", " + imageType + ", " + texts);
                        signInfoMap.put(signType, new SignInfo(signType, imageType, texts));
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                throw new RuntimeException("no assets: assets/inumodelloader/signs/signtext.xlsx");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
