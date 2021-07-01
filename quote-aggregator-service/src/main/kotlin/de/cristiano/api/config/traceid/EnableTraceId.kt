package de.cristiano.api.config.traceid

import org.springframework.context.annotation.Import
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FILE

@Retention
@MustBeDocumented
@Target(CLASS, FILE)
@Import(TraceIdFilter::class)
annotation class EnableTraceId
