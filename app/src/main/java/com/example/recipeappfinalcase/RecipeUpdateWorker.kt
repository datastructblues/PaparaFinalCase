package com.example.recipeappfinalcase

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.recipeappfinalcase.R
import com.example.recipeappfinalcase.data.repository.RecipeRepository
import com.example.recipeappfinalcase.data.source.network.NetworkState
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltWorker
class RecipeUpdateWorker @AssistedInject constructor(
    @Assisted @NonNull context: Context,
    @Assisted @NonNull workerParams: WorkerParameters,
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        Log.d("RecipeUpdateWorker", "doWork: Start")
        sendRecipeNotification(applicationContext, "Title", "Content")
        return try {
            Result.success()
        } catch (e: Exception) {
            Log.e("RecipeUpdateWorker", "doWork: Exception - ${e.message}", e)
            Result.failure()
        }
    }

    private fun sendRecipeNotification(context: Context, title: String, content: String) {
        val builder = NotificationCompat.Builder(context, "RECIPE_UPDATES")
            .setSmallIcon(R.drawable.favorite)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            notify(1, builder.build())
        }
    }
}



