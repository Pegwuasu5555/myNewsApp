package com.mynewsapp.ui

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mynewsapp.navigation.Screens
import com.mynewsapp.ui.components.PasswordInput
import com.mynewsapp.ui.components.TextInput
import com.mynewsapp.viewmodels.AuthViewModel
import com.mynewsapp.viewmodels.NewsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Register(navController: NavHostController, viewModel: NewsViewModel){
    val authViewModel: AuthViewModel = viewModel()

    val context = LocalContext.current
    fun onRegister() {
        val textSuccess = "Registration is successful"
        val duration = Toast.LENGTH_SHORT
        val successToast = Toast.makeText(context, textSuccess, duration) // in Activity
//        val failedToast = Toast.makeText(context, textFailed, duration) // in Activity
        authViewModel.signUp(onSuccess = {
            successToast.show()
            navController.navigate(Screens.AllNewsScreen.route)
        }, onFailed = { it ->
            val failedToast = Toast.makeText(context, it, duration)
            failedToast.show()
        })
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(0.dp, 0.dp)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp, 0.dp)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 25.dp)
            )
            {
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 30.dp),
                    text = "Awesome news App",
                    fontSize = 22.sp, fontWeight = FontWeight.Bold,
                )
                Text(text = "Create an account", fontSize = 26.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp))
                Text(text = "Create an account to see some awesome technology news", fontSize = 17.sp, modifier = Modifier.padding(0.dp, 0.dp))
            }
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Column(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 20.dp)) {
                    TextInput(value = authViewModel.email, label = "Email", onValueChange = { authViewModel.email = it })
                }
                Column(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 20.dp)) {
                    PasswordInput(password = authViewModel.password, label = "Password", onPasswordChange = { authViewModel.password = it })
                }

                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = { onRegister() }, modifier = Modifier.fillMaxWidth(), enabled = !authViewModel.loading) {
                    Text(text = "Register", fontSize = 17.sp)
                }

                Row(modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 30.dp)) {
                    Text(
                        modifier = Modifier.padding(10.dp, 0.dp), fontWeight = FontWeight.SemiBold,
                        text = "Already have an account ?", fontSize = 16.sp)
                    ClickableText(
                        style = TextStyle(color = Color.Blue, fontSize = 18.sp),
                        text = AnnotatedString("Login"),

                        onClick = { offset ->
                            Log.d("ClickableText", "$offset -th character is clicked.")
                            navController.navigate(Screens.LoginScreen.route)
                        }
                    )
                }
            }
        }
    }
}