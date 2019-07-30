package example.room.livedata.view.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import example.room.livedata.model.Projects
import androidx.databinding.DataBindingUtil
import example.room.livedata.R
import example.room.livedata.databinding.RecyclerviewItemBinding
import example.room.livedata.view.callbacks.OnItemClickListener


class ProjectsListAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<ProjectsListAdapter.ProjectViewHolder>() {

    private val mInflater: LayoutInflater
    private var mProjects: List<Projects>? = null // Cached copy of words
    var onClick:OnItemClickListener?=null

    class ProjectViewHolder(val dataBinding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {


        fun bind(projects: Projects, onClick:OnItemClickListener?) {
            dataBinding.project=projects
            dataBinding.callback=onClick
            dataBinding.executePendingBindings()

        }

    }

    init {
        mInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.recyclerview_item, parent, false) as RecyclerviewItemBinding
        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        if (mProjects != null) {
            val current = mProjects!![position]
            holder.bind(current,onClick)
        } else {
            // Covers the case of data not being ready yet.
            // holder.tv_projectName?.text = "No Projects"
        }
    }

    internal fun setWords(words: List<Projects>) {
        mProjects = words
        notifyDataSetChanged()
    }

    // getItemCount() is called many times, and when it is first called,
    // mProjects has not been updated (means initially, it's null, and we can't return null).
    override fun getItemCount(): Int {
        return if (mProjects != null)
            mProjects!!.size
        else
            0
    }
}