package com.example.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [
        Index(value = ["freeFireUid"], unique = true),
        Index(value = ["email"], unique = true)
    ]
)
data class UserEntity(
    @PrimaryKey val id: String,
    val playerName: String,
    val freeFireUid: String,
    val email: String,
    val avatarUri: String? = null,
    val role: UserRole = UserRole.PLAYER,
    val demoCoins: Int = 20,
    val originalPoints: Int = 0,
    val isBanned: Boolean = false,
    val banReason: String? = null,
    val profileProofUri: String? = null,
    val isUidVerified: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "point_transactions",
    indices = [Index(value = ["userId"])]
)
data class PointTransactionEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val currencyType: CurrencyType,
    val amount: Int,
    val reason: String,
    val referenceId: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "tournaments")
data class TournamentEntity(
    @PrimaryKey val id: String,
    val title: String,
    val category: TournamentCategory,
    val entryType: EntryType,
    val entryFeeCoins: Int = 0,
    val rewardOriginalPoints: Int = 100,
    val maxSlots: Int,
    val status: TournamentStatus = TournamentStatus.UPCOMING,
    val scheduleTime: Long,
    val youtubeLiveUrl: String? = null,
    val bannerKey: String = "bermuda",
    val rulesSummary: String = "Fair play enforced. Emulators/hacks prohibited. Only verified UIDs allowed.",
    val createdBy: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "teams",
    indices = [Index(value = ["captainId"])]
)
data class TeamEntity(
    @PrimaryKey val id: String,
    val teamName: String,
    val captainId: String,
    val memberUids: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "tournament_registrations",
    indices = [
        Index(value = ["tournamentId"]),
        Index(value = ["userId"])
    ]
)
data class TournamentRegistrationEntity(
    @PrimaryKey val id: String,
    val tournamentId: String,
    val userId: String,
    val playerName: String,
    val playerUid: String,
    val teamId: String? = null,
    val teamName: String? = null,
    val memberUids: String,
    val approvalStatus: ApprovalStatus = ApprovalStatus.PENDING,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "match_rooms",
    indices = [Index(value = ["tournamentId"], unique = true)]
)
data class MatchRoomEntity(
    @PrimaryKey val id: String,
    val tournamentId: String,
    val roomId: String,
    val roomPassword: String,
    val releaseTime: Long,
    val isPublished: Boolean = false
)

@Entity(
    tableName = "match_results",
    indices = [
        Index(value = ["tournamentId"]),
        Index(value = ["userId"])
    ]
)
data class MatchResultEntity(
    @PrimaryKey val id: String,
    val tournamentId: String,
    val userId: String,
    val playerName: String,
    val freeFireUid: String,
    val kills: Int,
    val placement: Int,
    val killPoints: Int,
    val matchPoints: Int,
    val isWinner: Boolean,
    val verifiedByAdmin: String,
    val verifiedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "videos")
data class VideoEntity(
    @PrimaryKey val id: String,
    val title: String,
    val section: VideoSection,
    val videoUrl: String,
    val mimeType: String? = null,
    val uploadedBy: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "announcements")
data class AnnouncementEntity(
    @PrimaryKey val id: String,
    val title: String,
    val content: String,
    val isPinned: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "reports",
    indices = [Index(value = ["reporterId"])]
)
data class ReportEntity(
    @PrimaryKey val id: String,
    val reporterId: String,
    val reportedUid: String,
    val tournamentId: String? = null,
    val reason: String,
    val proofUri: String? = null,
    val status: ReportStatus = ReportStatus.PENDING,
    val adminNotes: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "appeals",
    indices = [Index(value = ["userId"])]
)
data class AppealEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val reportId: String? = null,
    val message: String,
    val proofUri: String? = null,
    val status: AppealStatus = AppealStatus.PENDING,
    val adminNotes: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
