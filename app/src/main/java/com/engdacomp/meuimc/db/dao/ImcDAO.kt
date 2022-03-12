package com.engdacomp.meuimc.db.dao

import androidx.room.*
import com.engdacomp.meuimc.db.model.Imc

@Dao
interface ImcDAO {
    @Query("select * from imc")
    fun getAll(): List<Imc>

    @Query("select * from imc where id in (:userIds)")
    fun loadAllByIds(userIds: IntArray) : List<Imc>

    @Insert
    fun insertAll(vararg users: Imc)

    @Delete
    fun delete(user: Imc)

    @Update
    fun update(user: Imc)
}