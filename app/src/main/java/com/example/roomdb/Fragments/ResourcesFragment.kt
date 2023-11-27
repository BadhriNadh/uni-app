package com.example.roomdb.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.R
import com.example.roomdb.entities.Link
import android.widget.ImageView
import android.widget.LinearLayout

// Fragment displaying resources
class ResourcesFragment : Fragment() {

    lateinit var webView: WebView
    lateinit var linkRecyclerView: RecyclerView
    private lateinit var adapter: LinkAdapter
    lateinit var closeButton: ImageView
    private val imageResources = listOf(
        R.drawable.resscroll2,
        R.drawable.resscroll1,
        // Add more image resources as needed
    )
    private val links = listOf(
        // List of links with associated information
        Link("Academics", "https://www.dal.ca/academics.html",R.drawable.academics),
        Link("Campus Life", "https://www.dal.ca/campus_life.html",R.drawable.resource),
        Link("Research & Innovation", "https://www.dal.ca/research.html",R.drawable.image1),
        Link("Libraries", "https://libraries.dal.ca/",R.drawable.image),
        Link("Dal News", "https://www.dal.ca/news.html",R.drawable.saveus)
        // Add more links and their URLs as needed
    )
    // Flag to manage link visibility
    var isShowingLinks = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflating the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_resources, container, false)
        val imageScrollView: HorizontalScrollView = view.findViewById(R.id.imageHorizontalScrollView)
        val imageLinearLayout: LinearLayout = view.findViewById(R.id.imageLinearLayout)
        val resourcesTextView: TextView = view.findViewById(R.id.resourcesTextView)
        // Setting dynamic content

        resourcesTextView.text = "Resources"
        imageResources.forEach { imageResId ->
            val imageView = ImageView(requireContext())
            imageView.setImageResource(imageResId)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            val params = LinearLayout.LayoutParams(200, LinearLayout.LayoutParams.MATCH_PARENT) // Adjust width as needed
            params.marginEnd = 8.dpToPx() // Convert DP to pixels or set margins as needed
            imageView.layoutParams = params
            imageLinearLayout.addView(imageView)
        }
        webView = view.findViewById(R.id.webView)
        linkRecyclerView = view.findViewById(R.id.linkRecyclerView)
        linkRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        closeButton = view.findViewById(R.id.closeButton)
        closeButton.setOnClickListener {
            isShowingLinks = true
            webView.visibility = View.GONE
            linkRecyclerView.visibility = View.VISIBLE
            closeButton.visibility = View.GONE // Hide close button when returning to links
        }
        // Initializing and setting up views
        adapter = LinkAdapter(links) { url ->
            loadWebPage(url)
            isShowingLinks = false
        }

        linkRecyclerView.adapter = adapter

        // Initialize WebView settings
        setupWebView()

        return view
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        // Configuring WebView settings
        webView.webViewClient = MyWebViewClient()
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true // Enable JavaScript if required

        // Override back button behavior for WebView
        webView.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP && webView.canGoBack()) {
                webView.goBack()
                true
            } else {
                false
            }
        }
    }

    fun loadWebPage(url: String) {
        // Load the URL in the WebView
        webView.loadUrl(url)
        webView.visibility = View.VISIBLE
        linkRecyclerView.visibility = View.GONE
        closeButton.visibility = View.VISIBLE // Show close button when WebView is visible
    }

    // Handle back button press for WebView navigation
    fun onBackPressed(): Boolean {
        // Handle back button press for WebView and link visibility
        if (!isShowingLinks && webView.canGoBack()) {
            webView.goBack()
            return true
        } else if (!isShowingLinks) {
            isShowingLinks = true
            webView.visibility = View.GONE
            linkRecyclerView.visibility = View.VISIBLE
            closeButton.visibility = View.GONE // Hide close button when returning to links
            return true
        }
        return false
    }
    // Inner class for handling WebView client behavior
    private inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            // Method to handle URL loading in the WebView
            view?.loadUrl(url ?: "")
            return true
        }
    }
    private fun Int.dpToPx(): Int {
        // Convert DP to pixels
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            resources.displayMetrics
        ).toInt()
    }
}
