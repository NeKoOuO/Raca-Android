package com.skyd.raca.ui.component.dialog

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.skyd.raca.R
import com.skyd.raca.ui.component.RacaLottieAnimation

@Composable
fun WaitingDialog(
    visible: Boolean,
    currentValue: Int? = null,
    totalValue: Int? = null,
    title: String = stringResource(R.string.webdav_screen_waiting)
) {
    if (currentValue == null || totalValue == null) {
        WaitingDialog(visible = visible, title = title)
    } else {
        val animatedProgress by animateFloatAsState(
            targetValue = currentValue.toFloat() / totalValue,
            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
        )
        WaitingDialog(visible = visible, title = title) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LinearProgressIndicator(
                    modifier = Modifier.semantics(mergeDescendants = true) {},
                    progress = animatedProgress,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "$currentValue / $totalValue",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Composable
fun WaitingDialog(
    visible: Boolean,
    title: String = stringResource(R.string.webdav_screen_waiting),
    text: @Composable (() -> Unit)? = null,
) {
    RacaDialog(
        visible = visible,
        onDismissRequest = { },
        icon = {
            RacaLottieAnimation(
                modifier = Modifier.size(48.dp),
                resId = R.raw.lottie_genshin_impact_klee_3
            )
        },
        title = {
            Text(text = title)
        },
        text = text,
        confirmButton = {}
    )
}