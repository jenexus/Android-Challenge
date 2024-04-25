package android.challenge.model

data class LevelModel(
    val levels: List<Level>?
)

data class Level(
    val activities: List<Activity>?,
    val description: String?,
    val level: String?,
    val state: String?,
    val title: String?
)

data class Activity(
    val challengeId: String?,
    val description: String?,
    val descriptionB: Any?,
    val icon: Icon?,
    val id: String?,
    val lockedIcon: LockedIcon?,
    val state: String?,
    val title: String?,
    val titleB: String?,
    val type: String?
)

data class Icon(
    val description: String?,
    val `file`: File?,
    val title: String?
)

data class LockedIcon(
    val description: String?,
    val `file`: File?,
    val title: String?
)

data class File(
    val contentType: String?,
    val details: Details?,
    val fileName: String?,
    val url: String?
)

data class Details(
    val size: Int?
)