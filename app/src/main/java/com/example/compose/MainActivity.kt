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
import org.mariuszgromada.math.mxparser.Expression

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
    var canAddDecimal = remember{ mutableStateOf(true) }
    var canAddOperator  = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(1f)
    ) {
        Text(text=number.value)

            Row(

            ) {
                for(i in 9 downTo  7)
                Button(
                    onClick = {
                        number.value+=i.toString()
                        canAddOperator.value=true
                    },

                    ){
                   Text(text = i.toString())

                }
                Button(onClick = {
                    if(canAddOperator.value){
                    number.value+="+"
                    canAddOperator.value=false}
                }){
                    Text(text="+",color= Color.Red)
                }
            }
            Row() {
                for(i in 6 downTo  4){
                    Button(
                        onClick = {
                            number.value+=i.toString()
                            canAddOperator.value=true
                        },
                        ){
                        Text(text = i.toString()) }}
                Button(onClick = {
                    if(canAddOperator.value){
                        number.value+="-"
                        canAddOperator.value=false}
                }){
                    Text(text="-",color= Color.Red)
                }

            }
            Row() {
                for(i in 3 downTo  1)
                     Button(
                         onClick = {
                             number.value+=i.toString()
                             canAddOperator.value=true},
                         ){
                         Text(text = i.toString())


                    }
                    Button(onClick = {
                        if(canAddOperator.value){
                            number.value+="*"
                            canAddOperator.value=false}
                    }){
                        Text(text="*",color= Color.Red)
                    }
            }
            Row(){
               Button(
                   onClick = {
                       number.value = ""
                       canAddDecimal.value = true
                       canAddOperator.value = false
                   }
               )
               {
                   Text(
                       text="AC",
                       color=Color.Red
                   )
               }
                Button(
                    onClick = {
                        number.value+="0"
                        canAddOperator.value=true
                    }
                ){
                    Text(text="0")
                }
                Button(
                    onClick={
                        var len = number.value.length
                        if(len>0){
                        number.value = number.value.substring(0,len-1)
                        Log.d("Clicked","$len")}
                    }

                ){
                    Text(text="⌫ ")
                }




            }
            Button(
                onClick={
                    if(number.value.length>0){
                    val result = number.value
                    val answer = Expression(result).calculate()
                    number.value = answer.toString()}



                }
            ){
                Text(text="=")
            }
    }

}

