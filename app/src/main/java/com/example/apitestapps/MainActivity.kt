package com.example.apitestapps

import android.R.attr.fontWeight
import android.R.id.content
import android.os.Bundle
import android.view.Menu
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerDefaults.scrimColor
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apitestapps.ui.theme.APItestAppsTheme
import kotlinx.coroutines.launch

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
    val myFont = FontFamily(Font(R.font.porter, FontWeight.Light))
    val items = listOf(" ","Домой", "Профиль", "Настройки")
    val selectedItem = remember { mutableStateOf(items[0]) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var isConnected by remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(drawerContainerColor = Color.DarkGray) {
                items.forEach { item ->
                    TextButton(
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = item
                        }
                    ) {
                        Text(
                            item,
                            fontSize = 22.sp,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                    }
                }
            }
        }
    ) {
        Box(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 56.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .height(360.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "V P N",
                        color = Color.White,
                        fontSize = 40.sp,
                        fontFamily = myFont,
                        fontWeight = FontWeight.Light
                    )
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 120.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (isConnected) "d i s c o n n e c t" else "c o n n e c t",
                            color = Color.LightGray,
                            fontSize = 32.sp,
                            fontFamily = myFont,
                            fontWeight = FontWeight.Light
                        )
                    }
                }
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        Modifier
                            .size(220.dp)
                            .drawBehind {
                                val glowColor = Color(0xFF37FED9).copy(alpha = 0.99f)
                                val radius = size.minDimension / 1.6f
                                drawCircle(
                                    brush = Brush.radialGradient(
                                        colors = listOf(glowColor, Color.Transparent),
                                        center = center,
                                        radius = radius
                                    ),
                                    radius = radius,
                                    center = center
                                )
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        OutlinedButton(
                            onClick = { isConnected = !isConnected },
                            modifier = Modifier
                                .size(290.dp)
                                .clip(CircleShape),
                            shape = CircleShape,
                            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Black),
                            border = BorderStroke(6.dp, color = Color(0xFF164C42)),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.conection),
                                contentDescription = "Иконка коннект",
                                modifier = Modifier.size(80.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    selectedItem.value,
                    fontSize = 28.sp,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            IconButton(
                onClick = {
                    scope.launch {
                        if (drawerState.isClosed) drawerState.open()
                        else drawerState.close()
                    }
                },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = "Кнопка меню",
                    tint = Color.Gray
                )
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