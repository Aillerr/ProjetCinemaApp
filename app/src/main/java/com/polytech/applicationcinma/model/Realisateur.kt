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
@Entity(tableName = "Realisateur")
data class Realisateur  (
    @Json(name="noRea")
    @SerializedName("noRea")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "NoRea")
    private var _NoRea: Int = 0,

    @Json(name="nomRea")
    @SerializedName("nomRea")
    @ColumnInfo(name = "NomRea")
    private var _NomRea: String? = "",

    @Json(name="prenRea")
    @SerializedName("prenRea")
    @ColumnInfo(name = "PrenRea")
    private var _PrenRea: String? = "",

    @Json(name="image")
    @SerializedName("image")
    @ColumnInfo(name = "Image")
    private var _Image: String? = "",

    ): Parcelable,
    BaseObservable() {

    var NoRea: Int
        @Bindable get() = _NoRea
        set(value) {
            _NoRea = value
            notifyPropertyChanged(BR.noRea)
        }

    var NomRea: String?
        @Bindable get() = _NomRea
        set(value) {
            _NomRea = value
            notifyPropertyChanged(BR.nomRea)
        }

    var PrenRea: String?
        @Bindable get() = _PrenRea
        set(value) {
            _PrenRea = value
            notifyPropertyChanged(BR.prenRea)
        }

    var Image: String?
        @Bindable get() = _Image
        set(value) {
            _Image = value
            notifyPropertyChanged(BR.image)
        }


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(NoRea)
        parcel.writeString(NomRea)
        parcel.writeString(PrenRea)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Realisateur> {
        override fun createFromParcel(parcel: Parcel): Realisateur {
            return Realisateur(parcel)
        }

        override fun newArray(size: Int): Array<Realisateur?> {
            return arrayOfNulls(size)
        }
    }
}

