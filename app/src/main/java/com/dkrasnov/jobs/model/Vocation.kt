package com.dkrasnov.jobs.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Vocation() : Parcelable {

    var id: String = ""
    var title: String? = null
    var location: String? = null
    var type: String? = null
    var description: String? = null
    var company: String? = null
    @SerializedName("company_url")
    var companyUrl: String? = null
    @SerializedName("company_logo")
    var companyLogo: String? = null
    var url: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        title = parcel.readString()
        location = parcel.readString()
        type = parcel.readString()
        description = parcel.readString()
        company = parcel.readString()
        companyUrl = parcel.readString()
        companyLogo = parcel.readString()
        url = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(location)
        parcel.writeString(type)
        parcel.writeString(description)
        parcel.writeString(company)
        parcel.writeString(companyUrl)
        parcel.writeString(companyLogo)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Vocation> {
        override fun createFromParcel(parcel: Parcel): Vocation {
            return Vocation(parcel)
        }

        override fun newArray(size: Int): Array<Vocation?> {
            return arrayOfNulls(size)
        }
    }

}