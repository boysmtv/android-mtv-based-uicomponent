# MTV Base UI Components

Multi-repo UI component library for Android (Compose + XML), built with Kotlin and Theme system.

## Overview

This repository contains reusable UI components for Android projects. All components are compatible with:

- **Jetpack Compose**
- **XML / View wrapper**
- **Shared Theme (`AppTheme`)**
- **Multi-repo modular architecture**

---

## Module Structure

component/
├── core-ui/ # Base composables, colors, dimens, helpers
├── theme-ui/ # AppTheme, AppColors, AppDimens
├── component-input/ # Text input component
├── component-dialog/ # Dialog component
├── component-bottom-sheet/ # BottomSheet component
└── component-button/ # Optional custom button component


---

## Dependencies Diagram

      ┌────────────┐
      │  core-ui   │
      │(colors,    │
      │ dimens,    │
      │  helpers)  │
      └─────┬──────┘
            │
            ▼
      ┌────────────┐
      │ theme-ui   │
      │(AppTheme,  │
      │ AppColors, │
      │ AppDimens) │
      └─────┬──────┘
            │

┌──────────────┼──────────────┐
▼              ▼              ▼
component-input component-dialog component-bottom-sheet
(InputTextField) (AppDialog) (AppBottomSheet)
│ │ │
└───────┬────────┴─────────┬───────┘
        │                  │
XML wrapper Compose version
(InputTextView) (AppTextField)
(AppDialogView) (AppDialog)
(AppBottomSheetView) (AppBottomSheet)

---

## Features

- **Compose + XML hybrid support**
- **Reusable components** for input, dialog, bottom sheet, button
- **Theme system** (`AppTheme`) for colors, typography, dimens
- **ViewBinding support** for XML wrapper
- **Multi-repo friendly**: each component is a separate module

---

## Quick Start

### Add Module Dependency

```kotlin
dependencies {
    implementation(project(":core-ui"))
    implementation(project(":theme-ui"))
    implementation(project(":component-input"))
    implementation(project(":component-dialog"))
    implementation(project(":component-bottom-sheet"))
}

AppTheme {
    Column {
        AppTextField(
            value = text,
            onValueChange = { text = it },
            label = "Email"
        )
        AppDialog(
            state = dialogState,
            onDismiss = { dialogState = DialogState.Hidden },
            onConfirm = { dialogState = DialogState.Hidden }
        )
        AppBottomSheet(
            state = bottomSheetState,
            onDismiss = { bottomSheetState = BottomSheetState.Hidden }
        )
    }
}

<com.mtv.based.uicomponent.component.input.view.InputTextView
    android:id="@+id/xmlInput"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:label="Email"/>

<com.mtv.based.uicomponent.component.dialog.view.AppDialogView
    android:id="@+id/xmlDialog"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:title="Warning"
    app:message="This is an XML dialog"/>

<com.mtv.based.uicomponent.component.bottomsheet.view.AppBottomSheetView
    android:id="@+id/xmlBottomSheet"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:title="XML BottomSheet"
    app:message="This is an XML BottomSheet"/>

// Show / Hide XML components
binding.xmlDialog.showDialog("Title", "Message")
binding.xmlBottomSheet.showBottomSheet("Title", "Message")
binding.xmlBottomSheet.hideBottomSheet()

---
