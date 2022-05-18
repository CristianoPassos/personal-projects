package exercise

import exercise.service.StartupService


class CodingChallenge {

    private val startupService = StartupService()

    fun main(args: Array<String>) {
        startupService.retrieveLocalState()
    }

}


