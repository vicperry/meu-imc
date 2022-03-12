package com.engdacomp.meuimc.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Imc (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo
    var indice: String,
    @ColumnInfo
    var data: String,
    @ColumnInfo
    var grauObs: String
)