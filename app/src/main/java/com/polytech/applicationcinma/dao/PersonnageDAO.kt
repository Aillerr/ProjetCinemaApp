package com.polytech.applicationcinma.dao

import androidx.room.*
import com.polytech.applicationcinma.model.Personnage

@Dao
interface PersonnageDAO {

    @Insert
    fun insert(user: Personnage): Long

    @Delete
    fun delete(user: Personnage)

    @Update
    fun update(user: Personnage)

    @Query("SELECT * from Personnage WHERE NoFilm = :key")
    fun get(key: Int): Personnage?

}

