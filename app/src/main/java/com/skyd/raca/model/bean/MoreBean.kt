package com.skyd.raca.model.bean

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector

data class MoreBean(
    val title: String,
    val icon: ImageVector,
    val iconTint: Color,
    val shape: Shape,
    val shapeColor: Color,
    val action: () -> Unit
) : BaseBean

typealias More1Bean = MoreBean