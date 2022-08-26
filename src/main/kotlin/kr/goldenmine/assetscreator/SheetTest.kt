package kr.goldenmine.assetscreator

import kr.goldenmine.inumodelloader.inumodelloader.sign.SignSet
import org.apache.commons.io.IOUtils
import java.io.ByteArrayInputStream

import java.io.InputStream

fun main() {
    try {
        val input = Thread.currentThread().contextClassLoader.getResourceAsStream("data/signtext.xlsx")
        val bytes = IOUtils.toByteArray(input)
        val arrayInputStream = ByteArrayInputStream(bytes)

        SignSet.loadAll(arrayInputStream)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}