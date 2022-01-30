package com.polytech.applicationcinma.dao

import androidx.room.*
import com.polytech.applicationcinma.model.Acteur

@Dao
interface ActeurDAO {

    @Insert
    fun insert(user: Acteur): Long

    @Delete
    fun delete(user: Acteur)

    @Update
    fun update(user: Acteur)

    @Query("SELECT * from Acteur WHERE NoAct = :key")
    fun get(key: Int): Acteur?

}