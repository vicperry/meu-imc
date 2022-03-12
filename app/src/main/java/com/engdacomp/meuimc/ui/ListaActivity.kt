package com.engdacomp.meuimc.ui

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.engdacomp.meuimc.R
import com.engdacomp.meuimc.databinding.ListaBinding
import com.engdacomp.meuimc.db.MyDataBase
import com.engdacomp.meuimc.db.model.Imc
import com.engdacomp.meuimc.rv.ImcAdapter
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.collections.ArrayList

class ListaActivity : AppCompatActivity() {

    private lateinit var bindingLista: ListaBinding
    lateinit var db: MyDataBase
    lateinit var itens : ArrayList<Imc>
    lateinit var imc : Imc


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLista = ListaBinding.inflate(layoutInflater)
        val view = bindingLista.root
        setContentView(view)

        db = Room.databaseBuilder(
            applicationContext,
            MyDataBase::class.java,
            "historicoImc").build()
        consulta()

        Toast.makeText(this, "Segure no item para deletar da lista.", Toast.LENGTH_LONG).show()
    }

    fun consulta() {
        Thread(Runnable {
            itens = db.imcDAO().getAll() as ArrayList<Imc>
            popular()
        }).start()    }

    fun popular() {
        runOnUiThread {
            val adapter = ImcAdapter(applicationContext, itens)
            bindingLista.recycler.layoutManager = LinearLayoutManager(applicationContext)
            bindingLista.recycler.itemAnimator = DefaultItemAnimator()
            bindingLista.recycler.adapter = adapter
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var adapter = bindingLista.recycler.adapter as ImcAdapter
        imc = adapter.imc
        if(item!!.itemId == 0) {
            adapter.deleteContato()
            deletarImc(imc)
        }
        return super.onContextItemSelected(item)
    }

    private fun deletarImc(imc: Imc) {
        val timer = Timer()
        val snackbar = Snackbar.make(bindingLista.recycler, "Índice excluído com sucesso.", Snackbar.LENGTH_LONG)
        snackbar.setAction("Desfazer", View.OnClickListener {
            timer.cancel()
            consulta()
        }).show()
        timer.schedule(object : TimerTask() {
            override fun run() {
                db.imcDAO().delete(imc)
            }
        }, 5000)
    }
}