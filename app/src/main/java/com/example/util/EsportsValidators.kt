package com.example.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

sealed interface ValidationResult {
    data object Valid : ValidationResult
    data class Invalid(val message: String) : ValidationResult
}

object EsportsValidators {
    private val UID_REGEX = Regex("^\\d{8,12}$")
    private val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    private val YOUTUBE_ID_REGEX = Regex(
        "(?:youtube\\.com/(?:watch\\?(?:.*&)?v=|embed/|live/|shorts/)|youtu\\.be/)([A-Za-z0-9_-]{11})"
    )

    const val MAX_GUIDE_VIDEO_BYTES = 100L * 1024L * 1024L // 100 MB
    private val ALLOWED_VIDEO_MIMES = setOf("video/mp4", "video/webm")

    fun isValidFreeFireUid(uid: String): Boolean = UID_REGEX.matches(uid.trim())

    fun isValidPlayerName(name: String): Boolean {
        val trimmed = name.trim()
        return trimmed.length in 2..24
    }

    fun isValidEmail(email: String): Boolean = EMAIL_REGEX.matches(email.trim())

    fun validateUidList(rawUids: String, requiredCount: Int): ValidationResult {
        val tokens = rawUids.split(",", " ", "\n")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
        if (tokens.size < requiredCount) {
            return ValidationResult.Invalid("Enter at least $requiredCount valid Free Fire UIDs.")
        }
        val invalid = tokens.firstOrNull { !isValidFreeFireUid(it) }
        if (invalid != null) {
            return ValidationResult.Invalid("UID '$invalid' must be 8–12 digits.")
        }
        if (tokens.distinct().size != tokens.size) {
            return ValidationResult.Invalid("Duplicate teammate UIDs are not allowed.")
        }
        return ValidationResult.Valid
    }

    fun extractYouTubeVideoId(url: String): String? {
        val trimmed = url.trim()
        if (trimmed.length == 11 && trimmed.all { it.isLetterOrDigit() || it == '_' || it == '-' }) {
            return trimmed
        }
        return YOUTUBE_ID_REGEX.find(trimmed)?.groupValues?.getOrNull(1)
    }

    fun toSafeYouTubeEmbedUrl(url: String): String? {
        val videoId = extractYouTubeVideoId(url) ?: return null
        return "https://www.youtube-nocookie.com/embed/$videoId?rel=0&modestbranding=1"
    }

    fun validateVideoUpload(mimeType: String?, sizeBytes: Long?): ValidationResult {
        if (mimeType == null || mimeType.lowercase(Locale.ROOT) !in ALLOWED_VIDEO_MIMES) {
            return ValidationResult.Invalid("Only video/mp4 or video/webm guide videos are supported.")
        }
        if (sizeBytes != null && sizeBytes > MAX_GUIDE_VIDEO_BYTES) {
            return ValidationResult.Invalid("Guide video exceeds the 100 MB upload limit.")
        }
        return ValidationResult.Valid
    }

    fun formatDateTime(millis: Long): String {
        val sdf = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault())
        return sdf.format(Date(millis))
    }

    fun formatShortDate(millis: Long): String {
        val sdf = SimpleDateFormat("dd MMM", Locale.getDefault())
        return sdf.format(Date(millis))
    }

    fun maskUid(uid: String): String {
        return if (uid.length >= 6) {
            "${uid.take(3)}•••${uid.takeLast(3)}"
        } else {
            uid
        }
    }
}
