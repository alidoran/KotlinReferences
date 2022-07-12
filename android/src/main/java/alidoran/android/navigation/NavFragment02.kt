package alidoran.android.navigation

import alidoran.android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class NavFragment02 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_nav02, container, false)
        val txtView = view.findViewById<TextView>(R.id.txt_whole_02)
        txtView.setOnClickListener{ Navigation.findNavController(view).navigate(R.id.action_navFragment02_to_navFragment01)}
        return view
    }

}