# Project Instructions

## Core Principles
- Generate ONLY the requested code in the required file directly by editing that file
- NO separate markdown documentation files (VERIFICATION_REPORT.md, etc.)
- Provide a brief summary in chat
- Use ONLY packages that exist in standard Android/Kotlin ecosystem

## Communication Style
- Keep responses concise
- Explain what you generated in 2-3 sentences maximum
- Ask clarifying questions if requirements are ambiguous

## Code Generation Rules

### DO:
- Use standard Android Jetpack libraries
- Verify package imports exist before using them
- Generate complete, runnable code
- Include necessary annotations (@Composable, @Entity, etc.)
- Add brief inline comments for complex logic only

### DO NOT:
- Create VERIFICATION_REPORT.md
- Create DOCUMENTATION_INDEX.md
- Create QUICK_START.md
- Create COMPLETE_FILE_TREE.md
- Create IMPLEMENTATION_SUMMARY.md
- Create any separate markdown documentation files
- Use experimental or non-existent packages
- Generate verbose explanations in separate files

## Allowed Dependencies (Verify these exist!)

### Jetpack Compose
```gradle
androidx.compose.ui:ui
androidx.compose.material3:material3
androidx.compose.ui:ui-tooling-preview
androidx.activity:activity-compose
androidx.navigation:navigation-compose
```

### Room Database
```gradle
androidx.room:room-runtime
androidx.room:room-ktx
androidx.room:room-compiler (kapt/ksp)
```

### Firebase
```gradle
com.google.firebase:firebase-firestore-ktx
com.google.firebase:firebase-common-ktx
```

### Coroutines
```gradle
org.jetbrains.kotlinx:kotlinx-coroutines-android
org.jetbrains.kotlinx:kotlinx-coroutines-core
```

### Other Essentials
```gradle
androidx.lifecycle:lifecycle-viewmodel-compose
androidx.lifecycle:lifecycle-runtime-ktx
androidx.core:core-ktx
```

## Package Import Guidelines

### ✅ CORRECT Imports:
```kotlin
// Compose
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier

// Room
import androidx.room.*
import androidx.room.Dao
import androidx.room.Entity

// ViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

// Coroutines
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
```

### ❌ AVOID (These don't exist or are outdated):
```kotlin
// Wrong - doesn't exist
import androidx.compose.desktop.*
import androidx.room.room.*

// Wrong - old Material Design 2
import androidx.compose.material.* // Use material3 instead
```