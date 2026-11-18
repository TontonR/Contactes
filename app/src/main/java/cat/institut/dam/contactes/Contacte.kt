package cat.institut.dam.contactes

import org.json.JSONObject

data class Contacte(
    val id: Int,
    val nom: String,
    val username: String,
    val email: String,
    val telefon: String,
    val web: String,
    val carrer: String,
    val ciutat: String,
    val codiPostal: String,
    val latitud: Double,
    val longitud: Double
){
    val inicialsSplit : List<String> = nom.split(" ");
    val inicials : String = if (inicialsSplit.size > 1) {
        inicialsSplit[0][0].toString() + inicialsSplit [1][0].toString()
    } else {
        inicialsSplit[0][0].toString();
    }
    val adrecaCompleta : String = "$carrer $ciutat $codiPostal";
    val urlAvatar : String = "https://i.pravatar.cc/300?u=$username"

    companion object {
        fun desDeJson(json: JSONObject): Contacte {
            val adreca = json.getJSONObject("address")
            val geo = adreca.getJSONObject("geo")
            return Contacte(
                id = json.getInt("id"),
                nom = json.getString("name"),
                username = json.getString("username"),
                email = json.getString("email"),
                telefon = json.getString("phone"),
                web = json.getString("website"),
                carrer = adreca.getString("street"),
                ciutat = adreca.getString("city"),
                codiPostal = adreca.getString("zipcode"),
                latitud = geo.getDouble("lat"),
                longitud = geo.getDouble("lng")
            )
        }
    }
}