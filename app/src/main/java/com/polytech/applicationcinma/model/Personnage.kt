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
@Entity(tableName = "Personnage")
data class Personnage  (
    @Json(name="noPerso")
    @SerializedName("noPerso")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "NoPerso")
    private var _NoPerso: Int = 0,

    @Json(name="NoFilm")
    @SerializedName("NoFilm")
    @ColumnInfo(name = "NoFilm")
    private var _NoFilm: Int = 0,

    @Json(name="NoAct")
    @SerializedName("NoAct")
    @ColumnInfo(name = "NoAct")
    private var _NoAct: Int = 0,

    @Json(name="nomPers")
    @SerializedName("nomPers")
    @ColumnInfo(name = "NomPers")
    private var _NomPers: String? = "",

    ): Parcelable,
    BaseObservable() {

    var NoPerso: Int
        @Bindable get() = _NoPerso
        set(value) {
            _NoPerso = value
            notifyPropertyChanged(BR.noPerso)
        }

    var NoFilm: Int
        @Bindable get() = _NoFilm
        set(value) {
            _NoFilm = value
            notifyPropertyChanged(BR.noFilm)
        }

    var NoAct: Int
        @Bindable get() = _NoAct
        set(value) {
            _NoAct = value
            notifyPropertyChanged(BR.noAct)
        }

    var NomPers: String?
        @Bindable get() = _NomPers
        set(value) {
            _NomPers = value
            notifyPropertyChanged(BR.nomPers)
        }


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(NoPerso)
        parcel.writeInt(NoFilm)
        parcel.writeInt(NoAct)
        parcel.writeString(NomPers)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Personnage> {
        override fun createFromParcel(parcel: Parcel): Personnage {
            return Personnage(parcel)
        }

        override fun newArray(size: Int): Array<Personnage?> {
            return arrayOfNulls(size)
        }
    }
}

