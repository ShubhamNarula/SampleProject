package com.template.datastore

import androidx.datastore.preferences.core.stringPreferencesKey


const val DATA_STORE_NAME = "Sample_databse"
//key name
val MODE by lazy { stringPreferencesKey("MODE") }



