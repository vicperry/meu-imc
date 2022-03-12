package com.engdacomp.meuimc.rv

import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.engdacomp.meuimc.R
import com.engdacomp.meuimc.db.model.Imc

class ImcAdapter(
    var context: Context,
    var itens: ArrayList<Imc>
): RecyclerView.Adapter<ImcViewHolder>() {
    lateinit var imc: Imc
    var selectedPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImcViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_item, parent, false)
        val holder = ImcViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(viewHolder: ImcViewHolder, position: Int) {
        imc = itens.get(viewHolder.adapterPosition)
        viewHolder.indice_tv.text = imc.indice.toString()
        viewHolder.data_tv.text = imc.data
        viewHolder.grau_obs_tv.text = imc.grauObs

        viewHolder.itemView.setOnClickListener {
            Toast.makeText(context, "IMC clicado: " + imc.indice.toString(), Toast.LENGTH_LONG).show()
            imc = itens.get(viewHolder.adapterPosition)
            selectedPosition = viewHolder.adapterPosition
        }
        viewHolder.itemView.setOnCreateContextMenuListener(object : View.OnCreateContextMenuListener{
            override fun onCreateContextMenu(
                menu: ContextMenu?,
                v: View?,
                menuInfo: ContextMenu.ContextMenuInfo?
            ) {
                menu!!.setHeaderTitle("Ações: ")
                menu!!.add(0, 0, 0, "Deletar")

                imc = itens.get(viewHolder.adapterPosition)
                selectedPosition = viewHolder.adapterPosition
            }
        })

    }

    override fun getItemCount(): Int {
        return itens.size
    }

    fun deleteContato() {
        itens.remove(imc)
        this.notifyItemRemoved(selectedPosition)
    }

}