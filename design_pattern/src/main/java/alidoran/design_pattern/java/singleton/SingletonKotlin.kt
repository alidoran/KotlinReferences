package alidoran.design_pattern.java.singleton


class SingletonKotlin {

    companion object {
        @Volatile
        private var instance: SingletonKotlin? = null

        fun getInstance(): SingletonKotlin {
            return instance ?: synchronized(this){
                val instance = SingletonKotlin()
                Companion.instance = instance
                instance
            }
        }
    }
}