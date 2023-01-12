package com.example.nextprojet.folder_adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nextprojet.R
import com.example.nextprojet.PosteBytag

import com.example.nextprojet.folder_data.data.PostData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_layout.view.*


// Adapter mais cette fois avec "Postdata"
class PostAdapter: RecyclerView.Adapter<PostAdapter.MyViewHolder>(){
    // initialisation d'un adapteur de  donné pour la list "PostPreview"
    private var Context: Context? = null
    private var Poste= emptyList<PostData>()
    private var Layout = R.layout.design_layout


    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(Layout, parent, false))
    }

    override fun getItemCount(): Int {
        return Poste.size
    }


    // update les élements de view
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.nom_utili.text = String.format("%s. %s %s", Poste[position].owner.title, Poste[position].owner.firstName, Poste[position].owner.lastName )
        holder.itemView.date.text = Poste[position].publishDate
        Picasso.get().load(Poste[position].owner.picture).into(holder.itemView.imag_utili);
        Picasso.get().load(Poste[position].image).into(holder.itemView.image_pricip);
        holder.itemView.le_contenu.text = Poste[position].text
        holder.itemView.tag1.text = Poste[position].tags[0]
        holder.itemView.tag2.text = Poste[position].tags[1]
        holder.itemView.tag3.text = Poste[position].tags[2]




        //les postes en utilisant les tags:1,2,3
        holder.itemView.tag1.setOnClickListener {
            val tag = it.tag1.text
            val intent = Intent(Context, PosteBytag::class.java)
            intent.putExtra("tag", tag)
            Context?.startActivity(intent)
        }
        holder.itemView.tag2.setOnClickListener {
            val tag = it.tag2.text
            val intent = Intent(Context, PosteBytag::class.java)
            intent.putExtra("tag", tag)
            Context?.startActivity(intent)
        }
        holder.itemView.tag3.setOnClickListener {
            val tag = it.tag3.text
            val intent = Intent(Context, PosteBytag::class.java)
            intent.putExtra("tag", tag)
            Context?.startActivity(intent)
        }
    }


    // l'update de recycleview
    fun update_poste_data(context: Context, layout: Int, Post: List<PostData>){
        Poste = Post
        Context = context
        Layout = layout
        notifyDataSetChanged()
    }






}