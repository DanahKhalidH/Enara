package com.example.enarah.data

object SampleData {
    val schools = listOf(
        School(
            id = "al_noor",
            initials = "AN",
            name = "Al-Noor",
            city = "Dammam",
            type = "Private",
            rating = "4.5",
            reviewCount = 12,
            levels = listOf("Level 1", "Level 2"),
            tagline = "Inclusive school with autism support",
            services = listOf("Speech Therapy", "Occupational Therapy", "Behavioral Therapy", "Sensory Therapy"),
            specialists = listOf("Speech Therapist", "Occupational Therapist", "Behavioral Specialist"),
            phone = "050 000 0000",
            whatsapp = "+966 50 000 0000",
            email = "info@alnoor-school.example.com",
            isFavorite = true
        ),
        School(
            id = "al_amal",
            initials = "AA",
            name = "Al-Amal School",
            city = "Dammam",
            type = "Public",
            rating = "4.2",
            reviewCount = 9,
            levels = listOf("Level 1", "Level 2"),
            tagline = "Public inclusive program with dedicated support",
            services = listOf("Speech Therapy", "Behavioral Therapy"),
            specialists = listOf("Speech Therapist", "Behavioral Specialist"),
            phone = "050 111 2222",
            whatsapp = "+966 50 111 2222",
            email = "contact@alamal.example.com",
            isFavorite = true
        ),
        School(
            id = "dammam_academy",
            initials = "DA",
            name = "Dammam Academy",
            city = "Dammam",
            type = "Private",
            rating = "4.0",
            reviewCount = 7,
            levels = listOf("Level 2"),
            tagline = "Structured daily routines and communication support",
            services = listOf("Occupational Therapy", "Sensory Therapy"),
            specialists = listOf("Occupational Therapist", "Behavioral Specialist"),
            phone = "050 333 4444",
            whatsapp = "+966 50 333 4444",
            email = "hello@dammamacademy.example.com",
            isFavorite = true
        ),
        School(
            id = "bright_future",
            initials = "BF",
            name = "Bright Future School",
            city = "Dammam",
            type = "Private",
            rating = "4.1",
            reviewCount = 6,
            levels = listOf("Level 2"),
            tagline = "Individualized learning plans for children",
            services = listOf("Speech Therapy", "Sensory Therapy"),
            specialists = listOf("Speech Therapist"),
            phone = "050 555 6666",
            whatsapp = "+966 50 555 6666",
            email = "info@brightfuture.example.com",
            isFavorite = false
        )
    )

    val reviews = listOf(
        ParentReview(
            id = "r1",
            schoolId = "al_noor",
            authorName = "Hassah Mohammad",
            childLevelLabel = "Parent of a level 1 child",
            comment = "Al-Noor School has caring and patient teachers who create a safe and supportive environment for children with autism. I appreciate their dedication and support."
        ),
        ParentReview(
            id = "r2",
            schoolId = "al_noor",
            authorName = "Khalid Salah",
            childLevelLabel = "Parent of a level 2 child",
            comment = "I am happy with Al-Noor School. The staff are kind and supportive, and I have noticed positive progress in my child's communication and social skills."
        )
    )

    val autismLevels = listOf(
        AutismLevelInfo(
            level = "Level 1",
            subtitle = "Requires support",
            description = "Needs some support with social communication and flexibility."
        ),
        AutismLevelInfo(
            level = "Level 2",
            subtitle = "Requires substantial support",
            description = "Needs substantial support with communication and daily routines."
        ),
        AutismLevelInfo(
            level = "Level 3",
            subtitle = "Requires very substantial support",
            description = "Needs very substantial support in most areas of daily life."
        )
    )

    fun getSchoolById(schoolId: String): School {
        return schools.find { it.id == schoolId } ?: schools.first()
    }
}