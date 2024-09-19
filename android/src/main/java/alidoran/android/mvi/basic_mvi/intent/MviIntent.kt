package alidoran.android.mvi.basic_mvi.intent

sealed class MviIntent {
    object FetchUserMvi: MviIntent()
    object FetchUserMviFlow : MviIntent()
    object FetchUserMviStateFlow : MviIntent()
    object ReloadUser: MviIntent()
}