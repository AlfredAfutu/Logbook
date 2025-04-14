package com.codelabs.logic.bloodglucose

import com.codelabs.framework_provider.interfaces.database.dao.MockBloodGlucoseDao
import com.codelabs.logic.UseCaseTest
import com.codelabs.logic.di.component.DaggerLogicTestComponent
import com.codelabs.model.ReadingInput
import com.codelabs.model.Unit
import com.codelabs.model.toMMOLL
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import javax.inject.Inject

class AddBloodGlucoseReadingInputUseCaseTest : UseCaseTest() {
    @Inject
    lateinit var dao: MockBloodGlucoseDao

    @Inject
    lateinit var useCase: AddBloodGlucoseReadingUseCase

    @BeforeEach
    fun setup() {
        DaggerLogicTestComponent.create().inject(this)
    }

    @Nested
    @DisplayName("Given the usecase is invoked")
    inner class Invoked {
        @Nested
        @DisplayName("When the reading is in the mg/dl unit")
        inner class ReadingInputInMgDl {
            private val timestamp = LocalDateTime.now()
            @BeforeEach
            fun setup() {
                val reading = ReadingInput(
                    level = 180.0,
                    unit = Unit.MG_DL,
                    timestamp = timestamp
                )
                runTest {
                    useCase(reading)
                }
            }

            @Test
            fun `then the reading should be converted and saved as MMOLL`() {
                assertEquals(180.0.toMMOLL(), dao.levels.last())
            }

            @Test
            fun `then the reading should be saved with the correct timestamp`() {
                assertEquals(timestamp.toString(), dao.timestamps.last().toString())
            }
        }

        @Nested
        @DisplayName("When the reading is in the mmol/l unit")
        inner class ReadingInputInMmoLl {
            private val timestamp = LocalDateTime.now()
            @BeforeEach
            fun setup() {
                val reading = ReadingInput(
                    level = 180.0,
                    unit = Unit.MMOL_L,
                    timestamp = timestamp
                )
                runTest {
                    useCase(reading)
                }
            }

            @Test
            fun `then the reading is saved as is`() {
                assertEquals(180.0, dao.levels.last())
            }

            @Test
            fun `then the reading should be saved with the correct timestamp`() {
                assertEquals(timestamp.toString(), dao.timestamps.last().toString())
            }
        }
    }
}