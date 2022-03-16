//package com.sunny.cds.util
//
//import com.fasterxml.jackson.core.type.TypeReference
//import org.slf4j.Logger
//import org.slf4j.LoggerFactory
//import org.springframework.core.ParameterizedTypeReference
//import kotlin.properties.ReadOnlyProperty
//import kotlin.reflect.KProperty
//
//val typeRef: ReadOnlyProperty<Any, TypeReference<Any>> get() = TypeDelegate()
//
//private class TypeDelegate : ReadOnlyProperty<Any, TypeReference<Any>> {
//    private final inline fun <reified T> typeRef() = object : TypeReference<T>() {}
//
//    override fun getValue(thisRef: Any, property: KProperty<*>): TypeReference<Any> {
//        typeRef(thisRef::class)
//    }
//}
////private final inline fun <reified T> typeRef() = object : ParameterizedTypeReference<T>() {}
