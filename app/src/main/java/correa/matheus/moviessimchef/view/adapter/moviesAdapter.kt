package correa.matheus.moviessimchef.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import correa.matheus.moviessimchef.R
import correa.matheus.moviessimchef.data.model.Movie

class moviesAdapter(private val movies : List<Movie>, var clickListener: OnClickItemListener) : RecyclerView.Adapter<moviesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.movies_item,parent, false)


        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        holder.initialize(movies.get(position),clickListener)
        holder.bindView(item)
    }
    override fun getItemCount() = movies.size

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val titleMovie = itemView.findViewById<TextView>(R.id.titleMovie)
        val descriptionMovie = itemView.findViewById<TextView>(R.id.descriptionMovie)
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)

        fun bindView(item: Movie) = with(itemView){

            item.let{
                titleMovie.text = item.title
                descriptionMovie.text = item.plot
                Glide.with(itemView.context).load(it.poster).into(imageView)
            }

        }
        fun initialize(item: Movie, action: OnClickItemListener){
            titleMovie.text = item.title
            descriptionMovie.text = item.plot

            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)
            }
        }
    }
    interface OnClickItemListener{
        fun onItemClick(items: Movie, position: Int)
    }
}