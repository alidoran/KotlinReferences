package alidoran.android.compose.developer_android_samples.courses.c1

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "small font",
    group = "Font scales",
    fontScale = 0.5f
)

@Preview(
    name = "Large Font",
    group = "Font Scales",
    fontScale = 1.5f
)

annotation class FontScalePreview

@Preview(
    name = "Dark mode",
    group = "UI mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)

@Preview(
    name = "Light mode",
    group = "UI mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)

annotation class DarkLightPreview