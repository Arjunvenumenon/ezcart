package com.example.ezcart.presentation.help

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.ezcart.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {

    private lateinit var binding: FragmentHelpBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHelpBinding.inflate(inflater, container, false)

        binding.helpWebView.webViewClient = WebViewClient()
        binding.helpWebView.settings.javaScriptEnabled = true

        binding.helpWebView.loadUrl("file:///android_asset/help.html")

        return binding.root
    }

}