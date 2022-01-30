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
@Entity(tableName = "Categorie")
data class Categorie  (
    @Json(name="CodeCat")
    @SerializedName("CodeCat")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CodeCat")
    private var _CodeCat: Int = 0,

    @Json(name="LibelleCat")
    @SerializedName("LibelleCat")
    @ColumnInfo(name = "LibelleCat")
    private var _LibelleCat: String? = "",

    ): Parcelable,
    BaseObservable() {

    var CodeCat: Int
        @Bindable get() = _CodeCat
        set(value) {
            _CodeCat = value
            notifyPropertyChanged(BR.codeCat)
        }

    var LibelleCat: String?
        @Bindable get() = _LibelleCat
        set(value) {
            _LibelleCat = value
            notifyPropertyChanged(BR.libelleCat)
        }


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(CodeCat)
        parcel.writeString(LibelleCat)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Categorie> {
        override fun createFromParcel(parcel: Parcel): Categorie {
            return Categorie(parcel)
        }

        override fun newArray(size: Int): Array<Categorie?> {
            return arrayOfNulls(size)
        }
    }
}

