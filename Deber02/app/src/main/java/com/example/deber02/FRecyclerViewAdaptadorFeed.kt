package com.example.deber02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FRecyclerViewAdaptadorFeed(private val contexto: GRecyclerViewFeed,
                                 private val listaHome: List<BFeedHome>,
                                 private val recyclerView: RecyclerView
): RecyclerView.Adapter<FRecyclerViewAdaptadorFeed.MyViewHolder>()
{
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        var click: Boolean = false
        val usernameTextView: TextView
        val useridTextView: TextView
        val likesTextView: TextView
        val userTweet: TextView
        val userRetweets: TextView
        val userComments: TextView

        val userImage: ImageView
        val likeImage: ImageView
        //val accionButton: Button
        var numeroLikes = 5



        //val iconLike: ImageView



        init {
            usernameTextView = view.findViewById(R.id.tv_username)
            useridTextView = view.findViewById(R.id.tv_tema)
            userImage = view.findViewById(R.id.img_user)
            userTweet = view.findViewById(R.id.tv_tweet)
            userRetweets = view.findViewById(R.id.tv_retweets)
            userComments = view.findViewById(R.id.tv_comments)
            likeImage = view.findViewById(R.id.icon_like)

            likesTextView = view.findViewById(R.id.tv_likes)
            //accionButton = view.findViewById(R.id.btn_dar_like)


            likeImage.setOnClickListener{
                this.anadirLike()
            }



        }
        fun anadirLike(){

            if (click == false) {
                //this.numeroLikes = this.numeroLikes + 1
                val num = likesTextView.text.toString().toInt() + 1
                likesTextView.text = num.toString()
                //likesTextView.text = this.numeroLikes.toString()
                likeImage.setImageResource(R.drawable.icon_like_on)
                click = true
            }else{
                //this.numeroLikes = this.numeroLikes - 1
                val num = likesTextView.text.toString().toInt() - 1
                likesTextView.text = num.toString()
                //likesTextView.text = this.numeroLikes.toString()
                likeImage.setImageResource(R.drawable.icon_like_off)
                click = false
            }
            //contexto.aumentarTotalLikes()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recycled_view_feed,
            parent,
            false,
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = listaHome[position]
        holder.usernameTextView.text = user.username
        holder.useridTextView.text = user.userid
        //holder.accionButton.text = "Like a ${entrenador.nombre}"
        holder.userImage.setImageResource(user.image)
        holder.userTweet.text = user.tweet
        holder.userRetweets.text = user.retweet.toString()
        holder.userComments.text = user.comments.toString()
        holder.likesTextView.text = user.likes.toString()

    }

    override fun getItemCount(): Int {
        return listaHome.size//para saber cuantos en la lista
    }
}