package com.polytech.applicationcinma.dao

import androidx.room.*
import com.polytech.applicationcinma.model.Categorie

@Dao
interface CategorieDAO {

    @Insert
    fun insert(user: Categorie): Long

    @Delete
    fun delete(user: Categorie)

    @Update
    fun update(user: Categorie)

    @Query("SELECT * from Categorie WHERE CodeCat = :key")
    fun get(key: Int): Categorie?

}