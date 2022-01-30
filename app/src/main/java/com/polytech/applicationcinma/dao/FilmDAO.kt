package com.polytech.applicationcinma.dao

import androidx.room.*
import com.polytech.applicationcinma.model.Film

@Dao
interface FilmDAO {

    @Insert
    fun insert(user: Film): Long

    @Delete
    fun delete(user: Film)

    @Update
    fun update(user: Film)

    @Query("SELECT * from Film WHERE NoFilm = :key")
    fun get(key: Int): Film?

}