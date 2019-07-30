package example.room.livedata.view.activity

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import example.room.livedata.R
import example.room.livedata.databinding.ActivityNewProjectBinding
import example.room.livedata.model.Projects
import example.room.livedata.viewmodel.ProjectsViewModel
import java.util.*


class NewProjectActivity : AppCompatActivity() {

    private var mEditProject: EditText? = null
    var mProjectsViewModel: ProjectsViewModel? = null
    var mProjects:Projects?=null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_new_project) as ActivityNewProjectBinding

        mEditProject = findViewById(R.id.edit_pjct)
        val extra=intent.extras
        if(extra!=null){
            mProjects=extra.getParcelable("project")
        }
        var projectName: String?=null
        if(mProjects==null){
            projectName= "Project-" + (Random().nextInt(100))
            mProjects= Projects(projectName)
        }
        dataBinding.data=mProjects
        dataBinding.activity=this
        dataBinding.executePendingBindings()

        mProjectsViewModel = ViewModelProviders.of(this).get(ProjectsViewModel::class.java!!)


    }

    fun onClick(view:View){
        when(view.id){
            R.id.button_save->{
                mProjects?.mUpdatedTimeStamp=System.currentTimeMillis()
                mProjectsViewModel?.insert(mProjects!!)
                finish()
                return
            }
        }
    }

    companion object {

        val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}