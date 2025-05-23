package alidoran.android

import alidoran.android.backlight_light_change.BackLightChangeActivity
import alidoran.android.activity_result_launcher.ResultLauncherSourceActivity
import alidoran.android.add_dynamic_view.DynamicViewActivity
import alidoran.android.binding.DataBindingActivity
import alidoran.android.binding.ViewBindingActivity
import alidoran.android.biometric.BiometricActivity
import alidoran.android.bottom_sheet.BottomSheetActivity
import alidoran.android.canvas.CanvasActivity
import alidoran.android.compose.ComposeLearnActivity
import alidoran.android.data_store.DataStoreActivity
import alidoran.android.databinding.ActivityAndroidBinding
import alidoran.android.dialog.DialogActivity
import alidoran.android.fragment.FragmentActivity
import alidoran.android.intents.IntentsActivity
import alidoran.android.kotlin_flow.KotlinFlowActivity
import alidoran.android.lifecycle.LifeCycleActivity
import alidoran.android.loading.LoadingActivity
import alidoran.android.location.LocationActivity
import alidoran.android.mvi.basic_mvi.view.MviActivity
import alidoran.android.mvp.MvpActivity
import alidoran.android.mvvm_livedata.view.MvvmMainActivity
import alidoran.android.navigation_safe_args.NavigationActivity
import alidoran.android.paging_view.PagingRecyclerActivity
import alidoran.android.permissions.PermissionsActivity
import alidoran.android.recycler_view.RecyclerActivity
import alidoran.android.room.activity.RoomActivity
import alidoran.android.save_state_handle.SavedStateHandleActivity
import alidoran.android.sensors.ui.SensorsActivity
import alidoran.android.services.ServicesActivity
import alidoran.android.shared_preference.SharedPreferenceActivity
import alidoran.android.socket.SocketActivity
import alidoran.android.view_to_model.ViewToModelActivity
import alidoran.android.viewpager2.slider.ViewPagerSliderActivity
import alidoran.android.webview.WebviewActivity
import alidoran.android.worker.WorkerActivity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class AndroidActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAndroidBinding.inflate(layoutInflater)
        setContentView(binding.root)
        installerName()

        binding.btnMainRecycler.setOnClickListener {
            val intent = Intent(this, RecyclerActivity::class.java)
            startActivity(intent)
        }

        binding.btnLifecycle.setOnClickListener {
            val intent = Intent(this, LifeCycleActivity::class.java)
            startActivity(intent)
        }

        binding.btnBacklightChangeLight.setOnClickListener {
            val intent = Intent(this, BackLightChangeActivity::class.java)
            startActivity(intent)
        }

        binding.btnBottomSheetMain.setOnClickListener {
            val intent = Intent(this, BottomSheetActivity::class.java)
            startActivity(intent)
        }

        binding.btnServices.setOnClickListener{
            val intent = Intent(this, ServicesActivity::class.java)
            startActivity(intent)
        }

        binding.btnSharedPreferences.setOnClickListener{
            val intent = Intent(this, SharedPreferenceActivity::class.java)
            startActivity(intent)
        }

        binding.btnViewBinding.setOnClickListener{
            val intent = Intent(this, ViewBindingActivity::class.java)
            startActivity(intent)
        }

        binding.btnDataBinding.setOnClickListener{
            val intent = Intent(this, DataBindingActivity::class.java)
            startActivity(intent)
        }

        binding.btnMvvmLiveData.setOnClickListener{
            val intent = Intent(this, MvvmMainActivity::class.java)
            startActivity(intent)
        }

        binding.btnRoom.setOnClickListener{
            val intent = Intent(this, RoomActivity::class.java)
            startActivity(intent)
        }

        binding.btnViewToModel.setOnClickListener{
            val intent = Intent(this, ViewToModelActivity::class.java)
            startActivity(intent)
        }

        binding.btnSocket.setOnClickListener{
            val intent = Intent(this, SocketActivity::class.java)
            startActivity(intent)
        }

        binding.btnMvp.setOnClickListener{
            val intent = Intent(this, MvpActivity::class.java)
            startActivity(intent)
        }

        binding.btnPaging.setOnClickListener{
            val intent = Intent(this, PagingRecyclerActivity::class.java)
            startActivity(intent)
        }

        binding.btnViewpagerSlider.setOnClickListener{
            val intent = Intent(this, ViewPagerSliderActivity::class.java)
            startActivity(intent)
        }

        binding.btnFragment.setOnClickListener {
            val intent = Intent(this, FragmentActivity::class.java)
            startActivity(intent)
        }

        binding.btnNavigation.setOnClickListener {
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
        }

        binding.btnResultLauncher.setOnClickListener {
            val intent = Intent(this, ResultLauncherSourceActivity::class.java)
            startActivity(intent)
        }

        binding.btnSavedStateHandle.setOnClickListener {
            val intent = Intent(this, SavedStateHandleActivity::class.java)
            startActivity(intent)
        }

        binding.btnDialogs.setOnClickListener {
            val intent = Intent(this, DialogActivity::class.java)
            startActivity(intent)
        }

        binding.btnDynamicView.setOnClickListener {
            val intent = Intent(this, DynamicViewActivity::class.java)
            startActivity(intent)
        }

        binding.btnMvi.setOnClickListener {
            val intent = Intent(this, MviActivity::class.java)
            startActivity(intent)
        }

        binding.btnKotlinFlow.setOnClickListener {
            val intent = Intent(this, KotlinFlowActivity::class.java)
            startActivity(intent)
        }

        binding.btnWorker.setOnClickListener {
            val intent = Intent(this, WorkerActivity::class.java)
            startActivity(intent)
        }

        binding.btnCompose.setOnClickListener {
            val intent = Intent(this, ComposeLearnActivity::class.java)
            startActivity(intent)
        }

        binding.btnLocation.setOnClickListener {
            val intent = Intent(this, LocationActivity::class.java)
            startActivity(intent)
        }

        binding.btnPermission.setOnClickListener {
            val intent = Intent(this, PermissionsActivity::class.java)
            startActivity(intent)
        }

        binding.btnBiometric.setOnClickListener {
            val intent = Intent(this, BiometricActivity::class.java)
            startActivity(intent)
        }

        binding.btnIntents.setOnClickListener {
            val intent = Intent(this, IntentsActivity::class.java)
            startActivity(intent)
        }

        binding.btnWebview.setOnClickListener {
            val intent = Intent(this, WebviewActivity::class.java)
            startActivity(intent)
        }

        binding.btnSensors.setOnClickListener {
            val intent = Intent(this, SensorsActivity::class.java)
            startActivity(intent)
        }

        binding.btnDataStore.setOnClickListener {
            val intent = Intent(this, DataStoreActivity::class.java)
            startActivity(intent)
        }

        binding.btnCanvas.setOnClickListener {
            val intent = Intent(this, CanvasActivity::class.java)
            startActivity(intent)
        }

        binding.btnLoading.setOnClickListener {
            val intent = Intent(this, LoadingActivity::class.java)
            startActivity(intent)
        }
    }
    private fun installerName() {
        val installerName = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val init = this.packageManager.getInstallSourceInfo(this.packageName).installingPackageName
            Log.d("TAG", "installerName1: $init ")
            val init2 = this.packageManager.getInstallSourceInfo(this.packageName).initiatingPackageSigningInfo
            Log.d("TAG", "installerName2: $init2 ")
            this.packageManager.getInstallSourceInfo(this.packageName).installingPackageName
        } else {
            @Suppress("DEPRECATION")
            this.packageManager.getInstallerPackageName(this.packageName)
        }

        Log.d("TAG", "installerName3: $installerName ")
    }
}