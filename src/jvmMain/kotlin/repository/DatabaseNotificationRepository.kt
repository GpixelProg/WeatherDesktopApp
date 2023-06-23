package repository

interface DatabaseNotificationRepository {
    fun updateNotificationStatus(status: Boolean)

    fun updateTemperature(temperature: Int)

    fun getNotificationStatus(): Boolean

    fun getTemperature(): Int
}