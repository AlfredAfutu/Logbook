package com.codelabs.viewmodel.bloodglucose

import com.codelabs.framework_provider.interfaces.database.dao.MockBloodGlucoseDao
import com.codelabs.framework_provider.interfaces.dispatcher.MockDispatcherProvider
import com.codelabs.model.BloodGlucose
import com.codelabs.model.toMGDL
import com.codelabs.viewmodel.ViewModelTest
import com.codelabs.viewmodel.di.component.DaggerViewModelTestComponent
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import javax.inject.Inject

class ReadingsViewModelTest : ViewModelTest() {
    @Inject
    lateinit var dao: MockBloodGlucoseDao

    @Inject
    lateinit var dispatcher: MockDispatcherProvider

    @Inject
    lateinit var viewModel: ReadingsViewModel

    @BeforeEach
    fun setup() {
        DaggerViewModelTestComponent.create()
            .inject(this)
    }

    @Nested
    @DisplayName("Given the view appears")
    inner class ViewAppear {
        @BeforeEach
        fun setup() = runTest {
            backgroundScope.launch(dispatcher.main) {
                viewModel.state.collect {}
            }
        }

        @Nested
        @DisplayName("When there are no readings")
        inner class NoReadings {
            @Test
            fun `then the state is empty`() = runTest {
                assertEquals(ReadingsViewModel.State.Empty, viewModel.state.value)
            }
        }

        @Nested
        @DisplayName("When readings are added")
        inner class WithReadings {
            private val readings = listOf(
                BloodGlucose(level = 5.5, timestamp = LocalDateTime.now()),
                BloodGlucose(level = 6.0, timestamp = LocalDateTime.now())
            )

            @BeforeEach
            fun setup() = runTest {
               dao.flow.emit(readings)
            }

            @Test
            fun `then the state is success`() = runTest {
                val state = viewModel.state.value
                assertInstanceOf(ReadingsViewModel.State.Success::class.java, state)
            }

            @Test
            fun `then the first readings is displayed in mgDl`() = runTest {
                val state = viewModel.state.value as ReadingsViewModel.State.Success
                assertEquals(readings.first().level.toMGDL(), state.readings.first().levelInMgDL)
            }

            @Test
            fun `then the first readings is displayed in mmoLl`() = runTest {
                val state = viewModel.state.value as ReadingsViewModel.State.Success
                assertEquals(readings.first().level, state.readings.first().levelInMMOLL)
            }

            @Test
            fun `then the second readings is displayed in mgDl`() = runTest {
                val state = viewModel.state.value as ReadingsViewModel.State.Success
                assertEquals(readings.last().level.toMGDL(), state.readings.last().levelInMgDL)
            }

            @Test
            fun `then the second readings is displayed in mmoLl`() = runTest {
                val state = viewModel.state.value as ReadingsViewModel.State.Success
                assertEquals(readings.last().level, state.readings.last().levelInMMOLL)
            }
        }
    }
}