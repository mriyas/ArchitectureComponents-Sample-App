package example.room.livedata.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import example.room.livedata.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import example.room.livedata.view.adapter.ProjectsListAdapter
import example.room.livedata.viewmodel.ProjectsViewModel
import androidx.lifecycle.ViewModelProviders
import example.room.livedata.model.Projects
import example.room.livedata.view.callbacks.OnItemClickListener


class MainActivity : AppCompatActivity(),OnItemClickListener {
    var mProjectsViewModel: ProjectsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mProjectsViewModel = ViewModelProviders.of(this).get(ProjectsViewModel::class.java!!)
        val button = findViewById<View>(R.id.floatingActionButton)
        button.setOnClickListener(View.OnClickListener { view ->
           startActivity(Intent(this,NewProjectActivity::class.java))
        })
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ProjectsListAdapter(this)
        adapter.onClick=this
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        observeData(adapter)
    }

    private fun observeData(adapter: ProjectsListAdapter) {

        mProjectsViewModel?.mAllProjects?.observe(this, Observer<List<Projects>>() { projects ->
            adapter.setWords(projects)
        })
    }

    override fun onItemClick(v: View, obj: Any) {
        val intent=Intent(this,NewProjectActivity::class.java)
        intent.putExtra("project",obj as Projects)
        startActivity(intent)

    }
}
