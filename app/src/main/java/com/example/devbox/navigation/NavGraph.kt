package com.example.devbox.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.devbox.features.apitester.ApiTesterScreen
import com.example.devbox.features.base64.Base64Screen
import com.example.devbox.features.graphql.GraphQLScreen
import com.example.devbox.features.history.HistoryScreen
import com.example.devbox.features.home.HomeScreen
import com.example.devbox.features.jsonformatter.JsonFormatterScreen
import com.example.devbox.features.jsonkotlin.JsonToKotlinScreen
import com.example.devbox.features.jwt.JwtDecoderScreen
import com.example.devbox.features.regex.RegexScreen
import com.example.devbox.features.sql.SqlPlaygroundScreen
import com.example.devbox.features.websocket.WebSocketScreen

@Composable
fun NavGraph()
{
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    )
    {
        composable(Routes.HOME){
            HomeScreen(navController)
        }

        composable(Routes.JSON){
            JsonFormatterScreen()
        }

        composable(Routes.JWT){
            JwtDecoderScreen()
        }

        composable(Routes.BASE64)
        {
            Base64Screen()
        }

        composable(Routes.API)
        {
            ApiTesterScreen()
        }

        composable(Routes.REGEX)
        {
            RegexScreen()
        }

        composable(Routes.SQL)
        {
            SqlPlaygroundScreen()
        }

        composable(Routes.HIS) {
            HistoryScreen()
        }

        composable(Routes.KOTLINJSON) {
            JsonToKotlinScreen()
        }

        composable(Routes.WEBSOCK) {
            WebSocketScreen()
        }

        composable(Routes.GRAPHSQL) {
            GraphQLScreen()
        }
    }
}