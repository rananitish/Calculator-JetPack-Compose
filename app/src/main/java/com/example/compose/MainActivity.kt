package com.example.compose

import android.os.Bundle
import android.provider.SyncStateContract.Columns
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text(text = "Compose App")
            Layout()

        }
    }
}
@Preview()
@Composable
fun Layout(){
    var number = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxWidth(1f)
    ) {
        Text(text=number.value)

            Row(
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                for(i in 9 downTo  7)
                Button(
                    onClick = {
                        number.value+=i.toString()
                    },

                    ){
                   Text(text = i.toString())

                }
                Button(onClick = {}){
                    Text(text="+",color= Color.Red)
                }
            }
            Row() {
                for(i in 6 downTo  4){
                    Button(
                        onClick = { },
                        ){
                        Text(text = i.toString()) }}
                Button(onClick = {}){
                    Text(text="-",color= Color.Red)
                }

            }
            Row() {
                for(i in 3 downTo  1)
                     Button(
                         onClick = { },
                         ){
                         Text(text = i.toString())


                    }
                    Button(onClick = {}){
                        Text(text="*",color= Color.Red)
                    }
            }
            Row(){
                for(i in 3 downTo 0){
                    Button(onClick = { /*TODO*/ }) {
                        when(i){
                            1->Text(text="<=")
                            2->Text(text="0")
                            3->Text(text="AC")
                            0->Text(text=".",color= Color.Red)
                        }

                    }
                }
            }
    }

}

