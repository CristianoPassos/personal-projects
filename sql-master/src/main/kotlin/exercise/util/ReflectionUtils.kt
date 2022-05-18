package exercise.util

import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties

object ReflectionUtils {

    fun <T : Any> getField(fieldName: String, clazz: KClass<T>): KProperty1<T, *> = clazz.declaredMemberProperties
        .firstOrNull { it.name.equals(fieldName, true) }
        ?: throw NoSuchFieldError("Filed $fieldName does not exist in ${clazz.simpleName}")
}
