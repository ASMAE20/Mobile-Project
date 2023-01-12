package com.example.nextprojet

import android.content.DialogInterface
import com.example.nextprojet.folder_adapter.Adapter
import com.example.nextprojet.repository.Poste_Repository
import com.example.nextprojet.tools.Global.Companion.debut_page
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nextprojet.tools.Global.Companion.nbr_poste
import com.google.android.material.snackbar.Snackbar


import kotlinx.android.synthetic.main.activity_main.*


open class MainActivity : AppCompatActivity() {

    var loading = false
    var call = 1
    lateinit var layoutManager: LinearLayoutManager
    private lateinit var viewModel: MainViewModel
    private val adapter by lazy { Adapter() }






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button_background : Int = 1;
        button2.setOnClickListener {
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://dummyapi.io/")
            startActivity(openURL)

            if(button_background==2){
                button2.setBackgroundResource(R.drawable.btn_center_gradient);
                button_background=1;
            } else if(button_background==1){
                button2.setBackgroundResource(R.drawable.btn_oval_gradient);
                button_background=2;
            }


        }

        switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

        }

        setupRecyclerview()


        // when button is clicked, show the alert
        button3.setOnClickListener {
            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(this)

            // set message of alert dialog
            dialogBuilder.setMessage("Do you want realy to close this application ?")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("Proceed", DialogInterface.OnClickListener {
                        dialog, id -> finish()
                })
                // negative button text and action
                .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("Alert")
            // show alert dialog
            alert.show()
        }

        PostButton.setOnClickListener {
            val intent = Intent(this, CréerPoste::class.java)
            this.startActivity(intent)
            PostButton.animate().apply{
                duration=2000
                rotationXBy(360f)

            }.start()

            val snack = Snackbar.make(it,"This is a simple Creat Poste",Snackbar.LENGTH_SHORT)
            snack.show()

            if(button_background==2){
                PostButton.setBackgroundResource(R.drawable.btn_center_gradient);
                button_background=1;
            } else if(button_background==1){
                PostButton.setBackgroundResource(R.drawable.btn_oval_gradient);
                button_background=2;
            }



        }




        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        forward(debut_page,nbr_poste, 0)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    if(!loading) {
                        // forward after reaching the button
                        forward(debut_page,nbr_poste*(call+1),3000)
                        // adding one callout
                        call++
                    }
                }
            }
        })

    }


    // forward ini
    private fun forward(pageNumber: Int, postsNumber: Int, delay: Long) {
        // ini repository.Repository
        loading = true
        val repository = Poste_Repository()

        // ini viewModelFactory
        val viewModelFactory = MainViewModelFactory(repository)

        // forwading
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.afficherPosts(pageNumber, postsNumber)
        viewModel.responseData.observe(this, Observer { response ->
            if(response.isSuccessful){
                // delay to stop spam
                Handler().postDelayed({
                    loading = false
                }, delay)
                adapter.updatedata(this,R.layout.design_layout, response.body()?.data!!)
            }else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })

    }

    //setting up recyclerview
    private fun setupRecyclerview() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }


}











































