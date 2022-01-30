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
@Entity(tableName = "Film")/*,
    foreignKeys = [ForeignKey(entity = Realisateur::class,
                                parentColumns = arrayOf("NoFilm"),
                                childColumns = arrayOf("NoRea"),
                                onDelete = CASCADE)])*/
data class Film(
    @Json(name="NoFilm")
    @SerializedName("NoFilm")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "NoFilm")
    private var _NoFilm: Int = 0,

    @Json(name="Titre")
    @SerializedName("Titre")
    @ColumnInfo(name = "Titre")
    private var _Titre: String? = "",

    @Json(name="Duree")
    @SerializedName("Duree")
    @ColumnInfo(name = "Duree")
    private var _Duree: Int = 0,

    @Json(name="DateSortie")
    @SerializedName("DateSortie")
    @ColumnInfo(name = "DateSortie")
    private var _DateSortie: String? = "",

    @Json(name="Budget")
    @SerializedName("Budget")
    @ColumnInfo(name = "Budget")
    private var _Budget: Int = 0,

    @Json(name="MontantRecette")
    @SerializedName("MontantRecette")
    @ColumnInfo(name = "MontantRecette")
    private var _MontantRecette: Int = 0,

    @Json(name="NoRea")
    @SerializedName("NoRea")
    @ColumnInfo(name = "NoRea")
    private var _NoRea: Int = 0,

    @Json(name="CodeCat")
    @SerializedName("CodeCat")
    @ColumnInfo(name = "CodeCat")
    private var _CodeCat: String? = "",

    @Json(name="Image")
    @SerializedName("Image")
    @ColumnInfo(name = "Image")
    private var _Image: String? = "",

    ): Parcelable,
    BaseObservable() {

    var NoFilm: Int
        @Bindable get() = _NoFilm
        set(value) {
            _NoFilm = value
            notifyPropertyChanged(BR.noFilm)
        }

    var Titre: String?
        @Bindable get() = _Titre
        set(value) {
            _Titre = value
            notifyPropertyChanged(BR.titre)
        }

    var Duree: Int
        @Bindable get() = _Duree
        set(value) {
            _Duree = value
            notifyPropertyChanged(BR.duree)
        }

    var DateSortie: String?
        @Bindable get() = _DateSortie
        set(value) {
            _DateSortie = value
            notifyPropertyChanged(BR.dateSortie)
        }

    var Budget: Int
        @Bindable get() = _Budget
        set(value) {
            _Budget = value
            notifyPropertyChanged(BR.budget)
        }

    var MontantRecette: Int
        @Bindable get() = _MontantRecette
        set(value) {
            _MontantRecette = value
            notifyPropertyChanged(BR.montantRecette)
        }

    var NoRea: Int
        @Bindable get() = _NoRea
        set(value) {
            _NoRea = value
            notifyPropertyChanged(BR.noRea)
        }

    var CodeCat: String?
    @Bindable get() = _CodeCat
        set(value) {
            _CodeCat = value
            notifyPropertyChanged(BR.codeCat)
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
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
        )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(NoFilm)
        parcel.writeString(Titre)
        parcel.writeInt(Duree)
        parcel.writeString(DateSortie)
        parcel.writeInt(Budget)
        parcel.writeInt(MontantRecette)
        parcel.writeInt(NoRea)
        parcel.writeString(CodeCat)
        parcel.writeString(Image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Film> {
        override fun createFromParcel(parcel: Parcel): Film {
            return Film(parcel)
        }

        override fun newArray(size: Int): Array<Film?> {
            return arrayOfNulls(size)
        }
    }
}

