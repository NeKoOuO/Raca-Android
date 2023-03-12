package com.skyd.raca.ui.screen.about.license

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.skyd.raca.R
import com.skyd.raca.ext.plus
import com.skyd.raca.model.bean.LicenseBean
import com.skyd.raca.ui.component.BackIcon
import com.skyd.raca.ui.component.RacaTopBar
import com.skyd.raca.ui.component.RacaTopBarStyle
import com.skyd.raca.ui.local.LocalNavController
import com.skyd.raca.util.CommonUtil.openBrowser

const val LICENSE_SCREEN_ROUTE = "licenseScreen"

@Composable
fun LicenseScreen() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val navController = LocalNavController.current
    Scaffold(
        topBar = {
            RacaTopBar(
                style = RacaTopBarStyle.Large,
                title = { Text(text = stringResource(R.string.license_screen_name)) },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    BackIcon { navController.popBackStack() }
                }
            )
        }
    ) {
        val dataList = remember { getLicenseList() }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            contentPadding = PaddingValues(vertical = 10.dp) + it
        ) {
            items(items = dataList) { item ->
                LicenseItem(item)
            }
        }
    }
}

@Composable
private fun LicenseItem(data: LicenseBean) {
    Card(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
        Column(
            modifier = Modifier
                .clickable { openBrowser(data.url) }
                .padding(15.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = data.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    modifier = Modifier.padding(start = 5.dp),
                    text = data.license,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = data.url,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

private fun getLicenseList(): List<LicenseBean> {
    return listOf(
        LicenseBean(
            name = "Android Open Source Project",
            license = "Apache-2.0 License",
            url = "https://source.android.com/"
        ),
        LicenseBean(
            name = "Accompanist",
            license = "Apache-2.0 License",
            url = "https://github.com/google/accompanist"
        ),
        LicenseBean(
            name = "Hilt",
            license = "Apache-2.0 License",
            url = "https://github.com/googlecodelabs/android-hilt"
        ),
        LicenseBean(
            name = "Coil",
            license = "Apache-2.0 License",
            url = "https://github.com/coil-kt/coil"
        ),
        LicenseBean(
            name = "kotlinx.coroutines",
            license = "Apache-2.0 License",
            url = "https://github.com/Kotlin/kotlinx.coroutines"
        ),
        LicenseBean(
            name = "Gson",
            license = "Apache-2.0 License",
            url = "https://github.com/google/gson"
        ),
        LicenseBean(
            name = "kotlin-csv",
            license = "Apache-2.0 License",
            url = "https://github.com/doyaaaaaken/kotlin-csv"
        ),
        LicenseBean(
            name = "sardine-android",
            license = "Apache-2.0 License",
            url = "https://github.com/thegrizzlylabs/sardine-android"
        ),
        LicenseBean(
            name = "Kotlin Serialization",
            license = "Apache-2.0 License",
            url = "https://github.com/Kotlin/kotlinx.serialization"
        ),
    )
}