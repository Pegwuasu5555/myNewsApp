package com.mynewsapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun PasswordInput(
    password: String, // Password string being passed down the UI
    label: String,
    onPasswordChange: (String) -> Unit, // Callback when the password is updated
//    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 10.dp)
            .fillMaxWidth(),
        value = password,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        onValueChange = onPasswordChange,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        shape = RoundedCornerShape(10.dp),
        trailingIcon = {
//            val image = if (passwordVisible)
//
//                Icons.Filled.
//            else
//                Icons.Filled.VisibilityOff

            // Localized description for accessibility services
            val description =   if (passwordVisible)
                "hide password"
            else
                "show password"

            // Toggle button to hide or display password
//            IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                Icon(imageVector  = image, description)
//            }
        },
    )
}