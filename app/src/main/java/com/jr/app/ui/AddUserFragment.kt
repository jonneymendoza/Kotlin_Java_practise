package com.jr.app.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jr.app.R
import com.jr.app.models.ExampleData
import com.jr.app.network.RestService
import kotlinx.android.synthetic.main.add_user_layout.*
import retrofit2.Call
import retrofit2.Callback
import okhttp3.Response


/**
 * A simple [Fragment] subclass.
 * Use the [AddUserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddUserFragment : Fragment() {

    private var contentView: ViewGroup? = null


    inner class CallBack : Callback<Response> {
        override fun onResponse(call: Call<Response>?, response: Response<Response>?) {
//            val snackbar: Snackbar = Snackbar.make(contentView)
            Log.d("jjj", " response is " + response.body.toString)
            if(response.code == 200){
                showToastMessage()
            }else{

            }

        }

        override fun onFailure(call: Call<Response>?, t: Throwable?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
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
            RestService().addNewUserService(getData(), CallBack()) }

    }

    private fun getData(): ExampleData {

        var examplePics: MutableList<String> = mutableListOf<String>()
        examplePics.add("EXAMPLEPIC_BASE_64 IT WILL BE")
        examplePics.add("another one!!!")

        return ExampleData("", first_name_text.text.toString(),
                second_name_text.text.toString(),
                examplePics,
                info_txt.text.toString())
    }
}


