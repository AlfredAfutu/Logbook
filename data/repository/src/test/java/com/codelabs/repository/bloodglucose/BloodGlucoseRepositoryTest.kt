package com.codelabs.repository.bloodglucose

import com.codelabs.framework_provider.interfaces.database.dao.MockBloodGlucoseDao
import com.codelabs.model.BloodGlucose
import com.codelabs.repository.RepositoryTest
import com.codelabs.repository.di.component.DaggerRepositoryTestComponent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import javax.inject.Inject

class BloodGlucoseRepositoryTest : RepositoryTest() {
    @Inject
    lateinit var dao: MockBloodGlucoseDao

    @Inject
    lateinit var repository: BloodGlucoseRepository

    @BeforeEach
    fun setup() {
        DaggerRepositoryTestComponent.create().inject(this)
    }

    @Nested
    @DisplayName("Given a BloodGlucoseRepository is initialized")
    inner class Initialized {
        @Nested
        @DisplayName("When a blood glucose reading is added")
        inner class BloodGlucoseAdded {
            @Test
            fun `then it should be store in the database`() {
                val bloodGlucose = BloodGlucose(100, LocalDateTime.now(), level = 129.0)

                runTest {
                    repository.addBloodGlucoseReading(bloodGlucose)

                    dao.getAll().collect { readings ->
                        assert(readings.contains(bloodGlucose))
                    }
                }
            }
        }
    }
}