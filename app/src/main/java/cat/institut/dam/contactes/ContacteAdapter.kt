package cat.institut.dam.contactes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ContacteAdapter (
        private val onContacteClick: (Contacte) -> Unit,
        private val onFavoriteClick: (Contacte) -> Unit
    ) : RecyclerView.Adapter<ContacteAdapter.FilaViewHolder>() {
    private var contactes: List<Contacte> = emptyList()
    class FilaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvInicials: TextView = itemView.findViewById(R.id.tvInicials)
        val tvNom: TextView = itemView.findViewById(R.id.tvNom)
        val tvCiutat: TextView = itemView.findViewById(R.id.tvCiutat)
        val tvCorreu: TextView = itemView.findViewById(R.id.tvCorreu)
        val chkFavorit: CheckBox = itemView.findViewById(R.id.chkFavorit)
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
        holder.tvInicials.text = contacte.inicials
        holder.tvNom.text = contacte.nom
        holder.tvCiutat.text = contacte.ciutat
        holder.tvCorreu.text = contacte.email

        holder.chkFavorit.setOnCheckedChangeListener(null)
        holder.chkFavorit.isChecked = ContactesRepository.esFavorit(contacte.id)
        holder.chkFavorit.setOnCheckedChangeListener { _, _ ->
            onFavoriteClick(contacte)
        }

        holder.itemView.setOnClickListener {
            onContacteClick(contacte)
        }

    }

    override fun getItemCount(): Int = contactes.size
}

