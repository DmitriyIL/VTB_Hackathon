package xyz.tusion.vtb_hackathon.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import io.reactivex.Observable
import xyz.tusion.vtb_hackathon.R
import java.util.*

object QRCodeGenerator {
    fun generateFromString(text: String, size: Int): Observable<Bitmap> {
        try {
            val hints = Hashtable<EncodeHintType, String>()
            hints[EncodeHintType.CHARACTER_SET] = "UTF-8"
            val bitMatrix = MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, size, size, hints)
            val bitMatrixWidth = bitMatrix.width
            val bitMatrixHeight = bitMatrix.height
            val pixels = IntArray(bitMatrixHeight * bitMatrixWidth)

            var y = 0
            while (y < bitMatrixHeight) {
                val offset = y * bitMatrixWidth
                var x = 0
                while (x < bitMatrixWidth) {
                    pixels[offset + x] = if (bitMatrix.get(x, y)) Color.BLACK
                    else Color.WHITE
                    x++
                }
                y++
            }
            val bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.RGB_565)
            bitmap.setPixels(pixels, 0, size, 0, 0, bitMatrixWidth, bitMatrixHeight)
            return Observable.just(bitmap)
        } catch (e: Exception) {
            return Observable.empty()
        }
    }
}

fun getNormalProfileQrCodeSize(context: Context): Int =
    context.resources.getDimensionPixelOffset(
        R.dimen.profile_screen_qr_size
    )
