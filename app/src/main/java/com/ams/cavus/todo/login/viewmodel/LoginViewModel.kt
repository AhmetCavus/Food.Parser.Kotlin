package com.ams.cavus.todo.login.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Intent
import com.ams.cavus.todo.client.GoogleService
import com.ams.cavus.todo.helper.QueryBuilder
import com.ams.cavus.todo.list.service.IdentityService
import com.ams.cavus.todo.login.model.LoginDataModel
import com.ams.cavus.todo.login.service.AzureAuthService
import com.example.amstodo.util.SingleLiveEvent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.microsoft.windowsazure.mobileservices.authentication.MobileServiceAuthenticationProvider
import javax.inject.Inject

class LoginViewModel (private val app: Application) : AndroidViewModel(app), LifecycleObserver{

    companion object {
        const val GOOGLE_CALLBACK = 101
        const val MICROSOFT_CALLBACK = 102
        const val AD_CALLBACK = 103
        const val TWITTER_CALLBACK = 104
    }

    @Inject
    lateinit var authService: AzureAuthService

    @Inject
    lateinit var identityService: IdentityService

    @Inject
    lateinit var googleService: GoogleService

    @Inject
    lateinit var model: LoginDataModel

    var currentCallbackId = 0

    val startActivityForResultEvent = SingleLiveEvent<Intent>()
    val showNextEvent = SingleLiveEvent<String>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        identityService.fetch(null) {identities ->
            relogin()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
    }

    private fun relogin() {
        authService.initialize()
        if(!authService.isLoggedIn) return
        if(!authService.hasIdentifier) return
        showNext()
    }

    fun handleGoogleResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        // The Task returned from this call is always completed, no need to attach
        // a listener.
        val completedTask = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = completedTask.getResult(ApiException::class.java)
            googleService.loadCredentials(app, account) {
                credentials ->
                    authService.login(
                            "Google",
                            credentials,
                            { _ -> showNext()},
                            { exception -> updateUI(exception)}
                    )
            }
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            updateUI(e)
        }
    }

    fun handleTwitterResult(requestCode: Int, resultCode: Int, data: Intent?) {
        TODO("Handle Twitter result")
    }

    fun handleMicrosoftResult(requestCode: Int, resultCode: Int, data: Intent?) {
        TODO("Handle Microsoft result")
    }

    fun handleAdResult(requestCode: Int, resultCode: Int, data: Intent?) {
        TODO("Handle Active Directory result")
    }

    fun handleResult(requestCode: Int, resultCode: Int, data: Intent) {
        val result = authService.onActivityResult(data)
        if(result.isLoggedIn) {
            val account = identityService.itemsCache?.find { identity -> identity.userId == authService.currentCredentials.userId }
            if(account == null) {
                showEditUsername()
            } else {
                authService.currentCredentials.userName = account.name
                hideEditUsername()
                showNext()
            }
        } else {
            updateUI(Exception(result.errorMessage))
        }
    }

    fun signIn(provider: MobileServiceAuthenticationProvider) {
        authService.login(provider.name, currentCallbackId)
    }

    fun signOut() {
        authService.logout(
            { _ -> showNext()},
            { exception -> updateUI(exception) }
        )
    }

    private fun showNext() {
        showNextEvent.value = "category"
    }

    private fun showEditUsername() {
        model.isSignInVisible = false
    }

    private fun hideEditUsername() {
        model.isSignInVisible = true
    }

    private fun updateUI(error: Exception?) {
        model.statusText = error?.message ?: ""
    }

    fun onNext() {
        if(identityService.checkUsernameIsValid(model.username, authService.currentCredentials.userId)) {
            identityService.add(authService.currentCredentials.userId, model.username) { identity ->
                authService.currentCredentials.userName = identity.name
                hideEditUsername()
                showNext()
            }
        } else {
            model.statusText = "Benutzername existiert bereits!"
        }
    }

}