package cat.institut.dam.contactes

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object ContactesService {
    private val URL_CONTACTES = "https://jsonplaceholder.typicode.com/users"

    fun desDeXarxa(): List<Contacte> {
        val connexio = URL(URL_CONTACTES).openConnection() as HttpURLConnection
        try {
            connexio.requestMethod = "GET"
            connexio.connectTimeout = 8000
            connexio.readTimeout = 8000

            if (connexio.responseCode != HttpURLConnection.HTTP_OK ) {
                throw IllegalStateException("El servidor ha respost amb ${connexio.responseCode}")
            }

            val text = connexio.getInputStream().bufferedReader().use { it.readText() }

            return parseja(text)
        } finally {

        }
    }

    fun desDeRecurs(context: Context): List<Contacte>{
        val contingut = context.assets.open("contactes.json").bufferedReader().use { it.readText() }
        return parseja(contingut)
    }

    fun parseja(text: String): List<Contacte>{
        val array = JSONArray(text)
        val contactes = mutableListOf<Contacte>()
        for (i in 0 until array.length()) {
            val objecte = array.getJSONObject(i)
            val contacte = Contacte.desDeJson(objecte)
            contactes.add(contacte)
        }
        return contactes
    }

    fun baixaImatge(url: String): Bitmap? {
        val connexio = URL(URL_CONTACTES).openConnection() as HttpURLConnection
        connexio.connectTimeout = 8000
        connexio.readTimeout = 8000
        return BitmapFactory.decodeStream(connexio.getInputStream())
    }


}