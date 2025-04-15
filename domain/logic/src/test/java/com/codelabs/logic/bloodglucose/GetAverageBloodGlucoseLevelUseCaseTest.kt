package com.codelabs.logic.bloodglucose

import com.codelabs.framework_provider.interfaces.database.dao.MockBloodGlucoseDao
import com.codelabs.framework_provider.interfaces.dispatcher.MockDispatcherProvider
import com.codelabs.logic.UseCaseTest
import com.codelabs.logic.di.component.DaggerLogicTestComponent
import com.codelabs.model.AverageLevel
import com.codelabs.model.BloodGlucose
import com.codelabs.model.toMGDL
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import javax.inject.Inject

class GetAverageBloodGlucoseLevelUseCaseTest : UseCaseTest() {
    @Inject
    lateinit var dao: MockBloodGlucoseDao

    @Inject
    lateinit var dispatcher: MockDispatcherProvider

    @Inject
    lateinit var useCase: GetAverageBloodGlucoseLevelUseCase

    @BeforeEach
    fun setup() {
        DaggerLogicTestComponent.create().inject(this)
    }

    @Nested
    @DisplayName("Given the usecase is invoked")
    inner class Invoked {
        private var averageLevel: AverageLevel? = null
        private val readings = buildList {
            add(BloodGlucose(LocalDateTime.now(), 5.0))
            add(BloodGlucose(LocalDateTime.now(), 7.0))
            add(BloodGlucose(LocalDateTime.now(), 6.0))
        }

        @BeforeEach
        fun setup() = runTest {
            backgroundScope.launch(dispatcher.database) {
                readings.forEach { dao.insert(it) }
                useCase().collectLatest { averageLevel = it }
            }
        }

        @Test
        fun `then the average level should be calculated correctly`() {
            assertEquals(6.0, averageLevel?.mmoLL)
        }

        @Test
        fun `then the average level in mgDl should be calculated correctly`() {
            assertEquals(6.0.toMGDL(), averageLevel?.mgDL)
        }

        @Nested
        @DisplayName("And more readings are added")
        inner class MoreReadingsAdded {
            private val newReadings = listOf(
                BloodGlucose(LocalDateTime.now(), 8.0),
                BloodGlucose(LocalDateTime.now(), 9.0)
            )

            @BeforeEach
            fun setup() = runTest {
                backgroundScope.launch(dispatcher.database) {
                    newReadings.forEach { dao.insert(it) }
                    useCase().collectLatest { averageLevel = it }
                }
            }

            @Test
            fun `then the average level should be updated correctly`() {
                assertEquals(7.0, averageLevel?.mmoLL)
            }

            @Test
            fun `then the average level in mgDl should be updated correctly`() {
                assertEquals(7.0.toMGDL(), averageLevel?.mgDL)
            }
        }
    }
}