package com.nordgame.calcofmaterial.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.FloatingActionButtonElevation
import androidx.compose.material.Text
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nordgame.calcofmaterial.R
import com.nordgame.calcofmaterial.utils.ColorsApp
import com.nordgame.calcofmaterial.utils.Constants


@Composable
fun GUI_BottomBar(navController: NavHostController) {
val save = remember {
    mutableStateOf(false)
}
    Scaffold(bottomBar = {
        BottomNavigationBar(navController = navController)
    }, content = { padding ->
        NavHostContainer(navController = navController, padding = padding, save)
    },
        floatingActionButton = {FloatingActionButton(
            onClick = { save.value = true },
            content = {Image(ImageBitmap.imageResource(R.drawable.discicon),
                contentDescription = "save")},
            containerColor = Color.Transparent,
            shape = CircleShape,
            elevation = androidx.compose.material3.FloatingActionButtonDefaults.elevation(),
            )
        })
}

@Composable
fun NavHostContainer(navController: NavHostController, padding: PaddingValues, save: MutableState<Boolean>) {
    NavHost(navController = navController, startDestination = "woodplank",
        modifier = Modifier.padding(paddingValues = padding),
        builder = {
            composable("woodplank") {

                WoodPlankScreen(save)
            }
            composable("firewood") {
                FireWoodScreen()
            }
        })

}

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    BottomNavigation(
        backgroundColor = Color(ColorsApp.BottomBar)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Constants.BottomNavItems.forEach { item ->
            BottomNavigationItem(selected = currentRoute == item.path,
                onClick = {
                    navController.navigate(item.path)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = "Icon", tint = Color.Unspecified
                    )
                },
                label = {
                    Text(text = item.label)
                }

            )
        }
    }

}