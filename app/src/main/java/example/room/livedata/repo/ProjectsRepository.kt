package example.room.livedata.repo

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import example.room.livedata.model.Projects
import example.room.livedata.repo.room.ProjectsDao
import example.room.livedata.repo.room.db.ProjectsRoomDatabase

class ProjectsRepository {
    var mProjectsDao: ProjectsDao?=null
    var mAllProjects:LiveData<PagedList<Projects>>?=null
    constructor(ctx :Application){
        val db=ProjectsRoomDatabase.getDatabase(ctx)
        mProjectsDao=db.projectDao()
        mAllProjects=mProjectsDao?.getAllProjects()?.toLiveData(pageSize = 50)
    }

    fun insert(project:Projects ){
        mProjectsDao?.insert(project)
    }
}