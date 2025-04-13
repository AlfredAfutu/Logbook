package com.codelabs.framework_provider.interfaces.database

import com.codelabs.framework_provider.interfaces.database.dao.IBloodGlucoseDao

interface ILogbookDatabase {
    fun bloodGlucoseDao(): IBloodGlucoseDao
}