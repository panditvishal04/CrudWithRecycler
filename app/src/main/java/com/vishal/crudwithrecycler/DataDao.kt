package com.vishal.crudwithrecycler

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.google.firebase.firestore.auth.User


@Dao
 interface DataDao {

   @Insert
    fun insertIntoStudent(EntityData: EntityData) : Long

    @Query("SELECT * FROM EntityData")
    fun getStudentList() : List<EntityData>

    @Delete()
    fun deleteStudent(EntityData: EntityData)

    @Update
    fun updateStudent(EntityData: EntityData)


}