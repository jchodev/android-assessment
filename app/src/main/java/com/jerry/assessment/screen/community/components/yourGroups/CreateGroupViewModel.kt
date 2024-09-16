package com.jerry.assessment.screen.community.components.yourGroups

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateGroupViewModel:  ViewModel() {

    private val _uiState = MutableStateFlow<CreateGroupUIState>(CreateGroupUIState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchContact()
    }


    fun onContactSelect(contact: CreateGroupContact) {
        val updatedContacts = _uiState.value.contacts.map {
            if (it.id == contact.id) {
                it.copy(selected = !it.selected)
            } else {
                it
            }
        }
        updateUIState(
            uiState.value.copy(
                contacts = updatedContacts
            )
        )
    }

    private fun updateUIState(value : CreateGroupUIState){
        var nextButtonEnabled = true
        if (value.contacts.filter { it.selected }.size ==0){
            nextButtonEnabled = false
        } else if (value.selectedPageIndex == 1){
            if (value.groupName.isBlank()){
                nextButtonEnabled = false
            }
        }
        _uiState.value = value.copy(
            nextButtonEnabled = nextButtonEnabled
        )
    }

    fun onNextClick(){
        if (uiState.value.selectedPageIndex == 0){
            updateUIState (
                value = uiState.value.copy(
                    selectedPageIndex = uiState.value.selectedPageIndex + 1
                )
            )
        } else {
            //http post to create group
            updateUIState (
                value = uiState.value.copy(
                    selectedPageIndex = uiState.value.selectedPageIndex + 1
                )
            )
        }
    }

    fun onBackButtonClick(){
        if (uiState.value.selectedPageIndex == 1){
            updateUIState (
                value = uiState.value.copy(
                    selectedPageIndex = uiState.value.selectedPageIndex - 1
                )
            )
        } else {
            //http post to create group

        }
    }

    fun fetchContact(){
        updateUIState(
            value = _uiState.value.copy(
                isLoading = true
            )
        )

        val contacts: MutableList<CreateGroupContact> = mutableListOf()
        for (i in 1..100) {
            contacts.add(CreateGroupContact(
                id = i,
                name = "name ${i}",
                imageUrl = "https://dummyimage.com/100x100/6699cc/000",
            ))
        }
        updateUIState(
            value = _uiState.value.copy(
                contacts = contacts,
            )
        )

        updateUIState(
            value = _uiState.value.copy(
                isLoading = false
            )
        )
    }

    fun onGroupNameUpdate(value: String){
        updateUIState (
            value = uiState.value.copy(
                groupNameError = null,
                groupName = value
            )
        )
    }

    fun onAboutGroupUpdate(value: String){
        updateUIState (
            value = uiState.value.copy(
                aboutGroupError = null,
                aboutGroup = value
            )
        )
    }

    fun onPermanentGroupChange(value: Boolean){
        updateUIState (
            value = uiState.value.copy(
                permanentGroup = value
            )
        )
    }

    fun onSimplifiedPaymentChange(value: Boolean){
        updateUIState (
            value = uiState.value.copy(
                simplifiedPayment = value
            )
        )
    }
}

data class CreateGroupUIState(
    val isLoading: Boolean = false,
    val contacts: List<CreateGroupContact> = emptyList(),
    val selectedPageIndex: Int = 0,

    val groupName: String = "",
    val groupNameError: String? = null,

    val aboutGroup: String = "",
    val aboutGroupError: String? = null,

    val permanentGroup: Boolean = true,
    val simplifiedPayment: Boolean = true,

    val nextButtonEnabled: Boolean = false
)

data class CreateGroupContact(
    val id: Int = 0,
    val name: String = "",
    val imageUrl: String = "",
    val selected: Boolean = false,
)

