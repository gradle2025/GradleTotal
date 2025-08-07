package com.fns.examtodolist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface RationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRationdata(rationCardTB: RationCardTB)

    @Query("Select * from RationCardTB where Category=:categoryId")
    fun getRationDataCategoryWise(categoryId:String): LiveData<List<RationCardTB>>

    @Query("Select * from RationCardTB")
    fun getAllRationData(): LiveData<List<RationCardTB>>
}