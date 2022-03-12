package com.engdacomp.meuimc.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.engdacomp.meuimc.db.dao.ImcDAO
import com.engdacomp.meuimc.db.model.Imc

@Database(entities = arrayOf(Imc::class), version = 1, exportSchema = false)
abstract class MyDataBase: RoomDatabase() {
    abstract fun imcDAO(): ImcDAO
}