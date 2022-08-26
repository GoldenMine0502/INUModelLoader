package kr.goldenmine.assetscreator

import kr.goldenmine.inumodelloader.inumodelloader.sign.SignSet
import kr.goldenmine.inumodelloader.inumodelloader.util.SignInfo
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun main(args: Array<String>) {
    // woodType이 텍스쳐 종류임
    val inputStream = File("src/main/resources/data/signtext.xlsx").inputStream()
    SignSet.loadAll(inputStream)

    // load all sign contents
//    val sheetLocation = ResourceLocation(Inumodelloader.MOD_ID, "signs/signtext.xlsx")
//    try {
//        SignSet.loadAll(Minecraft.getInstance().resourceManager.getResource(sheetLocation).inputStream)
//    } catch (e: IOException) {
//        e.printStackTrace()
//    }


    SignSet.getSignInfoMap().forEach { (signType, signInfo) ->
        val folder = File("src/main/resources/assets/inumodelloader")

        val blockStateString = """
            {
                "variants": {
                    "": {
                    "model": "inumodelloader:block/inu_sign_$signType"
                    }
                }
            }
        """.trimIndent()

        val modelString = """
            {
                "textures": {
                "particle": "inumodelloader:block/inu_wood_planks"
                }
            }
        """.trimIndent()


        val itemString = """
            {
                "parent": "minecraft:item/generated",
                "textures": {
                    "layer0": "inumodelloader:item/inu_sign_$signType"
                }
            }
        """.trimIndent()

        val image = createItemImage(signType)

        val blockStateFile = File(folder, "blockstates/inu_sign_$signType.json")
        val modelFile = File(folder, "models/block/inu_sign_$signType.json")
        val itemFile = File(folder, "models/item/inu_sign_$signType.json")
        val imageFile = File(folder, "textures/item/inu_sign_$signType.png")

        if (blockStateFile.exists()) blockStateFile.createNewFile()
        if (modelFile.exists()) modelFile.createNewFile()
        if (itemFile.exists()) itemFile.createNewFile()
        if (imageFile.exists()) imageFile.createNewFile()

        blockStateFile.writeText(blockStateString)
        modelFile.writeText(modelString)
        itemFile.writeText(itemString)
        ImageIO.write(image, "png", imageFile)

        image.flush()
        println("{")
        println("SignSet.signInfoMap.put(\"$signType\", new SignInfo(\"$signType\", \"${signInfo.signTextureType}\", \"$${signInfo.texts}\")));")
        println("}")
    }
}

fun createItemImage(type: String): BufferedImage {
    val image = BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB)
    val graphics = image.graphics as Graphics2D

    // 하얀색으로 배경 채우기
    graphics.color = Color.WHITE
    graphics.fillRect(0, 0, 64, 64)

    // 검은색으로 글씨 쓰기
    graphics.color = Color.BLACK
    graphics.font = graphics.font.deriveFont(32F)

    if (type.length < 3) {
        graphics.drawString(type, 0, 28)
    } else {
        graphics.drawString(type.substring(0, 3), 0, 28)
        graphics.font = graphics.font.deriveFont(24F)
        graphics.drawString(type.substring(3), 0, 54)
    }
    return image
}