package example.room.livedata.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import example.room.livedata.model.Projects
import example.room.livedata.repo.ProjectsRepository

class ProjectsViewModel (ctx:Application): AndroidViewModel(ctx){
    var mProjectsRepository: ProjectsRepository?=null
    var mAllProjects: LiveData<PagedList<Projects>>?=null
    init{
        mProjectsRepository= ProjectsRepository(ctx)
        mAllProjects = mProjectsRepository?.mAllProjects
    }

    fun insert(project: Projects) {
        val thread=Thread(Runnable {
            mProjectsRepository?.insert(project)

        })
        thread.start()
    }
}
