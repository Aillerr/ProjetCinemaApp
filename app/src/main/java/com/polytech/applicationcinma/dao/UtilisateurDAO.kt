package com.polytech.applicationcinma.dao

import androidx.room.*
import com.polytech.applicationcinma.model.Film
import com.polytech.applicationcinma.model.Utilisateur

@Dao
interface UtilisateurDAO {

    @Insert
    fun insert(user: Utilisateur): Int

    @Delete
    fun delete(user: Utilisateur)

    @Update
    fun update(user: Utilisateur)

    @Query("SELECT * from Utilisateur WHERE NoUtil = :key")
    fun get(key: Int): Utilisateur?

}