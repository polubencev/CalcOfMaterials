package com.nordgame.calcofmaterial.ui.components

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.nordgame.calcofmaterial.MainActivity

@Composable
fun SetSettings(_w: MutableState<Int>, _h: MutableState<Int>, l:MutableState<Int>, _c: MutableState<Int>){
    val sharedPref :MainActivity = MainActivity()
    sharedPref
}