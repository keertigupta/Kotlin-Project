package com.example.credrdummy

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.database.Cursor.FIELD_TYPE_STRING
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.media.ExifInterface
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat.requestPermissions
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.widget.TextView
import android.widget.Toast
import org.apache.commons.lang3.text.WordUtils
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Shivam Jaiswal on 22/03/18.
 */
class Utils {

    companion object {

        private var pDialog: ProgressDialog? = null
        private lateinit var toast: Toast

        /*    fun showDialog(context: Activity, msg: String, title: String) {
                if (pDialog == null) {
                    pDialog = ProgressDialog(context)
                    pDialog?.setMessage(msg)
                    pDialog?.setTitle(title)
                    pDialog?.setCancelable(false)
                    if (pDialog?.isShowing == false && !(context as AppCompatActivity).isFinishing) {
                        pDialog?.show()
                    }
                }
            }
    */
        fun showDialog(context: Context, msg: String, title: String) {
            if (pDialog == null) {
                pDialog = ProgressDialog(context)
                pDialog?.setMessage(msg)
                pDialog?.setTitle(title)
                pDialog?.setCancelable(false)
                if (pDialog?.isShowing == false && !(context as AppCompatActivity).isFinishing) {
                    pDialog?.show()
                }
            }
        }

        fun showDialog(context: Context, msg: String) {
            if (pDialog == null) {
                pDialog = ProgressDialog(context)
                pDialog?.setMessage(msg)
                pDialog?.setTitle("")
                pDialog?.setCancelable(false)
                if (pDialog?.isShowing == false && !(context as AppCompatActivity).isFinishing) {
                    pDialog?.show()
                }
            }
        }

        fun hideDialog() {
            if (pDialog != null) {
                if (pDialog?.isShowing == true) {
                    pDialog?.dismiss()
                    pDialog = null
                }
            }
        }


        fun retriveVideoFrameFromVideo(videoPath: String?): Bitmap? {
            var bitmap: Bitmap? = null
            var mediaMetadataRetriever: MediaMetadataRetriever? = null
            try {
                mediaMetadataRetriever = MediaMetadataRetriever()
//                if (Build.VERSION.SDK_INT >= 14)
                mediaMetadataRetriever.setDataSource(videoPath, HashMap<String, String>())
//                else
//                    mediaMetadataRetriever.setDataSource(videoPath);

                bitmap = mediaMetadataRetriever.getFrameAtTime(2000)
            } catch (e: Exception) {
                e.printStackTrace()
                throw Throwable("Exception in retriveVideoFrameFromVideo(String videoPath)" + e.message)

            } finally {
                mediaMetadataRetriever?.release()
            }
            return if (bitmap != null)
                getResizedBitmap(bitmap)
            else null
        }


        fun toast(message: String, context: Context?) {

            val v = LayoutInflater.from(context).inflate(R.layout.layout_custom_toastview, null)

            val textView = v.findViewById<TextView>(R.id.tvToast)
            textView.text = message

            toast = Toast(context)
            toast.view = v
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.duration = Toast.LENGTH_LONG
            toast.show()
        }


        /**
         * @param actualImage  this is actual image file, which is to be compressed
         * compresses image and return compressed image file
         */

        private fun getRandomColor(): Int {
            val rand = Random()
            return Color.argb(100, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256))
        }

        fun getReadableFileSize(size: Long): String {
            if (size <= 0) {
                return "0"
            }
            val units = arrayOf("B", "KB", "MB", "GB", "TB")
            val digitGroups = (Math.log10(size.toDouble()) / Math.log10(1024.0)).toInt()
            return DecimalFormat("#,##0.#").format(
                size / Math.pow(
                    1024.0,
                    digitGroups.toDouble()
                )
            ) + " " + units[digitGroups]
        }

        fun arrangeStringCases(input: String?): String {
            if (TextUtils.isEmpty(input))
                return "User"
            else
                return WordUtils.capitalize(input?.toLowerCase())
        }


        fun isValidYear(year: Int): Boolean {
            if (year.toString().length < 4)
                return false

            val calendar = Calendar.getInstance()
            val thisYear = calendar.get(Calendar.YEAR)

            if (year > thisYear)
                return false

            return true
        }

        fun getFormattedDate(input: String?, toFormat: String?): String {
//            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            if (!TextUtils.isEmpty(input)) {
                val format =
                    SimpleDateFormat(if (toFormat.isNullOrEmpty()) "yyyy-MM-dd" else toFormat)
                val date = format.parse(input)

                return format.format(date)
            } else {
                return ""
            }
        }

        fun getFormattedDate(input: String?): String {
//            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            if (!TextUtils.isEmpty(input)) {
                val format = SimpleDateFormat("yyyy-MM-dd hh:mm")
                val date = format.parse(input)

                return format.format(date)
            } else {
                return ""
            }
        }


        fun isPermissionGranted(context: Context, permission: String): Boolean {
            return context.packageManager.checkPermission(
                permission,
                context.packageName
            ) == PackageManager.PERMISSION_GRANTED
        }

        fun createImageFile(): File {
            val storageDir = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "CredR-Images"
            )
            if (!storageDir.exists()) {
                if (!storageDir.mkdirs())
                    Log.d("Directory Creation Err", "Failed Failed")
            }
            return File(storageDir.absolutePath + "/Image-" + System.currentTimeMillis() + ".jpg")
        }

        fun getPathFromUri(contentURI: Uri, context: Context): String {
            var cursor: Cursor? = null
            try {
                val proj = arrayOf(MediaStore.Images.Media.DATA)
                cursor = context.contentResolver.query(contentURI, proj, null, null, null)
                val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                cursor.moveToFirst()
                if (cursor.getType(column_index) == FIELD_TYPE_STRING) {
                    return cursor.getString(column_index);
                }
                return cursor.getString(column_index)
            } finally {
                if (cursor != null) {
                    cursor.close()
                }
            }
        }

        fun getResizedBitmap(bitmap: Bitmap): Bitmap? {
            val maxSize = 250
            var output: Bitmap? = null
            val outWidth: Int
            val outHeight: Int
            val inWidth = bitmap.width
            val inHeight = bitmap.height

            if (inWidth > inHeight) {
                outWidth = maxSize
                outHeight = inHeight * maxSize / inWidth

            } else {
                outHeight = maxSize
                outWidth = inWidth * maxSize / inHeight

            }

            output = Bitmap.createScaledBitmap(bitmap, outWidth, outHeight, false)
            return output
        }


        fun scaleAnimation(v: View, startAnimation: Boolean) {
            if (startAnimation) {
                val anim = ScaleAnimation(
                    1f, 1.1f, // Start and end values for the X axis scaling
                    1f, 1.1f, // Start and end values for the Y axis scaling
                    Animation.RELATIVE_TO_SELF, .5f, // Pivot point of X scaling
                    Animation.RELATIVE_TO_SELF, .5f
                ) // Pivot point of Y scaling
//                anim.fillAfter = true // Needed to keep the result of the animation
//                anim.fillBefore = true
                anim.duration = 500
                anim.repeatMode = Animation.REVERSE
                anim.repeatCount = Animation.INFINITE
                v.startAnimation(anim)

            } else {
                v.clearAnimation()
            }

        }

        /*this method used to convert file to Bitmap*/
        fun decodeFile(f: File): Bitmap? {
            try {
                // Decode image size

                val o = BitmapFactory.Options()
                o.inJustDecodeBounds = true
                BitmapFactory.decodeStream(FileInputStream(f), null, o)

                // The new size we want to scale to
                val REQUIRED_SIZE = 100

                // Find the correct scale value. It should be the power of 2.
                var scale = 1
                while (o.outWidth / 4 / scale >= REQUIRED_SIZE && o.outHeight / 4 / scale >= REQUIRED_SIZE) {
                    scale *= 2
                }

                // Decode with inSampleSize
                val o2 = BitmapFactory.Options()
                o2.inSampleSize = scale
                return BitmapFactory.decodeStream(FileInputStream(f), null, o2)
            } catch (e: FileNotFoundException) {
            }

            return null
        }

        /**used specifically for SAMSUNG devices to fix the rotation issue*/

        fun rotateImage(filePath: String): Bitmap? {
            var bitmap: Bitmap? = null
            var rotate = 0
            try {
                val exif = ExifInterface(filePath)
                val orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL
                )

                when (orientation) {
                    ExifInterface.ORIENTATION_ROTATE_270 -> rotate = 270
                    ExifInterface.ORIENTATION_ROTATE_180 -> rotate = 180
                    ExifInterface.ORIENTATION_ROTATE_90 -> rotate = 90
                }
                val rotatedBitmap = decodeFile(File(filePath))
                val matrix = Matrix()
                matrix.postRotate(rotate.toFloat())
                bitmap = Bitmap.createBitmap(
                    rotatedBitmap!!,
                    0,
                    0,
                    rotatedBitmap.width,
                    rotatedBitmap.height,
                    matrix,
                    true
                )

            } catch (e: Exception) {
                e.printStackTrace()
            }

            return bitmap
        }

        fun viewBlinkAnimation(view: View, repeatCount: Int, duration: Long, context: Context) {
            var anim = ObjectAnimator.ofInt(
                view, "backgroundColor", Color.WHITE
                , context.resources.getColor(R.color.light_red), Color.WHITE
            )
            anim?.setEvaluator(ArgbEvaluator())
            anim?.duration = duration
            anim?.repeatMode = ValueAnimator.REVERSE
            anim?.repeatCount = repeatCount
            anim?.start()
        }


        fun rotateView(view: View, fromDegrees: Float, toDegrees: Float) {
            val rotate = RotateAnimation(
                fromDegrees,
                toDegrees,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )

            rotate.duration = 400
            rotate.interpolator = LinearInterpolator()
            rotate.fillAfter = true
            view.startAnimation(rotate)

        }


        /**
         * Used to select back date only MAX Date will be today
         */
        fun datePicker(
            mContext: Context,
            listener: DatePickerDialog.OnDateSetListener
        ): DatePickerDialog {
            val c = Calendar.getInstance()
            val dialog = DatePickerDialog(
                mContext,
                R.style.myDialogTheme,
                listener,
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            )
            dialog.datePicker.maxDate = System.currentTimeMillis()
            return dialog
        }

        /**
         * Used to select back date only MAX Date will be today
         */
        fun datePicker(
            mContext: Context,
            enableMaxDate: Boolean,
            listener: DatePickerDialog.OnDateSetListener
        ): DatePickerDialog {
            val c = Calendar.getInstance()
            val dialog = DatePickerDialog(
                mContext,
                R.style.myDialogTheme,
                listener,
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            )
            if (enableMaxDate)
                dialog.datePicker.maxDate = System.currentTimeMillis()
            return dialog
        }


        /**
         * Open Camera to click photo,
         * this single method will be called for different requestCode
         * USED SPECIFICALLY FOR ACTIVITIES
         * @param requestCode
         */
        fun openCamera(requestCode: Int, context: Context): String {
            var imagePathUrl = ""
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (isPermissionGranted(context, android.Manifest.permission.CAMERA)
                    && isPermissionGranted(
                        context,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                ) {


                    if (takePictureIntent.resolveActivity(context.packageManager) != null) {
                        var photoFile: File? = null
                        try {
                            photoFile = Utils.createImageFile()
                        } catch (ex: IOException) {
                            ex.printStackTrace()
                        }
                        //for Android 7.0 and above get URI using provider
                        val fileUri = FileProvider.getUriForFile(
                            context,
                            context.applicationContext
                                .packageName + ".provider", photoFile!!
                        )

                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
                        imagePathUrl = photoFile.absolutePath!!
                        (context as AppCompatActivity).startActivityForResult(
                            takePictureIntent,
                            requestCode
                        )
                    }

                } else {
                    //todo: request permission
                    requestPermissions(
                        (context as AppCompatActivity), arrayOf(
                            android.Manifest.permission.CAMERA
                            , android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE
                        ), requestCode
                    )
                }
            }
            return imagePathUrl
        }

        /**
         * Open Camera to click photo,
         * this single method will be called for different requestCode
         * USED SPECIFICALLY FOR FRAGMENT
         * @param requestCode
         */
        fun openCamera(requestCode: Int, context: Fragment): String {
            var imagePathUrl = ""
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (isPermissionGranted(context.context!!, android.Manifest.permission.CAMERA)
                    && isPermissionGranted(
                        context.context!!,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                ) {

                    if (takePictureIntent.resolveActivity(context.context!!.packageManager) != null) {
                        var photoFile: File? = null
                        try {
                            photoFile = createImageFile()
                        } catch (ex: IOException) {
                            ex.printStackTrace()
                        }
                        //for Android 7.0 and above get URI using provider
                        val fileUri = FileProvider.getUriForFile(
                            context.context!!,
                            context.context!!.applicationContext
                                .packageName + ".provider", photoFile!!
                        )

                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
                        imagePathUrl = photoFile.absolutePath!!
                        context.startActivityForResult(takePictureIntent, requestCode)

                    }

                } else {
                    //todo: request permission
                    context.requestPermissions(
                        arrayOf(
                            android.Manifest.permission.CAMERA
                            , android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE
                        ), requestCode
                    )
                }
            }

            return imagePathUrl
        }


        /**
         * This will pick image from gallery for various options based on request code
         * @param requestCode
         */
        fun pickImageFromGallery(requestCode: Int, context: Fragment) {
            if (Utils.isPermissionGranted(
                    context.context!!,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )

                if (intent.resolveActivity(context.context!!.packageManager) != null) {
                    (context).startActivityForResult(intent, requestCode)
                }
            } else {
                context.requestPermissions(
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    requestCode
                )
            }
        }

        /**
         * This will pick image from gallery for various options based on request code
         * Used for Activities
         * @param requestCode
         */
        fun pickImageFromGallery(requestCode: Int, context: Context) {
            if (Utils.isPermissionGranted(
                    context,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )

                if (intent.resolveActivity(context.packageManager) != null) {
                    (context as AppCompatActivity).startActivityForResult(intent, requestCode)
                }
            } else {
                requestPermissions(
                    (context as AppCompatActivity),
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    requestCode
                )
            }
        }


        fun openDialer(context: Context, number: String?) {
            if (!TextUtils.isEmpty(number)) {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:$number")
                context.startActivity(intent)
            } else {
                toast("number can't be empty", context)
            }
        }

        @Throws(NoSuchAlgorithmException::class)
        fun sha256(msg: String): String {
            val md = MessageDigest.getInstance("SHA-256")
            md.update(msg.toByteArray())

            return bytesToHex(md.digest())
        }

        fun bytesToHex(bytes: ByteArray): String {
            val builder = StringBuilder()
            for (b in bytes) {
                builder.append(String.format("%02x", b))
            }
            return builder.toString()
        }


    }

    interface SelectYear {
        fun onYearSelect(year: Int)
    }

}