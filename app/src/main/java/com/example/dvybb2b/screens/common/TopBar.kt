import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar(
    title: String,
    onMenuClick: () -> Unit
) {
    Column {
        TopBarHeader(onMenuClick)
    }
}

@Composable
fun TopBarHeader(onMenuClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(84.dp),
        color = Color(0xFFF9F9F9),
        shadowElevation = 4.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // ⬅️ Menu Icon with click
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu Icon",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
                    .size(24.dp)
                    .clickable { onMenuClick() },
                tint = Color.Black
            )

            Text(
                text = "DVYB – Vendor",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center),
                color = Color.Black
            )
        }
    }
}



//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.material.Icon // <-- Correct import
//import androidx.compose.material.Text
//import androidx.compose.material.TopAppBar
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material3.Surface
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
////import com.example.dvyb2b2.ui.dashboard.TopBarHeader
//import com.example.dvybb2b.R
////
////@Composable
////fun TopBar(title: String) {
////    Spacer(modifier = Modifier.height(12.dp))
////    TopAppBar(
////        title = { Text(title, fontWeight = FontWeight.Bold) },
////        navigationIcon = {
////            Icon(
////                painter = painterResource(id = R.drawable.logo),
////                contentDescription = "App Logo",
////                modifier = Modifier.padding(start = 16.dp)
////            )
////        },
////        backgroundColor = Color.White,
////        elevation = 4.dp
////    )
////}
//
//@Composable
//fun TopBar(title: String,
//           onMenuClick: () -> Unit
//) {
//    Column {
//        TopBarHeader()
//
//
////        TopAppBar(
////            title = {
////                Spacer(modifier = Modifier.height(16.dp))
////                Text(
////                    title,
////                    fontWeight = FontWeight.Bold,
////                    color = Color.Black
////                )
////            },
////            navigationIcon = {
////                Icon(
////                    painter = painterResource(id = R.drawable.logo),
////                    contentDescription = "App Logo",
////                    modifier = Modifier
////                        .padding(start = 16.dp)
////                        .size(32.dp) // Optional: Resize logo
////                )
////            },
////            backgroundColor = Color.White,
////            elevation = 4.dp,
////            modifier = Modifier
////                .fillMaxWidth()
////                .height(84.dp) // Increase height
////        )
//    }
//}
//
//@Composable
//fun TopBarHeader() {
//    Surface(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(84.dp),
//        color = Color(0xFFF9F9F9),
//        shadowElevation = 4.dp
//    ) {
//        Box(modifier = Modifier.fillMaxSize()) {
//            androidx.compose.material3.Icon(
//                imageVector = Icons.Default.Menu,
//                contentDescription = "Menu Icon",
//                modifier = Modifier
//                    .align(Alignment.CenterStart)
//                    .padding(start = 16.dp)
//                    .size(24.dp),
//                tint = Color.Black
//            )
//
//            androidx.compose.material3.Text(
//                text = "DVYB – Vendor",
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.align(Alignment.Center),
//                color = Color.Black
//            )
//
//
//        }
//    }
//}
//
