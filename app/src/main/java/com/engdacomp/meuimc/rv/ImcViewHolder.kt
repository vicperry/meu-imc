package com.engdacomp.meuimc.rv

import android.view.ContextMenu
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.engdacomp.meuimc.R
import com.engdacomp.meuimc.databinding.ListaBinding

class ImcViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
    View.OnLongClickListener,
    View.OnClickListener,
    View.OnCreateContextMenuListener {

    val indice_tv : TextView = itemView!!.findViewById(R.id.indice_tv)
    val data_tv : TextView = itemView!!.findViewById(R.id.data_tv)
    val grau_obs_tv : TextView = itemView!!.findViewById(R.id.grau_obs_tv)


    override fun onLongClick(v: View?): Boolean {
        return true
    }

    override fun onClick(v: View?) {
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu!!.setHeaderTitle("Ações: ")
        menu!!.add(0, 0, 0, "Deletar")
    }


}