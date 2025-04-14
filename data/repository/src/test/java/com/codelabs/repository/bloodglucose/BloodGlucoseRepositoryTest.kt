package com.codelabs.repository.bloodglucose

import com.codelabs.framework_provider.interfaces.database.dao.MockBloodGlucoseDao
import com.codelabs.model.BloodGlucose
import com.codelabs.repository.RepositoryTest
import com.codelabs.repository.di.component.DaggerRepositoryTestComponent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.time.LocalDateTime
import javax.inject.Inject

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BloodGlucoseRepositoryTest : RepositoryTest() {
    @Inject
    lateinit var dao: MockBloodGlucoseDao

    @Inject
    lateinit var repository: BloodGlucoseRepository

    init {
        DaggerRepositoryTestComponent.create().inject(this)
    }

    @Nested
    @DisplayName("Given a BloodGlucoseRepository is initialized")
    inner class Initialized {
        @Nested
        @DisplayName("When blood glucose readings are added")
        inner class BloodGlucoseAdded {
            @BeforeEach
            fun setup() {
                val bloodGlucose1 = BloodGlucose(LocalDateTime.now(), level = 129.0)
                val bloodGlucose2 = BloodGlucose(LocalDateTime.now(), level = 130.0)
                runTest {
                    repository.addBloodGlucoseReading(bloodGlucose1)
                    repository.addBloodGlucoseReading(bloodGlucose2)
                }
            }

            @Test
            fun `then they should be stored in the database`() {
                runTest {
                    Assertions.assertEquals(2, dao.insertCount)
                }
            }
        }

        @Nested
        @DisplayName("And when the blood glucose readings are retrieved")
        inner class BloodGlucoseRetrieved {
            private lateinit var readings: List<BloodGlucose>

            @BeforeEach
            fun setup() {
                runTest {
                    repository.getAllBloodGlucoseReadings().collectLatest { readings = it }
                }
            }

            @Test
            fun `then it should return the correct number of readings`() {
                Assertions.assertEquals(2, readings.size)
            }

            @Test
            fun `then it should return the first reading as inserted`() {
                Assertions.assertEquals(129.0, readings[0].level)
            }

            @Test
            fun `then it should return the second reading as inserted`() {
                Assertions.assertEquals(130.0, readings[1].level)
            }
        }
    }
}