package com.polytech.applicationcinma.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.polytech.applicationcinma.BR
import com.squareup.moshi.Json

@Keep
@Entity(tableName = "Utilisateur")
data class Utilisateur  (
    @Json(name="NoUtil")
    @SerializedName("NoUtil")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "NoUtil")
    private var _NoUtil: Int = 0,

    @Json(name="NomUtil")
    @SerializedName("NomUtil")
    @ColumnInfo(name = "NomUtil")
    private var _NomUtil: String? = "",

    @Json(name="MotPasse")
    @SerializedName("MotPasse")
    @ColumnInfo(name = "MotPasse")
    private var _MotPasse: String? = "",

    @Json(name="Role")
    @SerializedName("Role")
    @ColumnInfo(name = "Role")
    private var _Role: String? = "",

    ): Parcelable,
    BaseObservable() {

    var NoUtil: Int
        @Bindable get() = _NoUtil
        set(value) {
            _NoUtil = value
            notifyPropertyChanged(BR.noUtil)
        }

    var NomUtil: String?
        @Bindable get() = _NomUtil
        set(value) {
            _NomUtil = value
            notifyPropertyChanged(BR.nomUtil)
        }

    var MotPasse: String?
        @Bindable get() = _MotPasse
        set(value) {
            _MotPasse = value
            notifyPropertyChanged(BR.motPasse)
        }

    var Role: String?
        @Bindable get() = _Role
        set(value) {
            _Role = value
            notifyPropertyChanged(BR.role)
        }


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(NoUtil)
        parcel.writeString(NomUtil)
        parcel.writeString(MotPasse)
        parcel.writeString(Role)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Utilisateur> {
        override fun createFromParcel(parcel: Parcel): Utilisateur {
            return Utilisateur(parcel)
        }

        override fun newArray(size: Int): Array<Utilisateur?> {
            return arrayOfNulls(size)
        }
    }
}

