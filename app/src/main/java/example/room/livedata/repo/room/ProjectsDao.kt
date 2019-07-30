package example.room.livedata.repo.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import example.room.livedata.model.Projects
@Dao
interface ProjectsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert( projects: Projects)
    @Query("DELETE from tbl_project")
    fun deleteAll()
    @Query("SELECT * from tbl_project ORDER BY ts DESC")
    fun getAllProjects(): DataSource.Factory<Int, Projects>

}