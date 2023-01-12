package com.example.nextprojet.folder_adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nextprojet.SupprimerPoste
import com.example.nextprojet.LesPostes
import com.example.nextprojet.R
import com.example.nextprojet.PosteBytag
import com.example.nextprojet.folder_data.data.PostPreview
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.design_layout.view.*



class Adapter: RecyclerView.Adapter<Adapter.MyViewHolder>(){
    // initialisation d'un adapteur de  donné pour la list "PostPreview"
    private var context1: Context? = null
    private var list1 = emptyList<PostPreview>()
    private var layout1 = R.layout.design_layout


    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(layout1, parent, false))
    }

    override fun getItemCount(): Int {
        return list1.size
    }


    // update les élements de view
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.nom_utili.text = String.format("%s. %s %s", list1[position].owner.title, list1[position].owner.firstName, list1[position].owner.lastName )
        holder.itemView.date.text = list1[position].publishDate
        Picasso.get().load(list1[position].owner.picture).into(holder.itemView.imag_utili);
        Picasso.get().load(list1[position].image).into(holder.itemView.image_pricip);
        holder.itemView.le_contenu.text = list1[position].text
        holder.itemView.tag1.text = list1[position].tags[0]
        holder.itemView.tag2.text = list1[position].tags[1]
        holder.itemView.tag3.text = list1[position].tags[2]



        // l'activité supprimer
        holder.itemView.setOnLongClickListener{
            val postId = list1[position].id
            val intent = Intent(context1, SupprimerPoste::class.java)
            intent.putExtra("post_id", postId)
            context1?.startActivity(intent)
            return@setOnLongClickListener true
        }
        //les postes en utilisant les tags:1,2,3
        holder.itemView.tag1.setOnClickListener {
            val tag = it.tag1.text
            val intent = Intent(context1, PosteBytag::class.java)
            intent.putExtra("tag", tag)
            context1?.startActivity(intent)
        }
        holder.itemView.tag2.setOnClickListener {
            val tag = it.tag2.text
            val intent = Intent(context1, PosteBytag::class.java)
            intent.putExtra("tag", tag)
            context1?.startActivity(intent)
        }
        holder.itemView.tag3.setOnClickListener {
            val tag = it.tag3.text
            val intent = Intent(context1, PosteBytag::class.java)
            intent.putExtra("tag", tag)
            context1?.startActivity(intent)
        }


        // l'acivité de poste
        holder.itemView.setOnClickListener {
            val postId = list1[position].id
            val intent = Intent(context1, LesPostes::class.java)
            intent.putExtra("post_id", postId)
            context1?.startActivity(intent)
        }
    }
    // l'update de recycleview
    fun updatedata(context: Context, layout: Int, newList: List<PostPreview>){
        list1 = newList
        context1 = context
        layout1 = layout
        notifyDataSetChanged()
    }


}