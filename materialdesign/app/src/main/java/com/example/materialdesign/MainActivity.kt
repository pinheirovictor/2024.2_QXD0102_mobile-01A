package com.example.materialdesign

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.materialdesign.nav.AppNavigation
import com.example.materialdesign.ui.screens.DatePickerExample2
import com.example.materialdesign.ui.screens.FloatingLabelTextFieldExample
import com.example.materialdesign.ui.screens.SignupScreen
import com.example.materialdesign.ui.screens.TabsExample
import com.example.materialdesign.ui.screens.TimePickerExample
import com.example.materialdesign.ui.theme.MaterialdesignTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialdesignTheme {
                Scaffold() {
//                    AppNavigation()
//                    FloatingLabelTextFieldExample()
                    TabsExample()
//                    val navController = rememberNavController()
////                    DatePickerExample2()
//                    TimePickerExample()
//                    SignupScreen(navController = navController)
                }

//                    TimePickerExample()
//                    FloatingLabelTextFieldExample()
//                    TabsExample()

            }
        }
    }
}

