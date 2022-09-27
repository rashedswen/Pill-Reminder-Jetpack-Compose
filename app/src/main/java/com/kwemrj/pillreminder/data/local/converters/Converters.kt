package com.kwemrj.pillreminder.data.local.converters

import androidx.room.TypeConverter
import com.kwemrj.pillreminder.core.enums.MedicationForm
import com.kwemrj.pillreminder.core.enums.MedicineStatus
import com.kwemrj.pillreminder.core.enums.TakeStatus
import java.text.FieldPosition

class Converters {

    @TypeConverter
    fun fromMedicineStatus(medicineStatus: MedicineStatus) : Int {
        return medicineStatus.ordinal
    }

    @TypeConverter
    fun toMedicineStatus(position: Int) : MedicineStatus{
        return enumValues<MedicineStatus>()[position]
    }

    @TypeConverter
    fun fromMedicationForm(medicationForm: MedicationForm) : Int {
        return medicationForm.ordinal
    }

    @TypeConverter
    fun toMedicationForm(position: Int) : MedicationForm {
        return enumValues<MedicationForm>()[position]
    }

    @TypeConverter
    fun fromTakeStatus(takeStatus: TakeStatus) : Int {
        return takeStatus.ordinal
    }

    @TypeConverter
    fun toTakeStatus(position: Int) : TakeStatus{
        return enumValues<TakeStatus>()[position]
    }

}