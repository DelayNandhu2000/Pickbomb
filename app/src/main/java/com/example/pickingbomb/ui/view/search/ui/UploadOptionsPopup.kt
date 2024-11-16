package com.example.pickingbomb.ui.view.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.pickingbomb.R
import com.example.pickingbomb.ui.theme.BrandColor

@Composable
fun UploadOptionsPopup(
    onDismiss: () -> Unit,
    onGalleryClick: () -> Unit,
    onCameraClick: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = CenterHorizontally
            ) {

                Text(
                    text = "Search with a photo",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black, // Change this to your preferred color
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                )

                Text(
                    text = "Upload a photo and search for Fashion",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Black, // Change this to your preferred color
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )

                Button(
                    onClick = {
                        onGalleryClick()
                        onDismiss()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrandColor
                    ),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Row (verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(),){

                        Text(text = "Choose from Gallery", fontSize = 12.sp, fontWeight = FontWeight.Normal)
                        Spacer(Modifier.weight(1f))
                        Icon(
                            Icons.Filled.ArrowForward,
                            contentDescription = "Arrow Icon",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Button(
                    onClick = {
                        onCameraClick()
                        onDismiss()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(0.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BrandColor)

                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(text = "Click a photo", fontSize = 12.sp, color = BrandColor, fontWeight = FontWeight.Normal)
                        Spacer(Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Filled.ArrowForward,
                            contentDescription = "Arrow Icon",
                            tint = BrandColor,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                }
            }
        }
    }
}
