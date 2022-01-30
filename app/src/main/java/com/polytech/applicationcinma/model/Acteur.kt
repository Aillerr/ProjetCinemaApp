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
@Entity(tableName = "Acteur")
data class Acteur  (
    @Json(name="noAct")
    @SerializedName("noAct")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "NoAct")
    private var _NoAct: Int = 0,

    @Json(name="nomAct")
    @SerializedName("nomAct")
    @ColumnInfo(name = "NomAct")
    private var _NomAct: String? = "",

    @Json(name="prenAct")
    @SerializedName("prenAct")
    @ColumnInfo(name = "PrenAct")
    private var _PrenAct: String? = "",

    @Json(name="dateNaiss")
    @SerializedName("dateNaiss")
    @ColumnInfo(name = "DateNaiss")
    private var _DateNaiss: Long? = 0L,

    @Json(name="dateDeces")
    @SerializedName("dateDeces")
    @ColumnInfo(name = "DateDeces")
    private var _DateDeces: Long? = 0L,

    ): Parcelable,
    BaseObservable() {

    var NoAct: Int
        @Bindable get() = _NoAct
        set(value) {
            _NoAct = value
            notifyPropertyChanged(BR.noAct)
        }

    var NomAct: String?
        @Bindable get() = _NomAct
        set(value) {
            _NomAct = value
            notifyPropertyChanged(BR.nomAct)
        }

    var PrenAct: String?
        @Bindable get() = _PrenAct
        set(value) {
            _PrenAct = value
            notifyPropertyChanged(BR.prenAct)
        }

    var DateNaiss: Long?
        @Bindable get() = _DateNaiss
        set(value) {
            _DateNaiss = value
            notifyPropertyChanged(BR.dateNaiss)
        }

    var DateDeces: Long?
        @Bindable get() = _DateDeces
        set(value) {
            _DateDeces = value
            notifyPropertyChanged(BR.dateDeces)
        }


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readLong(),
        )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(NoAct)
        parcel.writeString(NomAct)
        parcel.writeString(PrenAct)
        DateNaiss?.let { parcel.writeLong(it) }
        DateDeces?.let { parcel.writeLong(it) }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Acteur> {
        override fun createFromParcel(parcel: Parcel): Acteur {
            return Acteur(parcel)
        }

        override fun newArray(size: Int): Array<Acteur?> {
            return arrayOfNulls(size)
        }
    }
}

