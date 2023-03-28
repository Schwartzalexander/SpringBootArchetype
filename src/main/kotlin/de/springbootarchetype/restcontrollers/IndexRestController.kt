package de.springbootarchetype.restcontrollers

import de.springbootarchetype.util.StaticUtils
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(PATH_REST_CONTROLLERS)
class IndexRestController(
    private var environment: Environment,
) {
    private val logger: Log = LogFactory.getLog(this.javaClass)


    @GetMapping(PATH_REST_CONTROLLER_HELP)
    @Throws(Exception::class)
    fun help(): ResponseEntity<String> {

        val host = StaticUtils.determineHost(environment)
        val port = environment.getProperty("server.port")

        val sb = StringBuilder()
        sb.append("The following REST requests are available:\n")
        sb.append("GET: $host:$port$PATH_REST_CONTROLLERS$PATH_REST_CONTROLLER_HELP - Displays this help.\n")
        sb.append("GET: $host:$port$PATH_REST_CONTROLLERS$PATH_REST_CONTROLLER_STATUS - Displays the status of the Spring Boot Archetype Backend application.\n")
        sb.append("GET: $host:$port/actuator/health - Health check for the application. Returns the status 200 and a simple JSON object ({\"status\": \"UP\"}).\n.\n")

        return ResponseEntity(sb.toString(), HttpStatus.ACCEPTED)
    }

}