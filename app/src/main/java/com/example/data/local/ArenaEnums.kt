package com.example.data.local

enum class UserRole(val label: String) {
    PLAYER("Player"),
    MODERATOR("Moderator"),
    SUPER_ADMIN("Super Admin")
}

enum class CurrencyType(val label: String, val unit: String) {
    DEMO_COIN("Demo Coins", "DC"),
    ORIGINAL_POINT("Original Points", "OP")
}

enum class TournamentCategory(val label: String, val requiredSquadSize: Int) {
    SOLO_1V1("Solo 1v1", 1),
    DUO("Duo", 2),
    SQUAD("Squad", 4),
    BERMUDA("Bermuda", 1),
    CUSTOM("Custom Room", 1)
}

enum class EntryType(val label: String) {
    FREE("Free Entry"),
    DEMO_COIN("Demo Coins")
}

enum class TournamentStatus(val label: String) {
    UPCOMING("Upcoming"),
    ONGOING("Live Now"),
    COMPLETED("Completed")
}

enum class ApprovalStatus(val label: String) {
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected")
}

enum class VideoSection(val label: String) {
    YOUTUBE_LIVE("YouTube Live"),
    OFFICIAL_GUIDE("Official Guide")
}

enum class ReportStatus(val label: String) {
    PENDING("Pending"),
    UNDER_REVIEW("Under Review"),
    ACTION_TAKEN("Action Taken"),
    DISMISSED("Dismissed")
}

enum class AppealStatus(val label: String) {
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected")
}
