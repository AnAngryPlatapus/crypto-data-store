package com.sunny.cds.config

import com.sunny.cds.model.book.Book
import com.sunny.cds.service.job.BookDataJob
import org.quartz.*
import org.quartz.SimpleScheduleBuilder.simpleSchedule
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service

@Service
class JobConfig {

    private val bookKey = JobKey.jobKey(BookDataJob::class.qualifiedName, "${Book::class.simpleName}_GROUP")

    @Bean
    fun geminiBookDetail(): JobDetail {
        return JobBuilder.newJob(BookDataJob::class.java)
            .withIdentity(bookKey)
            .storeDurably()
            .build()
    }

    @Bean
    fun geminiBookTrigger(): Trigger {
        return TriggerBuilder
            .newTrigger()
            .forJob(bookKey)
            .withSchedule(simpleSchedule())
            .build()
    }

}