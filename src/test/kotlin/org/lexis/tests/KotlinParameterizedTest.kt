package org.lexis.tests

import org.junit.jupiter.api.TestInstance

/**
 * Change the lifecycle of a test class to [PER_CLASS][TestInstance.Lifecycle.PER_CLASS] so that parameterized tests
 * can have non-static [MethodSource][org.junit.jupiter.params.provider.MethodSource] test argument factory methods.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class KotlinParameterizedTest {
}