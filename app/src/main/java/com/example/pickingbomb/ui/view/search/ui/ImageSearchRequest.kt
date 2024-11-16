package com.example.pickingbomb.ui.view.search.ui

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pickingbomb.R
import com.example.pickingbomb.network.ImageSearchViewModelFactory
import com.example.pickingbomb.network.Resource
import com.example.pickingbomb.network.RetrofitClient
import com.example.pickingbomb.ui.theme.BrandColor
import com.example.pickingbomb.ui.view.search.holder.HolderFunctions
import com.example.pickingbomb.ui.view.search.holder.Loader
import com.example.pickingbomb.ui.view.search.holder.SharedViewModel
import com.example.pickingbomb.ui.view.search.presentaion.ImageSearchRepository
import com.example.pickingbomb.ui.view.search.presentaion.ImageSearchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody


@Composable
fun ImageSearchRequest(navController: NavController) {

    lateinit var viewModel: ImageSearchViewModel

    val apiService = RetrofitClient.apiService
    val repository = ImageSearchRepository(apiService)
    val factory = ImageSearchViewModelFactory(repository)

    viewModel = ViewModelProvider(
        LocalContext.current as ViewModelStoreOwner,
        factory
    )[ImageSearchViewModel::class.java]


    var showPopup by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var photoURI by remember { mutableStateOf<Uri?>(null) }

    var multipartBody by remember { mutableStateOf<MultipartBody.Part?>(null) }

    // Lens state variables
    var lensSize by remember { mutableFloatStateOf(300f) }
    var lensCenter by remember { mutableStateOf(Offset(300f, 300f)) }

    val context = LocalContext.current


    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                Log.d("Camera", "imageUpdated1")
                imageUri = photoURI
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        multipartBody = HolderFunctions.getMultipartFromUri(photoURI!!, context)
                        Log.i("Success", "Multipart body created")
                    } catch (e: Exception) {
                        Log.i("Error", e.message.toString())
                    }
                }
            }
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                val photoFile = HolderFunctions.createImageFile(context)
                val fileProviderAuthority = "${context.packageName}.fileprovider"
                photoURI = FileProvider.getUriForFile(context, fileProviderAuthority, photoFile)
                cameraLauncher.launch(photoURI!!)
            } else {
                Toast.makeText(context, "Camera permission is required", Toast.LENGTH_SHORT).show()
            }
        }
    )


    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                imageUri = it
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        multipartBody = HolderFunctions.getMultipartFromUri(it, context)
                        Log.i("Success", "Multipart body created")
                    } catch (e: Exception) {
                        Log.i("Error", e.message.toString())
                    }
                }
            }
        }



    LaunchedEffect(multipartBody) {
        multipartBody?.let {

            viewModel.searchImage(it)
            multipartBody=null
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            imageUri?.let {
                AsyncImage(
                    model = it,
                    contentDescription = "Selected image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } ?: Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Default image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Overlay the clear lens effect
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, _ ->
                            // Update lens center and size, ensuring values stay within boundaries
                            lensCenter = Offset(
                                (lensCenter.x + pan.x).coerceIn(0f, size.width.toFloat()),
                                (lensCenter.y + pan.y).coerceIn(0f, size.height.toFloat())
                            )
                            lensSize = (lensSize * zoom).coerceIn(100f, 800f) // Limit the lens size
                        }
                    }
            ) {
                drawClearLens(lensCenter, lensSize)
            }

            Button(
                onClick = { showPopup = true },
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .padding(16.dp)
                    .height(44.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BrandColor)
            ) {
                Text(
                    text = "Upload Photo",
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold
                )
            }

            if (showPopup) {
                UploadOptionsPopup(
                    onDismiss = { showPopup = false },
                    onGalleryClick = { galleryLauncher.launch("image/*") },
                    onCameraClick = {
                        if (ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.CAMERA
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            val photoFile = HolderFunctions.createImageFile(context)
                            val fileProviderAuthority = "${context.packageName}.fileprovider"
                            photoURI = FileProvider.getUriForFile(
                                context,
                                fileProviderAuthority,
                                photoFile
                            )
                            cameraLauncher.launch(photoURI!!)
                        } else {
                            permissionLauncher.launch(Manifest.permission.CAMERA)
                        }
                    }
                )
            }

            val sharedViewModel: SharedViewModel =
                viewModel(LocalContext.current as androidx.activity.ComponentActivity)

            viewModel.imageSearchState.observeAsState().value?.let {
                when (it) {
                    is Resource.Loading -> {
                        Loader()
//                        CircularProgressIndicator()
                    }

                    is Resource.Success -> {
                        Log.d(
                            "success1",
                            "Successfully reached Success block with value: ${it.value}"
                        )

                        sharedViewModel.setImageSearchData(it.value)
                    }

                    is Resource.Failure -> {
                        Log.d("error1", it.errorBody.toString())
                        Log.d("error1", "error")
                    }

                }
            }

            sharedViewModel.imageSearchData.observeAsState().value?.let {
                ImageSearchResult(navController)
//                navController.navigate("result-screen")
            }


        }
    }
}

// Extension function to draw the clear lens effect
fun DrawScope.drawClearLens(lensCenter: Offset, lensSize: Float) {
    val lensRect = Rect(
        offset = Offset(lensCenter.x - lensSize / 2, lensCenter.y - lensSize / 2),
        size = Size(lensSize, lensSize)
    )

    drawRoundRect(
        color = Color.Transparent,
        topLeft = lensRect.topLeft,
        size = lensRect.size,
        cornerRadius = CornerRadius(16f, 16f)
    )

    // Draw the border of the lens
    drawRoundRect(
        color = Color.Gray,
        topLeft = lensRect.topLeft,
        size = lensRect.size,
        cornerRadius = CornerRadius(16f, 16f),
        style = Stroke(width = 2f)
    )
}
