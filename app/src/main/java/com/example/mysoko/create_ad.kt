package com.example.mysoko

import android.app.Activity
import android.app.ProgressDialog
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.OnProgressListener
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

import java.io.IOException

class create_ad : AppCompatActivity() {

    // Folder path for Firebase Storage.
    internal var Storage_Path = "All_Image_Uploads/"

    // Root Database Name for Firebase Database.
    var Database_Path = "All_Image_Uploads_Database"

    // Creating button.
    internal lateinit var ChooseButton: Button
    internal lateinit var UploadButton: Button

    // Creating EditText.
    internal lateinit var service_category: EditText
    internal lateinit var service_name: EditText
    internal lateinit var service_description: EditText

    // Creating ImageView.
    internal lateinit var SelectImage: ImageView

    // Creating URI.
    internal var FilePathUri: Uri? = null

    // Creating StorageReference and DatabaseReference object.
    internal lateinit var storageReference: StorageReference
    internal lateinit var databaseReference: DatabaseReference

    // Image request code for onActivityResult() .
    internal var Image_Request_Code = 7

    internal lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_ad)

        // Assign FirebaseStorage instance to storageReference.
        storageReference = FirebaseStorage.getInstance().reference

        // Assign FirebaseDatabase instance with root database name.
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path)

        //Assign ID'S to button.
        ChooseButton = findViewById<View>(R.id.selectImage) as Button
        UploadButton = findViewById<View>(R.id.create_ad) as Button

        // Assign ID's to EditText.
        service_name = findViewById<View>(R.id.service_name) as EditText
        service_category = findViewById<View>(R.id.service_category) as EditText
        service_description = findViewById<View>(R.id.service_description) as EditText

        // Assign ID'S to image view.
        SelectImage = findViewById<View>(R.id.ShowImageView) as ImageView

        // Assigning Id to ProgressDialog.
        progressDialog = ProgressDialog(this@create_ad)

        // Adding click listener to Choose image button.
        ChooseButton.setOnClickListener {
            // Creating intent.
            val intent = Intent()

            // Setting intent type as image to select image from phone storage.
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Please Select Image"),
                Image_Request_Code
            )
        }


        // Adding click listener to Upload image button.
        UploadButton.setOnClickListener {
            // Calling method to upload selected image on Firebase storage.
            UploadImageFileToFirebaseStorage()
        }

        val bottomNavigationView = findViewById<View>(R.id.nav_view) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_services -> {
                    val nav_servies = Intent(this@create_ad, search_service::class.java)
                    startActivity(nav_servies)
                }

                R.id.navigation_home -> {
                    val nav_create = Intent(this@create_ad, MainActivity::class.java)
                    startActivity(nav_create)
                }
            }
            false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Image_Request_Code && resultCode == Activity.RESULT_OK && data != null && data.data != null) {

            FilePathUri = data.data

            try {

                // Getting selected image into Bitmap.
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, FilePathUri)

                // Setting up bitmap selected image into ImageView.
                SelectImage.setImageBitmap(bitmap)

                // After selecting image change choose button above text.
                ChooseButton.text = "Image Selected"

            } catch (e: IOException) {

                e.printStackTrace()
            }

        }
    }

    // Creating Method to get the selected image file Extension from File Path URI.
    fun GetFileExtension(uri: Uri): String? {

        val contentResolver = contentResolver

        val mimeTypeMap = MimeTypeMap.getSingleton()

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri))

    }

    // Creating UploadImageFileToFirebaseStorage method to upload image on storage.
    fun UploadImageFileToFirebaseStorage() {

        // Checking whether FilePathUri Is empty or not.
        if (FilePathUri != null) {

            // Setting progressDialog Title.
            progressDialog.setTitle("Image is Uploading...")

            // Showing progressDialog.
            progressDialog.show()

            // Creating second StorageReference.
            val storageReference2nd = storageReference.child(
                Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(FilePathUri!!)
            )

            // Adding addOnSuccessListener to second StorageReference.
            storageReference2nd.putFile(FilePathUri!!)
                .addOnSuccessListener { taskSnapshot ->
                    // Getting image name from EditText and store into string variable.
                    val Tempservice_name = service_name.text.toString().trim { it <= ' ' }
                    val Tempservice_category = service_category.text.toString().trim { it <= ' ' }
                    val Tempservice_description =
                        service_description.text.toString().trim { it <= ' ' }

                    // Hiding the progressDialog after done uploading.
                    progressDialog.dismiss()

                    // Showing toast message after done uploading.
                    Toast.makeText(
                        applicationContext,
                        "Image Uploaded Successfully ",
                        Toast.LENGTH_LONG
                    ).show()

                    val imageUploadInfo = ImageUploadInfo(
                        Tempservice_name,
                        Tempservice_category,
                        Tempservice_description,
                        taskSnapshot.metadata!!.reference!!.downloadUrl.toString()
                    )

                    // Getting image upload ID.
                    val ImageUploadId = databaseReference.push().key

                    // Adding image upload id s child element into databaseReference.
                    databaseReference.child(ImageUploadId!!).setValue(imageUploadInfo)
                }
                // If something goes wrong .
                .addOnFailureListener { exception ->
                    // Hiding the progressDialog.
                    progressDialog.dismiss()

                    // Showing exception erro message.
                    Toast.makeText(this@create_ad, exception.message, Toast.LENGTH_LONG).show()
                }

                // On progress change upload time.
                .addOnProgressListener {
                    // Setting progressDialog Title.
                    progressDialog.setTitle("Image is Uploading...")
                }
        } else {

            Toast.makeText(
                this@create_ad,
                "Please Select Image or Add Image Name",
                Toast.LENGTH_LONG
            ).show()

        }
    }


}

