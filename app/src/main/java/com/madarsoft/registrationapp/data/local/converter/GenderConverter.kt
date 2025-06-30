package com.madarsoft.registrationapp.data.local.converter

import androidx.room.TypeConverter
import com.madarsoft.registrationapp.domain.model.Gender

class GenderConverter {
    @TypeConverter
    fun fromGender(gender: Gender): String {
        return gender.name
    }

    @TypeConverter
    fun toGender(gender: String): Gender {
        return Gender.valueOf(gender)
    }
} 