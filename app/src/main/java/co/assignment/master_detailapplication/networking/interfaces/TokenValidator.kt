package co.assignment.master_detailapplication.networking.interfaces

internal interface TokenValidator {
    var tokenRefreshInProgress: Boolean
    fun invalidate()
}