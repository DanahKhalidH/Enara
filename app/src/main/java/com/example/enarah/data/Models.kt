package com.example.enarah.data

data class School(
    val id: String,
    val initials: String,
    val name: String,
    val city: String,
    val type: String,
    val rating: String,
    val reviewCount: Int,
    val levels: List<String>,
    val tagline: String,
    val services: List<String>,
    val specialists: List<String>,
    val phone: String,
    val whatsapp: String,
    val email: String,
    val isFavorite: Boolean = false
)

data class ParentReview(
    val id: String,
    val schoolId: String,
    val authorName: String,
    val childLevelLabel: String,
    val comment: String
)

data class AutismLevelInfo(
    val level: String,
    val subtitle: String,
    val description: String
)
