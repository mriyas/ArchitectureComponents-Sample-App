package example.room.livedata.repo.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import example.room.livedata.model.Projects
import example.room.livedata.repo.room.ProjectsDao

@Database(entities = arrayOf(Projects::class), version = 2)
abstract open class ProjectsRoomDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectsDao

    companion object {
        @Volatile
        private var INSTANCE: ProjectsRoomDatabase? = null

        fun getDatabase(context: Context): ProjectsRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProjectsRoomDatabase::class.java,
                    "my_pjcts"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}