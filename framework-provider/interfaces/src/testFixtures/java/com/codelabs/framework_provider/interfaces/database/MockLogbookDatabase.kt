package com.codelabs.framework_provider.interfaces.database

import com.codelabs.framework_provider.interfaces.database.dao.IBloodGlucoseDao
import javax.inject.Inject

class MockLogbookDatabase @Inject constructor(
    private val dao: IBloodGlucoseDao
) : ILogbookDatabase {
    override fun bloodGlucoseDao(): IBloodGlucoseDao = dao
}