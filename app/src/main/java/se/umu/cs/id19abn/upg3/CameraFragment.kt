package se.umu.cs.id19abn.upg3

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import se.umu.cs.id19abn.upg3.databinding.FragmentCameraBinding
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


/**
 * A Fragment that contains actions for opening the device's camera,
 * take a photo and save the photo on the device.
 */
class CameraFragment : Fragment() {
    private lateinit var binding: FragmentCameraBinding
    private lateinit var session: Session
    private var imageCapture: ImageCapture? = null
    private lateinit var cameraExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCameraBinding.inflate(layoutInflater)

        session = arguments?.let { CameraFragmentArgs.fromBundle(it).session }!!

        // Request camera permissions
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            requestPermissions()
        }

        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using the provided inflater
        binding = FragmentCameraBinding.inflate(inflater)
        // Set a click listener for the capture button
        binding.imgCaptureButton.setOnClickListener {
            // Call the takePhoto() function when the capture button is clicked
            takePhoto()
        }
        // Return the root view of the inflated layout
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        // shutdown camera on destroy fragment
        cameraExecutor.shutdown()
    }

    override fun onResume() {
        super.onResume()
        // toggle the visibility of the actionBar
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        // toggle the visibility of the actionBar
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }

    private fun takePhoto() {
        // Get a stable reference of the modifiable image capture use case
        val imageCapture = imageCapture ?: return

        // Create time stamped name and MediaStore entry.
        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
            .format(System.currentTimeMillis())
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Vad-Sager-Systemet")
            }
        }

        // Create output options object which contains file + metadata
        val outputOptions = activity?.applicationContext?.let {
            ImageCapture.OutputFileOptions
                .Builder(
                    it.contentResolver,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    contentValues)
                .build()
        }

        // Set up image capture listener, which is triggered after photo has
        // been taken
        if (outputOptions != null) {
            // Use the ImageCapture instance (imageCapture) to capture a photo
            // with the provided output options
            imageCapture.takePicture(
                outputOptions,
                ContextCompat.getMainExecutor(requireContext()),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onError(exc: ImageCaptureException) {
                        // Handle any errors that occur during photo capture
                        Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                    }

                    override fun onImageSaved(output: ImageCapture.OutputFileResults){
                        // When the image is successfully saved, update the image path in beerGameObj
                        val imagePath = "/storage/emulated/0/Pictures/Vad-Sager-Systemet/$name.jpg"
                        session.currentGame?.imgPath = imagePath

                        // Create a navigation action to go to the BeerNameFragment with the updated beerGameObj
                        val action = CameraFragmentDirections.actionCameraFragmentToBeerNameFragment(session)
                        // Navigate to the BeerNameFragment
                        binding.root.findNavController().navigate(action)
                    }
                }
            )
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireActivity())

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()
            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture)

            } catch(exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(requireActivity()))
    }

    private fun requestPermissions() {
        // Launch the activity result launcher to request the required permissions
        activityResultLauncher.launch(REQUIRED_PERMISSIONS)
    }

    // Check if all the required permissions are granted
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        // Check if the specified permission is granted using ContextCompat
        ContextCompat.checkSelfPermission(
            requireActivity().baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private val activityResultLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions())
        { permissions ->
            // Handle Permission granted/rejected
            var permissionGranted = true
            permissions.entries.forEach {
                if (it.key in REQUIRED_PERMISSIONS && !it.value)
                    permissionGranted = false
            }
            if (!permissionGranted) {
                Toast.makeText(requireActivity().baseContext,
                    "Permission request denied",
                    Toast.LENGTH_SHORT).show()
            } else {
                startCamera()
            }
        }

    companion object {
        const val TAG = "CameraXApp"
        // Format for generating unique file names for captured images
        const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        // List of required permissions for the app, initially containing the CAMERA permission
        val REQUIRED_PERMISSIONS =
            mutableListOf (
                Manifest.permission.CAMERA
            ).apply {
                // If the device's Android version is less than or equal to P (API 28),
                // add WRITE_EXTERNAL_STORAGE permission
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }.toTypedArray()
    }
}