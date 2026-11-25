package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface ArenaDao {
    // Users
    @Query("SELECT * FROM users ORDER BY originalPoints DESC, playerName ASC")
    fun observeAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    fun observeUser(userId: String): Flow<UserEntity?>

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    suspend fun getUserById(userId: String): UserEntity?

    @Query("SELECT * FROM users WHERE freeFireUid = :uid LIMIT 1")
    suspend fun getUserByUid(uid: String): UserEntity?

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Query("SELECT COUNT(*) FROM users")
    suspend fun getUserCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Query("UPDATE users SET demoCoins = demoCoins + :delta WHERE id = :userId")
    suspend fun adjustDemoCoins(userId: String, delta: Int)

    @Query("UPDATE users SET originalPoints = originalPoints + :delta WHERE id = :userId")
    suspend fun adjustOriginalPoints(userId: String, delta: Int)

    @Query("UPDATE users SET isBanned = :banned, banReason = :reason WHERE freeFireUid = :uid")
    suspend fun updateBanStatusByUid(uid: String, banned: Boolean, reason: String?)

    @Query("UPDATE users SET isBanned = :banned, banReason = :reason WHERE id = :userId")
    suspend fun updateBanStatusById(userId: String, banned: Boolean, reason: String?)

    @Query("UPDATE users SET avatarUri = :avatarUri WHERE id = :userId")
    suspend fun updateAvatarUri(userId: String, avatarUri: String)

    @Query("UPDATE users SET profileProofUri = :proofUri, isUidVerified = 1 WHERE id = :userId")
    suspend fun updateProfileVerification(userId: String, proofUri: String)

    @Query("UPDATE users SET role = :role WHERE id = :userId")
    suspend fun updateUserRole(userId: String, role: UserRole)

    // Point Transactions
    @Query("SELECT * FROM point_transactions WHERE userId = :userId ORDER BY createdAt DESC")
    fun observeTransactionsForUser(userId: String): Flow<List<PointTransactionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(tx: PointTransactionEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTransactions(txs: List<PointTransactionEntity>)

    // Tournaments
    @Query("SELECT * FROM tournaments ORDER BY scheduleTime ASC")
    fun observeTournaments(): Flow<List<TournamentEntity>>

    @Query("SELECT * FROM tournaments WHERE id = :id LIMIT 1")
    fun observeTournament(id: String): Flow<TournamentEntity?>

    @Query("SELECT * FROM tournaments WHERE id = :id LIMIT 1")
    suspend fun getTournamentById(id: String): TournamentEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTournament(tournament: TournamentEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTournaments(tournaments: List<TournamentEntity>)

    @Query("UPDATE tournaments SET status = :status WHERE id = :tournamentId")
    suspend fun updateTournamentStatus(tournamentId: String, status: TournamentStatus)

    // Teams
    @Query("SELECT * FROM teams WHERE captainId = :captainId ORDER BY createdAt DESC")
    fun observeTeamsForCaptain(captainId: String): Flow<List<TeamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(team: TeamEntity)

    // Registrations
    @Query("SELECT * FROM tournament_registrations ORDER BY createdAt DESC")
    fun observeAllRegistrations(): Flow<List<TournamentRegistrationEntity>>

    @Query("SELECT * FROM tournament_registrations WHERE tournamentId = :tournamentId ORDER BY createdAt ASC")
    fun observeRegistrationsForTournament(tournamentId: String): Flow<List<TournamentRegistrationEntity>>

    @Query("SELECT * FROM tournament_registrations WHERE userId = :userId ORDER BY createdAt DESC")
    fun observeRegistrationsForUser(userId: String): Flow<List<TournamentRegistrationEntity>>

    @Query("SELECT * FROM tournament_registrations WHERE tournamentId = :tournamentId AND userId = :userId LIMIT 1")
    suspend fun getRegistrationForUser(tournamentId: String, userId: String): TournamentRegistrationEntity?

    @Query("SELECT * FROM tournament_registrations WHERE id = :registrationId LIMIT 1")
    suspend fun getRegistrationById(registrationId: String): TournamentRegistrationEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRegistration(registration: TournamentRegistrationEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRegistrations(registrations: List<TournamentRegistrationEntity>)

    @Query("UPDATE tournament_registrations SET approvalStatus = :status WHERE id = :registrationId")
    suspend fun updateRegistrationStatus(registrationId: String, status: ApprovalStatus)

    // Match Rooms
    @Query("SELECT * FROM match_rooms WHERE tournamentId = :tournamentId LIMIT 1")
    fun observeMatchRoom(tournamentId: String): Flow<MatchRoomEntity?>

    @Query("SELECT * FROM match_rooms")
    fun observeAllMatchRooms(): Flow<List<MatchRoomEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateMatchRoom(room: MatchRoomEntity)

    // Match Results
    @Query("SELECT * FROM match_results ORDER BY verifiedAt DESC")
    fun observeAllMatchResults(): Flow<List<MatchResultEntity>>

    @Query("SELECT * FROM match_results WHERE tournamentId = :tournamentId ORDER BY placement ASC, kills DESC")
    fun observeResultsForTournament(tournamentId: String): Flow<List<MatchResultEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatchResult(result: MatchResultEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMatchResults(results: List<MatchResultEntity>)

    // Videos
    @Query("SELECT * FROM videos ORDER BY createdAt DESC")
    fun observeVideos(): Flow<List<VideoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(video: VideoEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVideos(videos: List<VideoEntity>)

    // Announcements
    @Query("SELECT * FROM announcements ORDER BY isPinned DESC, createdAt DESC")
    fun observeAnnouncements(): Flow<List<AnnouncementEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnnouncement(announcement: AnnouncementEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAnnouncements(announcements: List<AnnouncementEntity>)

    @Query("DELETE FROM announcements WHERE id = :id")
    suspend fun deleteAnnouncement(id: String)

    // Reports & Appeals
    @Query("SELECT * FROM reports ORDER BY createdAt DESC")
    fun observeAllReports(): Flow<List<ReportEntity>>

    @Query("SELECT * FROM reports WHERE reporterId = :userId ORDER BY createdAt DESC")
    fun observeReportsForUser(userId: String): Flow<List<ReportEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReport(report: ReportEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertReports(reports: List<ReportEntity>)

    @Query("UPDATE reports SET status = :status, adminNotes = :notes WHERE id = :reportId")
    suspend fun updateReportStatus(reportId: String, status: ReportStatus, notes: String?)

    @Query("SELECT * FROM appeals ORDER BY createdAt DESC")
    fun observeAllAppeals(): Flow<List<AppealEntity>>

    @Query("SELECT * FROM appeals WHERE userId = :userId ORDER BY createdAt DESC")
    fun observeAppealsForUser(userId: String): Flow<List<AppealEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppeal(appeal: AppealEntity)

    @Query("UPDATE appeals SET status = :status, adminNotes = :notes WHERE id = :appealId")
    suspend fun updateAppealStatus(appealId: String, status: AppealStatus, notes: String?)

    // Atomic Transactional Workflows
    @Transaction
    suspend fun createUserWithSignupBonus(user: UserEntity) {
        insertUser(user)
        insertTransaction(
            PointTransactionEntity(
                id = UUID.randomUUID().toString(),
                userId = user.id,
                currencyType = CurrencyType.DEMO_COIN,
                amount = user.demoCoins,
                reason = "SIGNUP_BONUS",
                referenceId = null
            )
        )
    }

    @Transaction
    suspend fun registerForTournamentAtomic(
        tournament: TournamentEntity,
        user: UserEntity,
        team: TeamEntity?,
        memberUids: String
    ) {
        if (team != null) {
            insertTeam(team)
        }
        if (tournament.entryType == EntryType.DEMO_COIN && tournament.entryFeeCoins > 0) {
            adjustDemoCoins(user.id, -tournament.entryFeeCoins)
            insertTransaction(
                PointTransactionEntity(
                    id = UUID.randomUUID().toString(),
                    userId = user.id,
                    currencyType = CurrencyType.DEMO_COIN,
                    amount = -tournament.entryFeeCoins,
                    reason = "PRACTICE_ENTRY",
                    referenceId = tournament.id
                )
            )
        }
        insertRegistration(
            TournamentRegistrationEntity(
                id = UUID.randomUUID().toString(),
                tournamentId = tournament.id,
                userId = user.id,
                playerName = user.playerName,
                playerUid = user.freeFireUid,
                teamId = team?.id,
                teamName = team?.teamName,
                memberUids = memberUids,
                approvalStatus = ApprovalStatus.PENDING
            )
        )
    }

    @Transaction
    suspend fun reviewRegistrationAtomic(
        registrationId: String,
        newStatus: ApprovalStatus
    ) {
        val reg = getRegistrationById(registrationId) ?: return
        val previous = reg.approvalStatus
        updateRegistrationStatus(registrationId, newStatus)
        if (previous != ApprovalStatus.REJECTED && newStatus == ApprovalStatus.REJECTED) {
            val tournament = getTournamentById(reg.tournamentId)
            if (tournament != null && tournament.entryType == EntryType.DEMO_COIN && tournament.entryFeeCoins > 0) {
                adjustDemoCoins(reg.userId, tournament.entryFeeCoins)
                insertTransaction(
                    PointTransactionEntity(
                        id = UUID.randomUUID().toString(),
                        userId = reg.userId,
                        currencyType = CurrencyType.DEMO_COIN,
                        amount = tournament.entryFeeCoins,
                        reason = "ENTRY_REFUND",
                        referenceId = tournament.id
                    )
                )
            }
        }
    }

    @Transaction
    suspend fun verifyMatchResultAtomic(
        tournamentId: String,
        targetUser: UserEntity,
        kills: Int,
        placement: Int,
        isWinner: Boolean,
        adminId: String
    ) {
        val killPoints = kills * 5
        val placementPoints = when (placement) {
            1 -> 50
            2 -> 30
            3 -> 20
            in 4..6 -> 10
            else -> 0
        }
        val totalOriginalPoints = killPoints + placementPoints
        insertMatchResult(
            MatchResultEntity(
                id = UUID.randomUUID().toString(),
                tournamentId = tournamentId,
                userId = targetUser.id,
                playerName = targetUser.playerName,
                freeFireUid = targetUser.freeFireUid,
                kills = kills,
                placement = placement,
                killPoints = killPoints,
                matchPoints = totalOriginalPoints,
                isWinner = isWinner,
                verifiedByAdmin = adminId
            )
        )
        if (totalOriginalPoints > 0) {
            adjustOriginalPoints(targetUser.id, totalOriginalPoints)
            insertTransaction(
                PointTransactionEntity(
                    id = UUID.randomUUID().toString(),
                    userId = targetUser.id,
                    currencyType = CurrencyType.ORIGINAL_POINT,
                    amount = totalOriginalPoints,
                    reason = if (isWinner) "TOURNAMENT_WIN" else "MATCH_KILL_PLACEMENT",
                    referenceId = tournamentId
                )
            )
        }
    }
}
