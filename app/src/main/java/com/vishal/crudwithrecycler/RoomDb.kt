package com.vishal.crudwithrecycler

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(version = 1, entities = [EntityData::class])

abstract class RoomDb : RoomDatabase() {

    abstract fun studentDao() : DataDao
    companion object{
        private var roomDbClass : RoomDb ?= null

        fun createDatabase(context: Context) : RoomDb{
            if(roomDbClass == null){
                roomDbClass = Room.databaseBuilder(context,
                    RoomDb::class.java,
                   context.resources.getString(R.string.app_name)).build()
            }
            return roomDbClass!!
        }
    }
}