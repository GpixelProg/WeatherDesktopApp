package data.database

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class NotificationDB : RealmObject {
    @PrimaryKey
    var _id: Int = 0
    var onNotification: Boolean = false
    var temperature: Int = 0
}