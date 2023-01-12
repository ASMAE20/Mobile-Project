package com.example.nextprojet


import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nextprojet.folder_adapter.Adapter
import com.example.nextprojet.repository.Poste_Repository
import com.example.nextprojet.tools.Global.Companion.nbr_poste
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.activity_tag.*

// affichage des postes en utilisants les tags

class PosteBytag : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    var loading = false
    var call = 1
    private val adapter by lazy { Adapter() }
    lateinit var layoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag)
        Intent
    }

    private val Intent: Unit
        private get() {

            val button: Button = findViewById<View>(R.id.button) as Button
            var button_background=1;
            button.setOnClickListener {
                if(button_background==2){
                    button.setBackgroundResource(R.drawable.btn_center_gradient);
                    button_background=1;
                } else if(button_background==1){
                    button.setBackgroundResource(R.drawable.btn_oval_gradient);
                    button_background=2;
                }
            }
            if (intent.hasExtra("tag")) {

                val tag = intent.getStringExtra("tag")
                button.setText(tag)


                updaterecycleview()

                layoutManager = LinearLayoutManager(this)
                update.layoutManager = layoutManager
                if (tag != null) {
                  //scroll des tags
                    before(tag, nbr_poste, 0)
                    update.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                            super.onScrollStateChanged(recyclerView, newState)
                            if (!recyclerView.canScrollVertically(1)) {
                                if(!loading) {
                                    before(tag,nbr_poste*(call+1),2500)
                                    call++
                                }
                            }
                        }
                    })
                }
            }
        }



    private fun before(tag: String, postsNumber: Int, delay: Long) {
        loading = true
        val repository = Poste_Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.afficher_by_tag(tag, postsNumber)
        viewModel.responseData.observe(this, Observer { response ->
            if(response.isSuccessful){
                Handler().postDelayed({
                    loading = false
                }, delay)
                adapter.updatedata(this,R.layout.design_layout, response.body()?.data!!)
            }else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })

    }

    // update recyclerview
    private fun updaterecycleview() {
        update.adapter = adapter
        update.layoutManager = LinearLayoutManager(this)
    }
}