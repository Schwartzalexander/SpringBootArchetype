package de.springbootarchetype

import de.springbootarchetype.restControllers.PATH_REST_CONTROLLERS
import de.springbootarchetype.restControllers.PATH_REST_CONTROLLER_HELP
import de.springbootarchetype.restControllers.PATH_REST_CONTROLLER_STATUS
import de.springbootarchetype.util.SpringContextHolder.Companion.applicationContext
import de.springbootarchetype.util.StaticUtils
import de.springbootarchetype.util.StaticUtils.Companion.determineHost
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
@ComponentScan(basePackages = ["de.springbootarchetype"])
@EnableScheduling
open class Application : SpringBootServletInitializer() {

    companion object {
        val logger: Log = LogFactory.getLog(this.javaClass)

        /**
         * Prints some help messages about the REST interface.
         *
         * @param host hostname
         * @param port port
         */
        fun printHelpMessages(host: String?, port: String?) {
            val restPath = PATH_REST_CONTROLLERS
            val helpPath = PATH_REST_CONTROLLER_HELP
            val statusPath = PATH_REST_CONTROLLER_STATUS
            logger.info("Rest interface is running.")
            logger.info("Send GET request to http://$host:$port$restPath$statusPath to see the current status.")
            logger.info("Send GET request to http://$host:$port$restPath$helpPath to get further help.")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)

    val environment = applicationContext!!.environment
    val profile = StaticUtils.determineProfile(environment)
    val host = determineHost(environment)
    val port = environment.getProperty("local.server.port")

    Application.logger.info("Active profile: $profile")
    Application.logger.info("Spring Boot Archetype Backend application is running on http://$host:$port")
    Application.printHelpMessages(host, port)
}

