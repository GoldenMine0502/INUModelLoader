package kr.goldenmine.inumodelloader.assetscreator

import java.io.FileInputStream
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.awt.Color

fun main(args: Array<String>) {
    val fileName = "signtext.xlsx"

    FileInputStream(fileName).use { fis ->
        val workbook = XSSFWorkbook(fis)
        val sheet = workbook.getSheetAt(0)

        val rows = sheet.iterator()
        repeat(6) {
            rows.next()
        }

        while(rows.hasNext()) {
            val row = rows.next()

            val signType = row.getCell(1).stringCellValue
            val imageType = row.getCell(2).numericCellValue.toInt()
            val numberOfPoints = row.getCell(3).numericCellValue.toInt()

            for(i in 0 until numberOfPoints) {
                val index = i * 6 + 4

                val x = row.getCell(index).numericCellValue
                val y = row.getCell(index + 1).numericCellValue
                val text = row.getCell(index + 2).stringCellValue
                val multiplier = row.getCell(index + 3).numericCellValue

                // #FFFFFF -> Integer.MAX_VALUE
                val colorCell = row.getCell(index + 4).stringCellValue.replaceFirst("#", "")
                val color = if(colorCell.length == 6) Integer.parseInt(colorCell, 16) else (Integer.parseInt(colorCell, 16) and 0x00FFFFFF) or (0x000000FF shl 24)
                val colorObj = Color(color, true)
                println("${colorObj.red} ${colorObj.green} ${colorObj.blue} ${colorObj.alpha}")
            }
        }
    }
}