package com.example.apitestapps

import android.R.attr.fontWeight
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apitestapps.ui.theme.APItestAppsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WindowMain(name = "")
        }
    }
}

@Composable
fun WindowMain(name: String, modifier: Modifier = Modifier) {
    val gradient = Brush.verticalGradient(
        0.0f to Color.Black,
        0.8f to Color.Black,
        0.93f to Color(0xFF000080),
        1.0f to Color(0xFF000093)

    )
    val myFont = FontFamily(
        Font(R.font.porter, FontWeight.Light)
    )
    Column(Modifier.fillMaxSize().background(gradient), verticalArrangement = Arrangement.Top){
        Column(Modifier.fillMaxWidth().height(360.dp).padding(top = 36.dp)
            , horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween) {
            Text(text = "V P N", color = Color.White, fontSize = 40.sp, fontFamily = myFont,
               fontWeight = FontWeight.Light)
            Column (Modifier.fillMaxWidth().padding(bottom = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "c o n n e c t", color = Color.White, fontSize = 36.sp, fontFamily = myFont,
                    fontWeight = FontWeight.Light)
            }
        }
        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Box(Modifier.size(200.dp).drawBehind{val glowColor = Color.Blue.copy(alpha = 0.95f)
                val radius = size.minDimension / 1.4f
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(glowColor, Color.Transparent),
                        center = center,
                        radius = radius
                    ),
                    radius = radius,
                    center = center
                )}, contentAlignment = Alignment.Center){
                OutlinedButton( onClick = {}, Modifier.size(220.dp).clip(CircleShape).background(Color.Black), shape = CircleShape,
                    border = BorderStroke(2.dp, color = Color.Transparent), ) {
                    Image(
                        painterResource(id = R.drawable.conection),
                        contentDescription = "Иконка коннект",

                        Modifier.size(80.dp)
                    )
                }
            }

        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    APItestAppsTheme {
        WindowMain(name = "")
    }
}