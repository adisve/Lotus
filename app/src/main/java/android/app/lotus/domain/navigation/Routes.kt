package android.app.lotus.domain.navigation

object Routes {
    const val home = "home"
    const val homeArticles = "home/articles"
    const val homeArticleDetail = "home/articleDetail/{title}"
    const val homeVideos = "home/videos"
    const val homeVideoDetail = "home/videoDetail/{url}"

    const val profile = "profile"
    const val profileSupport = "profile/support"
    const val profileAddUserAccount = "profile/addUserAccount"
    const val profileEdit = "profile/edit"

    const val analytics = "analytics"
    const val analyticsEvaluation = "analytics/evaluation"
    const val analyticsStats = "analytics/stats"
}
