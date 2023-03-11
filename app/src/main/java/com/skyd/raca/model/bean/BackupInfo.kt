package com.skyd.raca.model.bean

import kotlinx.serialization.Serializable

@Serializable
data class BackupInfo(
    var uuid: String,
    var contentMd5: String,
    var modifiedTime: Long,
    var isDeleted: Boolean = false
) : BaseBean
