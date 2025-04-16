package com.codelabs.viewmodel.bloodglucose

import com.codelabs.framework_provider.interfaces.database.dao.MockBloodGlucoseDao
import com.codelabs.model.Unit
import com.codelabs.viewmodel.ViewModelTest
import com.codelabs.viewmodel.bloodglucose.model.ReadingEntryUIModel
import com.codelabs.viewmodel.di.component.DaggerViewModelTestComponent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import javax.inject.Inject

class ReadingEntryViewModelTest : ViewModelTest() {
    @Inject
    lateinit var dao: MockBloodGlucoseDao

    @Inject
    lateinit var viewModel: ReadingEntryViewModel

    @BeforeEach
    fun setup() {
        DaggerViewModelTestComponent.create().inject(this)
    }

    @Nested
    @DisplayName("Given the view appears")
    inner class ViewAppear {
        @Test
        fun `then is it in the default state`() = runTest {
            assertEquals(ReadingEntryUIModel.DEFAULT, viewModel.state.value)
        }

        @Nested
        @DisplayName("When the unit is changed to mgDL")
        inner class ChangeToMGDL {
            @BeforeEach
            fun setup() {
                viewModel.onUnitChange(Unit.MG_DL)
            }

            @Test
            fun `then the unit is updated to mgDL`() = runTest {
                assertEquals(Unit.MG_DL, viewModel.state.value.selectedUnit)
            }

            @Test
            fun `then the level is updated to empty mgDL`() = runTest {
                assertTrue(viewModel.state.value.level.isEmpty())
            }

            @Test
            fun `then it is invalid`() = runTest {
                assertTrue(viewModel.state.value.isInvalid)
            }

            @Test
            fun `then the unit options do not change`() {
                assertEquals(Unit.entries, viewModel.state.value.unitOptions)
            }
        }

        @Nested
        @DisplayName("When the unit is changed to mmoLL")
        inner class ChangeToMMOLL {
            @BeforeEach
            fun setup() {
                viewModel.onUnitChange(Unit.MG_DL)
                viewModel.onUnitChange(Unit.MMOL_L)
            }

            @Test
            fun `then the unit is updated to mmoLL`() = runTest {
                assertEquals(Unit.MMOL_L, viewModel.state.value.selectedUnit)
            }

            @Test
            fun `then the level is updated to empty mgDL`() = runTest {
                assertTrue(viewModel.state.value.level.isEmpty())
            }

            @Test
            fun `then it is invalid`() = runTest {
                assertTrue(viewModel.state.value.isInvalid)
            }

            @Test
            fun `then the unit options do not change`() {
                assertEquals(Unit.entries, viewModel.state.value.unitOptions)
            }
        }

        @Nested
        @DisplayName("When the level is changed")
        inner class ChangeLevel {
            @Nested
            @DisplayName("And the level is 0")
            inner class ZeroLevel {
                @BeforeEach
                fun setup() {
                    viewModel.onLevelChange("0.0")
                }

                @Test
                fun `then the level is updated`() = runTest {
                    assertEquals("0.0", viewModel.state.value.level)
                }

                @Test
                fun `then it is not invalid`() = runTest {
                    assertFalse(viewModel.state.value.isInvalid)
                }

                @Test
                fun `then the unit is not changed`() = runTest {
                    assertEquals(Unit.MMOL_L, viewModel.state.value.selectedUnit)
                }
            }

            @Nested
            @DisplayName("And the level is less than 0")
            inner class NegativeLevel {
                @BeforeEach
                fun setup() {
                    viewModel.onLevelChange("-9021")
                }

                @Test
                fun `then the level stays the same`() = runTest {
                    assertEquals("-9021", viewModel.state.value.level)
                }

                @Test
                fun `then it is invalid`() = runTest {
                    assertTrue(viewModel.state.value.isInvalid)
                }

                @Test
                fun `then the unit is not changed`() = runTest {
                    assertEquals(Unit.MMOL_L, viewModel.state.value.selectedUnit)
                }
            }

            @Nested
            @DisplayName("And the level is not a number")
            inner class NonNumericLevel {
                @BeforeEach
                fun setup() {
                    viewModel.onLevelChange("abc")
                }

                @Test
                fun `then the level stays the same`() = runTest {
                    assertEquals("abc", viewModel.state.value.level)
                }

                @Test
                fun `then it is invalid`() = runTest {
                    assertTrue(viewModel.state.value.isInvalid)
                }

                @Test
                fun `then the unit is not changed`() = runTest {
                    assertEquals(Unit.MMOL_L, viewModel.state.value.selectedUnit)
                }
            }

            @Nested
            @DisplayName("Level > 0")
            inner class LevelGreaterThanZero {
                @BeforeEach
                fun setup() {
                    viewModel.onLevelChange("5.5")
                }

                @Test
                fun `then the level is updated`() = runTest {
                    assertEquals("5.5", viewModel.state.value.level)
                }

                @Test
                fun `then it is not invalid`() = runTest {
                    assertFalse(viewModel.state.value.isInvalid)
                }

                @Test
                fun `then the unit is not changed`() = runTest {
                    assertEquals(Unit.MMOL_L, viewModel.state.value.selectedUnit)
                }

                @Nested
                @DisplayName("And the unit is changed to mgDL")
                inner class ChangeUnit {
                    @BeforeEach
                    fun setup() {
                        viewModel.onUnitChange(Unit.MG_DL)
                    }

                    @Test
                    fun `then the level is converted`() = runTest {
                        assertEquals("99.1001", viewModel.state.value.level)
                    }

                    @Test
                    fun `then it is not invalid`() = runTest {
                        assertFalse(viewModel.state.value.isInvalid)
                    }

                    @Test
                    fun `then the unit is updated`() = runTest {
                        assertEquals(Unit.MG_DL, viewModel.state.value.selectedUnit)
                    }

                    @Nested
                    @DisplayName("And the unit is changed to mmoLL")
                    inner class ChangeUnit {
                        @BeforeEach
                        fun setup() {
                            viewModel.onUnitChange(Unit.MMOL_L)
                        }

                        @Test
                        fun `then the level is converted`() = runTest {
                            assertEquals("5.5", viewModel.state.value.level)
                        }

                        @Test
                        fun `then it is not invalid`() = runTest {
                            assertFalse(viewModel.state.value.isInvalid)
                        }

                        @Test
                        fun `then the unit is updated`() = runTest {
                            assertEquals(Unit.MMOL_L, viewModel.state.value.selectedUnit)
                        }
                    }
                }
            }
        }

        @Nested
        @DisplayName("When the level is saved")
        inner class SaveLevel {
            @Nested
            @DisplayName("And the level is 0")
            inner class ZeroLevel {
                @Nested
                @DisplayName("And the unit is mmoLL")
                inner class MmoLL {
                    @BeforeEach
                    fun setup() = runTest {
                        viewModel.onSaveTap("0.0")
                    }

                    @Test
                    fun `then the level is saved in mmoLL`() = runTest {
                        assertEquals(0.0, dao.levels.last())
                    }

                    @Test
                    fun `then the level is cleared`() = runTest {
                        assertEquals("", viewModel.state.value.level)
                    }
                }

                @Nested
                @DisplayName("And the unit is mgDL")
                inner class MgDL {
                    @BeforeEach
                    fun setup() = runTest {
                        viewModel.onUnitChange(Unit.MG_DL)
                        viewModel.onSaveTap("0.0")
                    }

                    @Test
                    fun `then the level is saved in mmoLL`() = runTest {
                        assertEquals(0.0, dao.levels.last())
                    }

                    @Test
                    fun `then the level is cleared`() = runTest {
                        assertEquals("", viewModel.state.value.level)
                    }
                }
            }

            @Nested
            @DisplayName("And the level is less than 0")
            inner class NegativeLevel {
                @BeforeEach
                fun setup() = runTest {
                    viewModel.onSaveTap("-9021")
                }

                @Test
                fun `then the level is not saved`() = runTest {
                    assertTrue(dao.insertCount == 0)
                }

                @Test
                fun `then it is invalid`() = runTest {
                    assertTrue(viewModel.state.value.isInvalid)
                }
            }

            @Nested
            @DisplayName("And the level is not a number")
            inner class NonNumericLevel {
                @BeforeEach
                fun setup() = runTest {
                    viewModel.onSaveTap("abc")
                }

                @Test
                fun `then the level is not saved`() = runTest {
                    assertTrue(dao.insertCount == 0)
                }

                @Test
                fun `then it is invalid`() = runTest {
                    assertTrue(viewModel.state.value.isInvalid)
                }
            }

            @Nested
            @DisplayName("Level > 0")
            inner class LevelGreaterThanZero {
                @Nested
                @DisplayName("And the unit is mmoLL")
                inner class MmoLL {
                    @BeforeEach
                    fun setup() = runTest {
                        viewModel.onSaveTap("102.5")
                    }

                    @Test
                    fun `then the level is saved in mmoLL`() = runTest {
                        assertEquals(102.5, dao.levels.last())
                    }

                    @Test
                    fun `then the level is cleared`() = runTest {
                        assertEquals("", viewModel.state.value.level)
                    }
                }

                @Nested
                @DisplayName("And the unit is mgDL")
                inner class MgDL {
                    @BeforeEach
                    fun setup() = runTest {
                        viewModel.onUnitChange(Unit.MG_DL)
                        viewModel.onSaveTap("102.5")
                    }

                    @Test
                    fun `then the level is saved in mmoLL`() = runTest {
                        assertEquals(5.688692544205304, dao.levels.last())
                    }

                    @Test
                    fun `then the level is cleared`() = runTest {
                        assertEquals("", viewModel.state.value.level)
                    }
                }
            }
        }
    }
}