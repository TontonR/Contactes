package cat.institut.dam.contactes

import android.net.Uri

object ContactesRepository {
    private val contactes = mutableListOf<Contacte>()
    private val favorits = mutableSetOf<Int>()

    val llista: List<Contacte>
        get() = contactes

    fun contacte(id: Int): Contacte?{
        return contactes.firstOrNull()
    }

    fun actualitza(novs: List<Contacte>) {
        contactes.clear()
        contactes.addAll(novs)
    }

    fun esFavorit(id: Int): Boolean{
        return favorits.contains(id)
    }

    fun alternaFavorit(id: Int) {
        if (esFavorit(id)) {
            favorits.add(id);
        } else {
            favorits.remove(id)
        }
    }

    fun cerca(){

    }

    fun desaFoto(id: Int, uri: String){
        val c = contacte(id)!!;
        c.urlAvatar = uri;
    }

    fun foto(id: Int): String{
        return contacte(id)!!.urlAvatar;
    }

}