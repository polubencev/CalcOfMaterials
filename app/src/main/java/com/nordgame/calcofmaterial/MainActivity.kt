package com.nordgame.calcofmaterial

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.dataStore
import androidx.navigation.compose.rememberNavController
import com.nordgame.calcofmaterial.ui.components.GUI_BottomBar
import com.nordgame.calcofmaterial.ui.theme.CalcOfMaterialsTheme

class MainActivity : ComponentActivity() {
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalcOfMaterialsTheme {
                val navController = rememberNavController()

                   /* DataStore<Preferences> by preferencesDataStore(name = "settings")
*/

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {

                    /*Content*/

                    GUI_BottomBar(navController = navController)

                }
            }
        }
    }
}
