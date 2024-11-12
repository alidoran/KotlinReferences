@file:Suppress("SameParameterValue")

package alidoran.android.compose.developer_android_samples.courses.c1

import alidoran.android.R
import alidoran.android.compose.ui.theme.KotlinReferencesTheme
import alidoran.android.compose.ui.theme.Typography
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.commonlibrary.fake_endpoint.PictureModel
import com.example.commonlibrary.fake_endpoint.generatePictureList


class CodeLabBasicLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MySootheApp(true)
        }
    }
}

@Composable
private fun HomeScreen(modifier: Modifier = Modifier) {
    val listModel = generatePictureList(100)
    Column(
        modifier.verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = "Align Your Body") {
            AlignYourBodyRow(listModel)
        }
        HomeSection(title = "Favorite Collections") {
            FavoriteCollectionsGrid(listModel)
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
private fun HomeSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
private fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        placeholder = {
            Text(stringResource(R.string.search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Composable
private fun AlignYourBodyElement(
    @DrawableRes image: Int,
    itemText: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
                .background(Color.LightGray),
            painter = painterResource(id = image),
            contentScale = ContentScale.Crop,
            contentDescription = ""
        )
        Text(
            text = itemText,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = Typography.titleSmall
        )
    }
}

@Composable
private fun FavoriteCollectionCard(
    @DrawableRes image: Int,
    itemText: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.width(255.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.LightGray)
            )
            Text(
                modifier = Modifier.padding(16.dp),
                text = itemText,
                style = Typography.titleMedium
            )
        }
    }
}

@Composable
private fun AlignYourBodyRow(
    composePictureList: List<PictureModel>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(composePictureList) {
            AlignYourBodyElement(
                image = it.image,
                itemText = it.title
            )
        }
    }
}

@Composable
private fun FavoriteCollectionsGrid(
    composePictureList: List<PictureModel>,
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(composePictureList) {
            FavoriteCollectionCard(
                modifier = Modifier.height(80.dp),
                image = it.image,
                itemText = it.title
            )
        }
    }
}

@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription = null) },
            label = { Text("Favorite") },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
            label = { Text("Home") },
            selected = false,
            onClick = {}
        )
    }
}

@Composable
private fun MySootheAppPortrait() {
    KotlinReferencesTheme {
        Scaffold(
            bottomBar = { SootheBottomNavigation() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}

@Composable
private fun SootheNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription = null) },
                label = { Text("Favorite") },
                selected = true,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
                label = { Text("Home") },
                selected = false,
                onClick = {}
            )
        }
    }
}

@Composable
private fun MySootheAppLandscape() {
    KotlinReferencesTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Row {
                SootheNavigationRail()
                HomeScreen()
            }
        }
    }
}

@Composable
private fun MySootheApp(isPortrait: Boolean) {
    if (isPortrait) MySootheAppPortrait()
    else MySootheAppLandscape()
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
private fun HomeSectionPreview() {
    KotlinReferencesTheme {
        MySootheApp(true)
    }
}

//@Composable
//private fun VerticalListView(
//    modifier: Modifier = Modifier,
//    composePictureList: List<ComposePictureModel>
//) {
//    LazyColumn {
//        items(composePictureList) {
//            FavoriteCollectionCard(
//                modifier = modifier.padding(8.dp),
//                image = it.image,
//                itemText = it.title
//            )
//        }
//    }
//}