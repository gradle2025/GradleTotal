package com.fns.examtodolist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RationCardTB::class], version = 1)
abstract class RationDatabase:RoomDatabase() {
    abstract  fun getRationdata():RationDao
    companion object{
        @Volatile
        var INSTANCE: RationDatabase?=null
        fun getConnection(context: Context):RationDatabase{
            synchronized(this){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context,RationDatabase::class.java,"RationDB").build()
                }
                return INSTANCE!!
            }
        }
    }

}