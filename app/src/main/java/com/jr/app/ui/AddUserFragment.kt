package com.jr.app.ui


import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.UploadTask
import com.jr.app.R
import com.jr.app.models.ExampleData
import com.jr.app.network.RestService
import kotlinx.android.synthetic.main.add_user_layout.*
import retrofit2.Call
import retrofit2.Callback
import okhttp3.ResponseBody
import retrofit2.Response
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [AddUserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddUserFragment : Fragment() {

    private var contentView: ViewGroup? = null

    private val RESULT_LOAD_IMG = 1

    private var imageUri: Uri? = null

    private var userId = UUID.randomUUID().toString()

    private lateinit var imgDecodableString : String

    private val onSuccessListener: OnSuccessListener<UploadTask.TaskSnapshot> =
            OnSuccessListener{
                taskSnapShot: UploadTask.TaskSnapshot ->
                Toast.makeText(activity.applicationContext, "image uploaded", Toast.LENGTH_SHORT).show()
            }

    private val onfailureListener: OnFailureListener =
            OnFailureListener{
                Toast.makeText(activity.applicationContext, "image failed to upload", Toast.LENGTH_SHORT).show()
            }

    override fun onPause() {
        super.onPause()
        var imm : InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromInputMethod(activity.currentFocus.windowToken,0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun  showToastMessage(){
        Toast.makeText(activity,"added",Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view: View = inflater.inflate(R.layout.add_user_layout, null)
        contentView = view.findViewById<ViewGroup>(android.R.id.content)
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        add_user_button.setOnClickListener {
            RestService().addNewUserService(getData(), CallBackAdd())
            if(imageUri != null){
                RestService().uploadProfileImage(imgDecodableString, userId,
                        onSuccessListener, onfailureListener)
            }
        }

        picture.setOnClickListener{
            pickImageProfile()
        }

        info_txt.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE){
                RestService().addNewUserService(getData(), CallBackAdd())
            }
            false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK  && data != null){
            imageUri = data.data
            val filePath = arrayOf(MediaStore.Images.Media.DATA)

            val cursor = activity.contentResolver.query(imageUri,filePath,null,null,null)
            if(cursor.moveToFirst()){
                val columnIndex = cursor.getColumnIndex(filePath[0])
                imgDecodableString = cursor.getString(columnIndex)

                val fullSizeImg = BitmapFactory.decodeFile(imgDecodableString)
                val scaledImageBitmap =Bitmap.createScaledBitmap(fullSizeImg, 300,300, true)

                picture.setImageBitmap(scaledImageBitmap)
            }
        }


    }

    private fun getData(): ExampleData {
        var examplePics: MutableList<String> = mutableListOf<String>()
        examplePics.add("EXAMPLEPIC_BASE_64 IT WILL BE")
        examplePics.add("another one!!!")

        return ExampleData(userId, first_name_text.text.toString(),
                second_name_text.text.toString(),
                examplePics,
                info_txt.text.toString())
    }

    private fun pickImageProfile(){
        val galleryIntent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG)

    }

    inner class CallBackAdd : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
//            val snackbar: Snackbar = Snackbar.make(contentView)
            Log.d("jjj", " response is " + response.toString())
            if(response != null && response.code() == 200){
                showToastMessage()
            }else{

            }
        }

        override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

}


