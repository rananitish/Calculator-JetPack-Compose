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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.ComposeTheme
import org.mariuszgromada.math.mxparser.Expression
import java.lang.Double.NaN

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Layout()



        }
    }
}

@Preview()
@Composable
fun Layout(

){

    val number = remember { mutableStateOf("") }
    val canAddDecimal = remember { mutableStateOf(true) }
    val canAddOperator  = remember { mutableStateOf(false) }

    fun getNumber(i:String){
        if(i == "." && canAddDecimal.value){
            number.value+=i

            canAddDecimal.value = false
        }
        else if (i!="."){
            number.value+=i


        }
        canAddOperator.value = true
    }
    fun getOperator(i:String){
        if(canAddOperator.value){
            number.value += i
            canAddOperator.value = false
            canAddDecimal.value = true
        }
    }
    fun allClear(i:String){

        number.value=""
        canAddOperator.value = false
        canAddDecimal.value = true
    }
    fun backSpace(i:String){
        val len = i.length
        if(i[len-1]=='.') canAddDecimal.value = true
        if(len>0){
        number.value = i.substring(0,len-1)
        canAddOperator.value=true
        }

    }


Box(
    modifier = Modifier.background(Color.Black)

) {
    Column(
        modifier = Modifier.height(850.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(text=number.value, fontSize = 24.sp, textAlign = TextAlign.End, modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth(),
            color = Color.Gray
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)


        ){
            Button(
                onClick = {
                    allClear(number.value)

                },modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)

            )
            {
                Text(
                    text="AC",
                    color=Color.Red,
                    fontSize = 18.sp
                )
            }
            Button(
                onClick={
                    backSpace(number.value)
                },modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)


            ){
                Text(text="⌫ ",fontSize = 18.sp)
            }


        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)


        ) {
            for(i in 9 downTo  7)
                Button(
                    onClick = {
                        getNumber(i.toString())
                    },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)

                ){
                    Text(text = i.toString(),fontSize = 18.sp)

                }
            Button(onClick = {
                getOperator("+")

                },modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            ){
                Text(text="+",color= Color.Red,fontSize = 18.sp)
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)

        ) {
            for(i in 6 downTo  4){
                Button(
                    onClick = {
                        getNumber(i.toString())
                    },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ){
                    Text(text = i.toString(), fontSize = 18.sp) }}
            Button(onClick = {getOperator("-")},                     
                modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ){
                Text(text="-",color= Color.Red, fontSize = 22.sp, fontWeight = FontWeight.Bold )
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)

        ) {

            for(i in 3 downTo  1)
                Button(
                    onClick = {
                        getNumber(i.toString())},                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ){
                    Text(text = i.toString(), fontSize = 18.sp)


                }
            Button(onClick = { getOperator("*")} ,
                modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            )
            {
                Text(text="*",color= Color.Red, fontSize = 18.sp)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)

        ){
            Button(
                onClick = {
                    getNumber(".")

                },modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)

            )
            {
                Text(
                    text=".",
                    fontSize = 18.sp

                    )
            }

            Button(
                onClick = {
                    getNumber("0")
                },modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)

            ){
                Text(text="0", fontSize = 18.sp)
            }

            Button(

                onClick={
                    if(number.value.length>0){

                        val result = number.value
                        val answer = Expression(result).calculate()
                        if(answer!= NaN)
                        number.value = answer.toString()
                        else number.value = "Error"

                    }



                },modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)

            ){
                Text(text="=", fontSize = 18.sp)
            }


            Button(
                onClick = {
                    getOperator("/")
                },modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)

            ){
                Text(text="/", color = Color.Red, fontSize = 22.sp)
            }






        }

    }
}



}

