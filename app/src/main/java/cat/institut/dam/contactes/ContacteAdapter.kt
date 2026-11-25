package cat.institut.dam.contactes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ContacteAdapter : RecyclerView.Adapter<ContacteAdapter.FilaViewHolder>() {
    private var contactes: List<Contacte> = emptyList()
    class FilaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvNom: TextView = itemView.findViewById(R.id.tvNom)
        val tvCiutat: TextView = itemView.findViewById(R.id.tvCiutat)
        val tvCorreu: TextView = itemView.findViewById(R.id.tvCorreu)
    }

    fun actualitza(novs: List<Contacte>) {
        contactes = novs
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContacteAdapter.FilaViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_contacte, parent, false)
        return FilaViewHolder(vista)
    }

    override fun onBindViewHolder(holder: FilaViewHolder, position: Int) {
        val contacte = contactes[position]
        holder.tvNom.text = contacte.nom
        holder.tvCiutat.text = contacte.ciutat
        holder.tvCorreu.text = contacte.email
    }

    override fun getItemCount(): Int = contactes.size
}

