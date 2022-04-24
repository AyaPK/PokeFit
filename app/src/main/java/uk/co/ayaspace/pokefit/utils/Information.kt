package uk.co.ayaspace.pokefit.utils

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

// responsible for grabbing the information from either the online api or the local db (cache)
class Information {
    private operator fun get(bufferedReader: BufferedReader): String {
        return try {
            val stringBuilder = StringBuilder()
            var inputLine: String?
            while (bufferedReader.readLine().also { inputLine = it } != null) {
                stringBuilder.append(inputLine)
                stringBuilder.append(System.lineSeparator())
            }
            bufferedReader.close()
            stringBuilder.toString().trim { it <= ' ' }
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    fun fromInternet(targetURL: String?): String {
        val str: String
        return try {
            System.setProperty("http.agent", "Chrome")
            val url = URL(targetURL)
            val bufferedReader = BufferedReader(InputStreamReader(url.openStream()))
            str = Information()[bufferedReader]
            str
        } catch (e: Exception) {
            println("COULDN'T REACH API")
            println(e)
            ""
        }
    }
}