package android.lifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.R


class LifeCycleFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("LifeCycleFragment", "onCreateFragment")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("LifeCycleFragment", "onCreateViewFragment")
        return inflater.inflate(R.layout.fragment_life_cycle, container, false)
    }

    override fun onAttach(context: Context) {
        Log.d("LifeCycleFragment", "onAttachFragment")
        super.onAttach(context)
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        Log.d("LifeCycleFragment", "onActivityCreatedFragment")
//        super.onActivityCreated(savedInstanceState)
//    }

    override fun onResume() {
        Log.d("LifeCycleFragment", "onResumeCreatedFragment")
        super.onResume()
    }

    override fun onStart() {
        Log.d("LifeCycleFragment", "onStartFragment")
        super.onStart()
    }

    override fun onStop() {
        Log.d("LifeCycleFragment", "onStopCreatedFragment")
        super.onStop()
    }

    override fun onPause() {
        Log.d("LifeCycleFragment", "onPauseFragment")
        super.onPause()
    }

    override fun onDestroyView() {
        Log.d("LifeCycleFragment", "onDestroyViewFragment")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("LifeCycleFragment", "onDestroyFragment")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d("LifeCycleFragment", "onDetachFragment")
        super.onDetach()
    }
}