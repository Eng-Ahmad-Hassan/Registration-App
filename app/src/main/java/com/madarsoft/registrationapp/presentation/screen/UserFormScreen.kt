package com.madarsoft.registrationapp.presentation.screen

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.madarsoft.registrationapp.R
import com.madarsoft.registrationapp.domain.model.Gender
import com.madarsoft.registrationapp.presentation.viewmodel.UserFormViewModel
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserFormScreen(
    onNavigateToList: () -> Unit,
    viewModel: UserFormViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.resetForm()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.user_registration),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    TextButton(
                        onClick = onNavigateToList,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Icon(Icons.Default.Menu, contentDescription = null)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(stringResource(R.string.view_users), fontWeight = FontWeight.SemiBold)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.surface,
                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Header with mandatory field info
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn() + expandVertically()
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f)
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    Icons.Default.Info,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(24.dp)
                                )
                                Text(
                                    text = stringResource(R.string.required_fields),
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            Text(
                                text = stringResource(R.string.form_requirements),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }

                // Form Fields
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Name Field
                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn() + slideInHorizontally(initialOffsetX = { -it })
                    ) {
                        FormField(
                            label = stringResource(R.string.full_name),
                            value = state.name,
                            onValueChange = viewModel::onNameChange,
                            leadingIcon = Icons.Default.Face,
                            error = state.nameError,
                            isValid = state.name.isNotBlank() && state.nameError == null,
                            isRequired = true,
                            placeholder = stringResource(R.string.full_name_placeholder)
                        )
                    }

                    // Age Field
                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn() + slideInHorizontally(
                            initialOffsetX = { -it },
                            animationSpec = tween(delayMillis = 100)
                        )
                    ) {
                        FormField(
                            label = stringResource(R.string.age),
                            value = state.age,
                            onValueChange = viewModel::onAgeChange,
                            leadingIcon = Icons.Default.DateRange,
                            error = state.ageError,
                            isValid = state.age.isNotBlank() && state.ageError == null,
                            isRequired = true,
                            placeholder = stringResource(R.string.age_placeholder),
                            keyboardType = KeyboardType.Number,
                            visualTransformation = NoPasteTransformation()
                        )
                    }

                    // Job Title Field
                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn() + slideInHorizontally(
                            initialOffsetX = { -it },
                            animationSpec = tween(delayMillis = 200)
                        )
                    ) {
                        FormField(
                            label = stringResource(R.string.job_title),
                            value = state.jobTitle,
                            onValueChange = viewModel::onJobTitleChange,
                            leadingIcon = Icons.Default.Info,
                            error = state.jobTitleError,
                            isValid = state.jobTitle.isNotBlank() && state.jobTitleError == null,
                            isRequired = true,
                            placeholder = stringResource(R.string.job_title_placeholder)
                        )
                    }
                }

                // Gender Selection
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn() + slideInHorizontally(
                        initialOffsetX = { -it },
                        animationSpec = tween(delayMillis = 300)
                    )
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.gender),
                                style = MaterialTheme.typography.titleMedium,
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Gender.entries.forEach { gender ->
                                    FilterChip(
                                        selected = state.gender == gender,
                                        onClick = { viewModel.onGenderChange(gender) },
                                        label = {
                                            Text(
                                                gender.name.lowercase().replaceFirstChar { it.uppercase() }
                                            )
                                        },
                                        leadingIcon = {
                                            if (state.gender == gender) {
                                                Icon(
                                                    Icons.Default.Person,
                                                    contentDescription = null,
                                                    modifier = Modifier.size(16.dp)
                                                )
                                            }
                                        },
                                        modifier = Modifier.weight(1f),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Submit Button
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn() + slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(delayMillis = 400)
                    )
                ) {
                    Button(
                        onClick = { viewModel.saveUser(onNavigateToList) },
                        enabled = !state.isLoading && state.isValid(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 8.dp,
                            pressedElevation = 4.dp
                        )
                    ) {
                        if (state.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = MaterialTheme.colorScheme.onPrimary,
                                strokeWidth = 2.dp
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(stringResource(R.string.saving), style = MaterialTheme.typography.labelLarge)
                        } else {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(stringResource(R.string.save_user), style = MaterialTheme.typography.labelLarge)
                        }
                    }
                }

                // Form validation summary
                AnimatedVisibility(
                    visible = !state.isValid() && (state.name.isNotBlank() || state.age.isNotBlank() || state.jobTitle.isNotBlank()),
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.8f)
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    Icons.Default.Warning,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    text = stringResource(R.string.please_fix_issues),
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                            if (!state.isNameValid() && state.name.isNotBlank()) {
                                Text(stringResource(R.string.full_name_issue, state.nameError ?: stringResource(R.string.invalid_format)), style = MaterialTheme.typography.bodySmall)
                            }
                            if (!state.isAgeValid() && state.age.isNotBlank()) {
                                Text(stringResource(R.string.age_issue, state.ageError ?: stringResource(R.string.invalid_age)), style = MaterialTheme.typography.bodySmall)
                            }
                            if (!state.isJobTitleValid() && state.jobTitle.isNotBlank()) {
                                Text(stringResource(R.string.job_title_issue, state.jobTitleError ?: stringResource(R.string.invalid_format)), style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: androidx.compose.ui.graphics.vector.ImageVector,
    error: String?,
    isValid: Boolean,
    isRequired: Boolean,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.titleMedium
                )
                if (isRequired) {
                    Text(
                        text = stringResource(R.string.required_asterisk),
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                label = { Text(placeholder) },
                leadingIcon = { 
                    Icon(
                        leadingIcon, 
                        contentDescription = label,
                        tint = if (error != null) MaterialTheme.colorScheme.error 
                              else if (isValid) MaterialTheme.colorScheme.primary
                              else MaterialTheme.colorScheme.onSurfaceVariant
                    ) 
                },
                trailingIcon = {
                    if (isValid) {
                        Icon(
                            Icons.Default.CheckCircle,
                            contentDescription = stringResource(R.string.valid),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                visualTransformation = visualTransformation,
                isError = error != null,
                supportingText = {
                    if (error != null) {
                        Text(
                            error, 
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    } else if (isValid) {
                        Text(
                            stringResource(R.string.valid_label, label.lowercase()),
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = if (isValid) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = if (error != null) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.outline
                )
            )
        }
    }
}

// Visual transformation to disable paste functionality for age field
class NoPasteTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(text, OffsetMapping.Identity)
    }
} 