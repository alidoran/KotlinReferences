package alidoran.android.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.commonlibrary.fake_endpoint.FakeEndpoint

class UploadWorker(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {
    override fun doWork(): Result {

        // Do the work here--in this case, upload the images.
        FakeEndpoint.longProcess()

        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }
}