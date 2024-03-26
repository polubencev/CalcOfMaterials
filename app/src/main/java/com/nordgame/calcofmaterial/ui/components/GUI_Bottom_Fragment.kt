package com.nordgame.calcofmaterial.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nordgame.calcofmaterial.R
import com.nordgame.calcofmaterial.utils.ColorsApp



@Composable
fun GUI_Bottom_Fragment(_title: String, _icon: Int, _readOnly: Boolean, _value: MutableState<Int>) {
    val _temp = remember() {
        mutableStateOf(_value.value.toString())
    }
    if (_temp.value.isEmpty()) {
        _value.value = 0
    } else {
        _value.value = _temp.value.toInt()
    }
    Column(
        modifier = Modifier
            .padding(6.dp)
            .clip(shape = RoundedCornerShape(6.dp))
            .border(2.dp, shape = RoundedCornerShape(6.dp), color = Color.Yellow)
    ) {
        Row(
            modifier = Modifier
                .background(Color(ColorsApp.BottomBarColor))
                .padding(4.dp)
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                (ImageBitmap.imageResource(_icon)),
                modifier = Modifier
                    .padding(start = 2.dp, end = 3.dp)
                    .width(32.dp)
                    .height(32.dp),
                contentDescription = ""
            )
            Text(
                modifier = Modifier
                    .weight(3f)
                    .padding(start = 3.dp),
                text = _title,
                fontSize = 20.sp,
                fontFamily = FontFamily.Default
            )

            OutlinedTextField(
                value = _temp.value,
                { _temp.value = it },
                readOnly = if (_readOnly == false) {
                    false
                } else {
                    true
                },
                textStyle = TextStyle(fontSize = 20.sp, color = Color.Black),
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(2.dp))
                    .background(Color.LightGray)
                    ,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true

            )

            }
        }
    }
}

/*************************Float**************************/
@Composable
fun GUI_Bottom_Fragment_Float(
    _title: String,
    _icon: Int,
    _readOnly: Boolean,
    _value: MutableState<Float>,
) {
    Column(
        modifier = Modifier
            .padding(6.dp)
            .clip(shape = RoundedCornerShape(6.dp))
            .border(2.dp, shape = RoundedCornerShape(6.dp), color = Color.Yellow)
    ) {
        Row(
            modifier = Modifier
                .background(Color(ColorsApp.BottomBarColor))
                .padding(4.dp)
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                (ImageBitmap.imageResource(_icon)),
                modifier = Modifier
                    .padding(start = 2.dp, end = 3.dp)
                    .width(32.dp)
                    .height(32.dp),
                contentDescription = ""
            )
            Text(
                modifier = Modifier
                    .weight(3f)
                    .padding(start = 3.dp),
                text = _title,
                fontSize = 20.sp,
                fontFamily = FontFamily.Default
            )

            OutlinedTextField(
                value = _value.value.toString(),
                { _value.value = it.toFloat() },
                readOnly = if (_readOnly == false) {
                    false
                } else {
                    true
                },
                textStyle = TextStyle(fontSize = 20.sp, color = Color.Black),
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(2.dp))
                    .background(Color.LightGray),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true,
            )
        }
    }
}

/*******************************Результаты расчета**********************/
@Composable
fun GUI_Bottom_Fragment_Result(
    _s: Float, _m3: Float, _v_1_m3: Float,
    sum_m3: Float, sum_m2: Float,
    _count_m3: Float, _count_m2: Float, _price_m3: Float, _price_m2: Float,
) {
    val _temp_float = ((_m3 * 100).toInt()).toFloat() / 100f
    Column(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .border(
                3.dp, shape = RoundedCornerShape(10.dp),
                color = Color.LightGray
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                ImageBitmap.imageResource(R.drawable.calculating), contentDescription = "",
                modifier = Modifier
                    .width(32.dp)
                    .height(32.dp)
                    .padding(start = 3.dp)
            )
            Text(
                text = "Расчет:", modifier = Modifier.padding(start = 4.dp),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                textDecoration = TextDecoration.Underline
            )
        }

        item_Result(title = "Площадь одной доски, м²: ", _s = _s)
        item_Result(title = "Объем 1 доски, м³: ", _s = _v_1_m3)
        item_Result(title = "Количество досок в ${_count_m3} м³: ", _s = (_count_m3 * _m3))
        item_Result(title = "Количество досок в ${_count_m2} м²: ", _s = (_count_m2 / _s))
        item_Result(title = "Сумма за ${_count_m3} м³: ", _s = (_price_m3 * _count_m3))
        item_Result(title = "Сумма за ${_count_m2} м²: ", _s = (_price_m2 * _count_m2))

    }
}

@Composable
fun item_Result(title: String, _s: Float) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(ColorsApp.BottomBarColor))
            .padding(3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(start = 0.dp)
                .weight(5f),
            text = title,
            fontSize = 20.sp,
            fontFamily = FontFamily.Default,
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier
                .padding(end = 6.dp)
                .weight(2f),
            text = ((((_s * 100).toInt().toFloat()) / 100f).toString()),
            fontSize = 20.sp,
            fontFamily = FontFamily.Default,
            textAlign = TextAlign.End
        )
    }
}

