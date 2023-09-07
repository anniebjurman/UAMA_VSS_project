package se.umu.cs.id19abn.upg3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.exifinterface.media.ExifInterface
import java.io.IOException

/**
 * A class containing helper functions for
 * displaying av image.
 */
class ImageHelper {
    @Throws(IOException::class)
    fun rotateImage(path: String): Bitmap? {
        // Decode the image file located at 'path' into a Bitmap
        val bitmap = BitmapFactory.decodeFile(path)

        // Call the overloaded 'rotateImage' function to perform rotation based on image's EXIF data
        return rotateImage(bitmap, path)
    }

    @Throws(IOException::class)
    fun rotateImage(bitmap: Bitmap, path: String): Bitmap? {
        var rotate = 0

        // Create an ExifInterface instance to read EXIF data from the image file at 'path'
        val exif: ExifInterface = ExifInterface(path)

        // Get the orientation information from the EXIF data
        val orientation = exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        )

        // Determine the rotation angle based on the orientation information
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_270 -> rotate = 270
            ExifInterface.ORIENTATION_ROTATE_180 -> rotate = 180
            ExifInterface.ORIENTATION_ROTATE_90 -> rotate = 90
        }

        // Create a matrix for image rotation
        val matrix = Matrix()
        matrix.postRotate(rotate.toFloat())

        // Rotate the input Bitmap based on the calculated angle
        return Bitmap.createBitmap(
            bitmap, 0, 0, bitmap.width,
            bitmap.height, matrix, true
        )
    }

}