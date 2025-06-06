package com.example.di

class AppContainer : Container {
    private val dependencies : MutableMap<Class<*>, () -> Any> = mutableMapOf()

    @Suppress("UNCHECKED_CAST")
    override fun <T> register(type: Class<T>, factory: () -> T) {
        dependencies[type] = factory as () -> Any
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(type: Class<T>): T {
        val factory = dependencies[type]
            ?: throw IllegalArgumentException("${type.simpleName} not registered")

        return factory() as T
    }
}