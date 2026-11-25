package com.example.data.local

import androidx.room.TypeConverter

class ArenaConverters {
    @TypeConverter fun fromUserRole(v: UserRole): String = v.name
    @TypeConverter fun toUserRole(v: String): UserRole =
        UserRole.entries.firstOrNull { it.name == v } ?: UserRole.PLAYER

    @TypeConverter fun fromCurrencyType(v: CurrencyType): String = v.name
    @TypeConverter fun toCurrencyType(v: String): CurrencyType =
        CurrencyType.entries.firstOrNull { it.name == v } ?: CurrencyType.DEMO_COIN

    @TypeConverter fun fromTournamentCategory(v: TournamentCategory): String = v.name
    @TypeConverter fun toTournamentCategory(v: String): TournamentCategory =
        TournamentCategory.entries.firstOrNull { it.name == v } ?: TournamentCategory.SOLO_1V1

    @TypeConverter fun fromEntryType(v: EntryType): String = v.name
    @TypeConverter fun toEntryType(v: String): EntryType =
        EntryType.entries.firstOrNull { it.name == v } ?: EntryType.FREE

    @TypeConverter fun fromTournamentStatus(v: TournamentStatus): String = v.name
    @TypeConverter fun toTournamentStatus(v: String): TournamentStatus =
        TournamentStatus.entries.firstOrNull { it.name == v } ?: TournamentStatus.UPCOMING

    @TypeConverter fun fromApprovalStatus(v: ApprovalStatus): String = v.name
    @TypeConverter fun toApprovalStatus(v: String): ApprovalStatus =
        ApprovalStatus.entries.firstOrNull { it.name == v } ?: ApprovalStatus.PENDING

    @TypeConverter fun fromVideoSection(v: VideoSection): String = v.name
    @TypeConverter fun toVideoSection(v: String): VideoSection =
        VideoSection.entries.firstOrNull { it.name == v } ?: VideoSection.YOUTUBE_LIVE

    @TypeConverter fun fromReportStatus(v: ReportStatus): String = v.name
    @TypeConverter fun toReportStatus(v: String): ReportStatus =
        ReportStatus.entries.firstOrNull { it.name == v } ?: ReportStatus.PENDING

    @TypeConverter fun fromAppealStatus(v: AppealStatus): String = v.name
    @TypeConverter fun toAppealStatus(v: String): AppealStatus =
        AppealStatus.entries.firstOrNull { it.name == v } ?: AppealStatus.PENDING
}
