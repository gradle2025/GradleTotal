package com.fns.examtodolist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
  data class RationCardTB (
      @PrimaryKey(autoGenerate = true)
      val id :Int,
      val applicantName:String,
      val aadhaarNumber:String,
      val Category:String
)