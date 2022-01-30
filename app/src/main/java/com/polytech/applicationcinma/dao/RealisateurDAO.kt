package com.polytech.applicationcinma.dao

import androidx.room.*
import com.polytech.applicationcinma.model.Realisateur


@Dao
interface RealisateurDAO {

    @Insert
    fun insert(user: Realisateur): Long

    @Delete
    fun delete(user: Realisateur)

    @Update
    fun update(user: Realisateur)

    @Query("SELECT * from Realisateur WHERE NoRea = :key")
    fun get(key: Int): Realisateur?

}