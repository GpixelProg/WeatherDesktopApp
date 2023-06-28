package implementation

import data.database.NotificationDB
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import repository.DatabaseNotificationRepository
import utils.getCacheDir

class DatabaseNotificationImpl : DatabaseNotificationRepository {
    private val realm by lazy {
        val config = RealmConfiguration.Builder(
            schema = setOf(NotificationDB::class)
        )
            .name("notification.realm")
            .directory("${getCacheDir()}/database")
            .build()
        Realm.open(config)
    }

    override fun updateNotificationStatus(status: Boolean) {
        val item: NotificationDB? = realm.query<NotificationDB>("_id = 0").first().find()

        if (item != null) {
            realm.writeBlocking {
                findLatest(item)?.onNotification = status
            }
            println("Updated notification status")
        } else {
            realm.writeBlocking {
                copyToRealm(NotificationDB().apply {
                    onNotification = status
                })
            }
            println("Created notification status")
        }
    }

    override fun updateTemperature(temperature: Int) {
        val item: NotificationDB? = realm.query<NotificationDB>("_id = 0").first().find()

        if (item != null) {
            realm.writeBlocking {
                findLatest(item)?.temperature = temperature
            }
            println("Updated temperature")
        } else {
            realm.writeBlocking {
                copyToRealm(NotificationDB().apply {
                    this.temperature = temperature
                })
            }
            println("Created temperature")
        }
    }

    override fun getNotificationStatus(): Boolean {
        val item: NotificationDB? = realm.query<NotificationDB>("_id = 0").first().find()

        return item?.onNotification ?: false
    }

    override fun getTemperature(): Int {
        val item: NotificationDB? = realm.query<NotificationDB>("_id = 0").first().find()

        return item?.temperature ?: 20
    }
}