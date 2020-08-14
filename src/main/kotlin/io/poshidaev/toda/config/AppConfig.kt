package io.poshidaev.toda.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.scheduler.Scheduler
import reactor.core.scheduler.Schedulers
import java.util.concurrent.Executors

@Configuration
class AppConfig {

    @Value("\${spring.datasource.hikari.maximum-pool-size}")
    private var connectionPoolSize = 0

    @Bean
    fun jdbcScheduler(): Scheduler? = Schedulers.fromExecutor(Executors.newFixedThreadPool(connectionPoolSize))


}