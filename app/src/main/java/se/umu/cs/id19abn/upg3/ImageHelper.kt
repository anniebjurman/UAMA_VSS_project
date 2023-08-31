package se.umu.cs.id19abn.upg3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.util.Log
import android.widget.ImageView
import androidx.exifinterface.media.ExifInterface
import java.io.IOException


class ImageHelper {
    @Throws(IOException::class)
    fun rotateImage(path: String): Bitmap? {
        Log.d("ORIENTATIONNN", "rotate img fun")
        val bitmap = BitmapFactory.decodeFile(path)
        return rotateImage(bitmap, path)
    }

    @Throws(IOException::class)
    fun rotateImage(bitmap: Bitmap, path: String): Bitmap? {
        var rotate = 0
        val exif: ExifInterface = ExifInterface(path)
        val orientation = exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        )
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_270 -> rotate = 270
            ExifInterface.ORIENTATION_ROTATE_180 -> rotate = 180
            ExifInterface.ORIENTATION_ROTATE_90 -> rotate = 90
        }
        val matrix = Matrix()
        matrix.postRotate(rotate.toFloat())
        return Bitmap.createBitmap(
            bitmap, 0, 0, bitmap.width,
            bitmap.height, matrix, true
        )
    }

    fun scaleBitmap(bitmap: Bitmap, imageview: ImageView): Bitmap {
        val currentBitmapWidth: Double = bitmap.width.toDouble()
        val currentBitmapHeight: Double = bitmap.height.toDouble()
        Log.d("SCALE: BM width", currentBitmapWidth.toString())
        Log.d("SCALE: BM height", currentBitmapHeight.toString())

//        val ivWidth: Double = imageview.measuredWidth.toDouble()
//        val ivHeight: Double = imageview.measuredHeight.toDouble()
        val ivWidth: Double = 220.0
        val ivHeight: Double = 220.0
        Log.d("SCALE: IV width", ivWidth.toString())
        Log.d("SCALE: IV height", ivHeight.toString())

        val newWidth: Double = ivWidth
        val newHeight: Double = currentBitmapHeight * (newWidth / currentBitmapWidth)
        Log.d("SCALE: new width", newWidth.toString())
        Log.d("SCALE: new height", newHeight.toString())

        val newBitmap: Bitmap = Bitmap.createScaledBitmap(bitmap, newWidth.toInt(), newHeight.toInt(), true);
        return newBitmap
    }

}