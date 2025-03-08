package com.example.assignment2

import android.os.Parcel
import android.os.Parcelable
//DATA CLASS
data class RentalItem(
    val ID: Int, // ID of the item
    val imageRes: Int, // Image resource ID
    val name: String,
    val rating: Float, // 0-5
    val attributes: List<String>, // Multi-choice attributes
    val price: Int // Monthly price in "credits"
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.createStringArrayList()!!,
        parcel.readInt()
    )
    // write to Parcel function
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ID)
        parcel.writeInt(imageRes)
        parcel.writeString(name)
        parcel.writeFloat(rating)
        parcel.writeStringList(attributes)
        parcel.writeInt(price)
    }
    //describe Content
    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<RentalItem>
    {
        override fun createFromParcel(parcel: Parcel): RentalItem
        {
            return RentalItem(parcel)
        }

        override fun newArray(size:Int): Array<RentalItem?>
        {
            return arrayOfNulls(size)
        }
    }
}
