package com.vishal.crudwithrecycler

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
  class EntityData(
  @PrimaryKey(autoGenerate = true)
     var id : Int = 0,

        @ColumnInfo
        var name : String ?= null,

      @ColumnInfo
       var sClass : String ?= null
  )



