package example.room.livedata.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_project")
data class Projects(@ColumnInfo(name = "name") var mProjectName:String) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "m_id")
    var mId:Int=0
    @ColumnInfo(name = "ts")
    var mUpdatedTimeStamp: Long = 0

    constructor(parcel: Parcel) : this(parcel.readString()) {
        mUpdatedTimeStamp = parcel.readLong()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(mProjectName)
        parcel.writeLong(mUpdatedTimeStamp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Projects> {
        override fun createFromParcel(parcel: Parcel): Projects {
            return Projects(parcel)
        }

        override fun newArray(size: Int): Array<Projects?> {
            return arrayOfNulls(size)
        }
    }


}
