package org.specnaz.kotlin

import org.specnaz.SpecBuilder

/**
 * The equivalent of [SpecBuilder] for Kotlin.
 *
 * The main difference is that all of the lifecycle callback methods
 * (`before`/`after`) and the test bodies take `Nothing?`
 * (the equivalent of [Void] in Java) as a first parameter,
 * which allows for a little better auto-completion experience
 * when using the default anonymous function parameter (`it`) in Kotlin.
 *
 * @see should
 * @see shouldThrow
 * @see beginsAll
 * @see beginsEach
 * @see endsEach
 * @see endsAll
 * @see describes
 */
class KotlinSpecBuilder(@Deprecated("Only public because shouldThrow uses a reified type parameter and needs to be inline. Do not use!")
                        val specBuilder: SpecBuilder) {
    /**
     * The equivalent of [SpecBuilder.beginsAll] for Kotlin.
     */
    fun beginsAll(closure: (Nothing?) -> Unit) {
        specBuilder.beginsAll { closure.invoke(null) }
    }

    /**
     * The equivalent of [SpecBuilder.beginsEach] for Kotlin.
     */
    fun beginsEach(closure: (Nothing?) -> Unit) {
        specBuilder.beginsEach { closure.invoke(null) }
    }

    /**
     * The equivalent of [SpecBuilder.should] for Kotlin.
     */
    fun should(description: String, testBody: (Nothing?) -> Unit) {
        specBuilder.should(description, { testBody.invoke(null) })
    }

    /**
     * The equivalent of [SpecBuilder.shouldThrow] for Kotlin.
     * The difference from the Java version is that you don't pass the Exception class
     * as the first parameter of the method call,
     * but instead provide it as the type parameter. Example:
     *
     * ```kotlin
     * it.shouldThrow<ArithmeticException>("when dividing by zero") {
     *     1 / 0
     * }
     * ```
     */
    inline fun <reified E : Throwable> shouldThrow(description: String, crossinline testBody: (Nothing?) -> Unit) {
        specBuilder.shouldThrow(E::class.java, description, { testBody.invoke(null) })
    }

    /**
     * The equivalent of [SpecBuilder.endsEach] for Kotlin.
     */
    fun endsEach(closure: (Nothing?) -> Unit) {
        specBuilder.endsEach { closure.invoke(null) }
    }

    /**
     * The equivalent of [SpecBuilder.endsAll] for Kotlin.
     */
    fun endsAll(closure: (Nothing?) -> Unit) {
        specBuilder.endsAll { closure.invoke(null) }
    }

    /**
     * The equivalent of [SpecBuilder.describes] for Kotlin.
     */
    fun describes(description: String, specClosure: () -> Unit) {
        specBuilder.describes(description, { specClosure.invoke() })
    }
}
