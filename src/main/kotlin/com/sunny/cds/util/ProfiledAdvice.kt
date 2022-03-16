package com.sunny.cds.util

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.stereotype.Component


@Aspect
@Component
@ConditionalOnExpression("\${perf.logging.enabled:true}")
class ProfiledAdvice {

    private val log by loggerDelegate

    @Around("@annotation(com.sunny.cds.util.Profiled)")
    @Throws(Throwable::class)
    fun executionTime(point: ProceedingJoinPoint): Any? {
        val startTime = System.currentTimeMillis()
        val `object` = point.proceed()
        val endTime = System.currentTimeMillis()
        log.info("${point.signature.declaringTypeName}:${point.signature.name} -- ${endTime - startTime}ms")
        return `object`
    }

}

// M00nrock3t4$#3