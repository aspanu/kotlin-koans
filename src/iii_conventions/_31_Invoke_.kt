package iii_conventions

import util.TODO


class Invokable {
    var numInvocations : Int = 0
    operator fun invoke() : Invokable {
        numInvocations++
        return this
    }

    fun getNumberOfInvocations(): Int {
        return numInvocations
    }
}

fun todoTask31(): Nothing = TODO(
    """
        Task 31.
        Change class Invokable to count the number of invocations (round brackets).
        Uncomment the commented code - it should return 4.
    """,
    references = { invokable: Invokable -> })

fun task31(invokable: Invokable): Int {
    return invokable()()()().getNumberOfInvocations()
}
