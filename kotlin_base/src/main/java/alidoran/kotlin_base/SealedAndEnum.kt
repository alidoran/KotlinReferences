package alidoran.kotlin_base

class SealedAndEnum {
    enum class FirstEnum(code: Int) {
        RIGHT(0),
        LEFT(1)
    }

    sealed class FirstSealed(code: Int) {
        class Right():FirstSealed(0)
        class Left():FirstSealed(1)
    }

    fun call() {
        callFirst(
            FirstEnum.RIGHT,
            FirstSealed.Right()
        )
    }

    private fun callFirst(
        fEnum: FirstEnum,
        fSealed: FirstSealed,
    ) {

    }
}