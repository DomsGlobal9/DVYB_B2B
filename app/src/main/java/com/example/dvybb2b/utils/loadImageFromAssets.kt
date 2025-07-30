package com.example.dvybb2b.utils


import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.IOException

@Composable
fun loadImageFromAssets(context: Context, assetPath: String): ImageBitmap? {
    var imageBitmap by remember(assetPath) {
        mutableStateOf<ImageBitmap?>(null)
    }

    LaunchedEffect(assetPath) {
        try {
            val inputStream = context.assets.open(assetPath)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
            imageBitmap = bitmap.asImageBitmap()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    return imageBitmap
}
