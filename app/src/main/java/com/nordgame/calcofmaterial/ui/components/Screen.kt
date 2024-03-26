package com.nordgame.calcofmaterial.ui.components

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.nordgame.calcofmaterial.R
import com.nordgame.calcofmaterial.utils.ColorsApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun WoodPlankScreen(save: MutableState<Boolean>) {

val _coroutine = rememberCoroutineScope()
    var w = remember {
        mutableStateOf(180)
    }
    val h = remember {
        mutableStateOf(40)
    }
    val l = remember {
        mutableStateOf(4000)
    }
    val v_cube = remember {
        mutableStateOf(0f)
    }
    val c = remember {
        mutableStateOf(1)
    }
    val s = remember {
        mutableStateOf(0f)
    }
    val price_m3 = remember {
        mutableStateOf(12500f)
    }
    val price_m2 = remember {
        mutableStateOf(350f)
    }
    val calc_for_m3 = remember {
        mutableStateOf(0f)
    }
    val calc_for__m2 = remember {
        mutableStateOf(0f)
    }
    val count_plank_in_m3 = remember {
        mutableStateOf(0f)
    }
    val count_m2 = remember {
        mutableStateOf(1f)
    }
    val count_m3 = remember {
        mutableStateOf(1f)
    }
    val tempData = remember {
        mutableStateOf(SettingsData(0,0,0,0,0f,0f,0f,0f))
    }
    val _context: Context = LocalContext.current


    /******************Save Settings*******************/

    if(save.value) {
        DataToClass(
            context = _context,
            settingsData = tempData,
            w = w.value,
            h = h.value,
            l = l.value,
            c = c.value,
            price_m3 = price_m3.value,
            price_m2 = price_m2.value,
            lot_m3 = count_m3.value,
            lot_m2 = count_m2.value

        )

        _coroutine.launch {
            Log.d("MyDebug", tempData.value.toString())
            save_settings(tempData.value, _context)

        }

        save.value = false
    }
        loadSettings(_context,_w = w, _h = h)


    v_cube.value = CalculateCube(w = w.value, h = h.value, l = l.value, c = c.value)
    s.value = CalculateS(w = w.value, l = l.value, c = c.value)
    calc_for_m3.value = CalculatePriceM3(_res_cube = v_cube.value, _price = price_m3.value)
    calc_for__m2.value = CalculatePriceM2(_s = s.value, _price = price_m2.value)
    count_plank_in_m3.value = CountPlankInM3(_v_m3 = v_cube.value)
    Image(
        ImageBitmap.imageResource(R.drawable.background),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .border(
                    2.dp, shape = RoundedCornerShape(10.dp),
                    color = Color.Green
                ),
            shape = CardDefaults.elevatedShape,
            colors = CardDefaults.cardColors(
                containerColor = Color(ColorsApp.BottomBarColor)
            )
        )
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier
                        .width(90.dp)
                        .height(90.dp)
                        .weight(1f)
                        .padding(start = 3.dp),
                    painter = painterResource(id = R.drawable.wood_plank),
                    contentDescription = "Wood Plank Icons",
                    tint = Color.Unspecified
                )
                Text(
                    modifier = Modifier
                        .weight(2f)
                        .padding(start = 2.dp),
                    text = "Расчет доски и бруса",
                    color = Color.Black,
                    fontFamily = FontFamily.Default,
                    fontSize = 20.sp,
                    style = TextStyle.Default
                )
            }

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(ScrollState(0))
        ) {
            Log.d("MyDebug",v_cube.value.toString())


            /*Основной макет экрана*/
            GUI_Bottom_Fragment(_title = "Ширина доски, мм:", R.drawable.width, false, w)
            GUI_Bottom_Fragment(_title = "Длина доски, мм:", R.drawable.length, false, l)
            GUI_Bottom_Fragment(_title = "Толщина доски, мм:", R.drawable.height, false, h)
            GUI_Bottom_Fragment_Float(_title = "Цена за м³:", R.drawable.ruble, false, price_m3)
            GUI_Bottom_Fragment_Float(_title = "Цена за м²:", R.drawable.ruble_icon, false, price_m2)
            GUI_Bottom_Fragment_Float(_title = "Количество, м³:", R.drawable.planks_icon, false, count_m3)
            GUI_Bottom_Fragment_Float(_title = "Количество, м²:", R.drawable.planks_icon, false, count_m2)
            GUI_Bottom_Fragment_Result(_s = s.value, _m3 = count_plank_in_m3.value,
                v_cube.value, calc_for_m3.value, calc_for__m2.value, _count_m3 = count_m3.value,
                _count_m2 = count_m2.value, price_m3.value, price_m2.value)
        }

    }

}

@Composable
fun FireWoodScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.firewood),
            contentDescription = "Firewood Icons"
        )
        Text(text = "FireWood screen", color = Color.Black)
    }
}

/***********************Calculating*****************************/

@Composable
fun CalculateCube(w: Int, h: Int, l: Int, c: Int): Float {
    if ((w != 0) && (h != 0) && (l != 0) && (c != 0)) {
        try {
            return (c * (w * h * l) / 1000000000f)
        } catch (e: NullPointerException) {
            return 0f
        }
    } else {
        return 0f

    }
}

@Composable
fun CalculateS(w: Int, l: Int, c: Int): Float {
    if ((w != 0) && (l != 0) && (c != 0)) {
        try {
            return (c * (w * l) / 1000000f)
        } catch (e: NullPointerException) {
            return 0f
        }
    } else {
        return 0f

    }
}

@Composable
fun CalculatePriceM3(_res_cube: Float, _price: Float): Float {

    if ((_res_cube != 0f) && (_price != 0f)) {
        try {
            return (_res_cube * _price)
        } catch (e: NullPointerException) {
            return 0f
        }
    } else {
        return 0f

    }
}

@Composable
fun CalculatePriceM2(_s: Float, _price: Float): Float {
    if ((_s != 0f) && (_price != 0f)) {
        try {
            return (_s * _price)
        } catch (e: NullPointerException) {
            return 0f
        }
    } else {
        return 0f

    }
}
@Composable
fun CountPlankInM3(_v_m3: Float): Float {
    if (_v_m3 != 0f) {
        try {
            return (1f/_v_m3)
        } catch (e: NullPointerException) {
            return 0f
        }
    } else {
        return 0f

    }
}
/*****************************SAVE_SETTINGS*************************/
suspend fun save_settings(_settings: SettingsData, _context: Context ){
    _context.dataStore.edit {it ->
        it[intPreferencesKey("_w")] = _settings._w
        it[intPreferencesKey("_h")] = _settings._h
        it[intPreferencesKey("_l")] = _settings._l
        it[intPreferencesKey("_c")] = _settings._c
        it[floatPreferencesKey("_price_m3")] = _settings._price_m3
        it[floatPreferencesKey("_price_m2")] = _settings._price_m2
        it[floatPreferencesKey("_lot_m3")] = _settings._lot_m3
        it[floatPreferencesKey("_lot_m2")] = _settings._lot_m2
        Toast.makeText(_context, "Сохранено!", Toast.LENGTH_SHORT ).show()
    }

}
@Composable
fun DataToClass(context: Context, settingsData: MutableState<SettingsData>, w: Int, h:Int, l:Int,c: Int,
                price_m3:Float, price_m2: Float,
                lot_m3: Float, lot_m2:Float){
    settingsData.value._w = w
    settingsData.value._h = h
    settingsData.value._l = l
    settingsData.value._c = c
    settingsData.value._price_m3 = price_m3
    settingsData.value._price_m2 = price_m2
    settingsData.value._lot_m3 = lot_m3
    settingsData.value._lot_m2 = lot_m2
   // Toast.makeText(context, "DataToClass!", Toast.LENGTH_SHORT ).show()
}
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun loadSettings(_context: Context, _w: MutableState<Int>, _h: MutableState<Int>){
    val flow: Flow<Int> = _context.dataStore.data.map { pref ->
       (pref[intPreferencesKey("_w")]?:0)
       (pref[intPreferencesKey("_h")]?:0)
    }
//Toast.makeText(LocalContext.current,"LoadSettings!", Toast.LENGTH_SHORT).show()
    Log.d("MyDebug", flow.toString()+ " --Flow")
    val _coroutine = rememberCoroutineScope()
    _coroutine.launch {
        flow.collect{
            _w.value = it
            //_h.value = it

        }
    }

}
