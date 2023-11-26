package com.example.roomdb.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.HorizontalScrollView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.R
import com.example.roomdb.entities.Link


import android.widget.ImageView
import android.widget.LinearLayout

//worksss
class ResourcesFragment : Fragment() {

    lateinit var webView: WebView
    private lateinit var linkRecyclerView: RecyclerView
    private lateinit var adapter: LinkAdapter
    private lateinit var closeButton: ImageView
    private val imageResources = listOf(
        R.drawable.resscroll2,
        R.drawable.resscroll1,
        // Add more image resources as needed
    )
    private val links = listOf(
        Link("Academics", "https://www.dal.ca/academics.html",R.drawable.academics),
        Link("Campus Life", "https://www.dal.ca/campus_life.html",R.drawable.resource),
        Link("Research & Innovation", "https://www.dal.ca/research.html",R.drawable.image1),
        Link("Libraries", "https://libraries.dal.ca/",R.drawable.image),
        Link("Dal News", "https://www.dal.ca/news.html",R.drawable.saveus)
        // Add more links and their URLs as needed
    )

    var isShowingLinks = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_resources, container, false)
        val imageScrollView: HorizontalScrollView = view.findViewById(R.id.imageHorizontalScrollView)
        val imageLinearLayout: LinearLayout = view.findViewById(R.id.imageLinearLayout)
        val resourcesTextView: TextView = view.findViewById(R.id.resourcesTextView)
        resourcesTextView.text = "Resources" // Set your dynamic content here
        // Populate images horizontally
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

    private fun loadWebPage(url: String) {
        webView.loadUrl(url)
        webView.visibility = View.VISIBLE
        linkRecyclerView.visibility = View.GONE
        closeButton.visibility = View.VISIBLE // Show close button when WebView is visible
    }

    // Handle back button press for WebView navigation
    fun onBackPressed(): Boolean {
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

    private inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url ?: "")
            return true
        }
    }
    private fun Int.dpToPx(): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            resources.displayMetrics
        ).toInt()
    }
}

//
//import android.widget.ImageView
//
//
//class ResourcesFragment : Fragment() {
//
//    private lateinit var webView: WebView
//    private lateinit var linkRecyclerView: RecyclerView
//    private lateinit var adapter: LinkAdapter
//    private lateinit var closeButton: ImageView
//
//    private val links = listOf(
//        Link("Giant Panda", "https://en.wikipedia.org/wiki/Giant_panda"),
//        Link("Lion", "https://en.wikipedia.org/wiki/Lion"),
//        Link("Tiger", "https://en.wikipedia.org/wiki/Tiger")
//        // Add more links and their URLs as needed
//    )
//
//    private var isShowingLinks = true
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_resources, container, false)
//
//        webView = view.findViewById(R.id.webView)
//        linkRecyclerView = view.findViewById(R.id.linkRecyclerView)
//        linkRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//        closeButton = view.findViewById(R.id.closeButton)
//        closeButton.setOnClickListener {
//            isShowingLinks = true
//            webView.visibility = View.GONE
//            linkRecyclerView.visibility = View.VISIBLE
//        }
//
//        adapter = LinkAdapter(links) { url ->
//            loadWebPage(url)
//            isShowingLinks = false
//        }
//
//        linkRecyclerView.adapter = adapter
//
//        // Initialize WebView settings
//        setupWebView()
//
//        return view
//    }
//
//    @SuppressLint("SetJavaScriptEnabled")
//    private fun setupWebView() {
//        webView.webViewClient = MyWebViewClient()
//        val webSettings = webView.settings
//        webSettings.javaScriptEnabled = true // Enable JavaScript if required
//
//        // Override back button behavior for WebView
//        webView.setOnKeyListener { _, keyCode, event ->
//            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP && webView.canGoBack()) {
//                webView.goBack()
//                true
//            } else {
//                false
//            }
//        }
//    }
//
//    private fun loadWebPage(url: String) {
//        webView.loadUrl(url)
//        webView.visibility = View.VISIBLE
//        linkRecyclerView.visibility = View.GONE
//    }
//
//    // Handle back button press for WebView navigation
//    fun onBackPressed(): Boolean {
//        if (!isShowingLinks && webView.canGoBack()) {
//            webView.goBack()
//            return true
//        } else if (!isShowingLinks) {
//            isShowingLinks = true
//            webView.visibility = View.GONE
//            linkRecyclerView.visibility = View.VISIBLE
//            return true
//        }
//        return false
//    }
//
//    private inner class MyWebViewClient : WebViewClient() {
//        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//            view?.loadUrl(url ?: "")
//            return true
//        }
//    }
//}


//works well
//class ResourcesFragment : Fragment() {
//
//    private lateinit var webView: WebView
//    private lateinit var linkRecyclerView: RecyclerView
//    private lateinit var adapter: LinkAdapter
//
//    private val links = listOf(
//        Link("Giant Panda", "https://en.wikipedia.org/wiki/Giant_panda"),
//        Link("Lion", "https://en.wikipedia.org/wiki/Lion"),
//        Link("Tiger", "https://en.wikipedia.org/wiki/Tiger")
//        // Add more links and their URLs as needed
//    )
//
//    private var isShowingLinks = true
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_resources, container, false)
//
//        webView = view.findViewById(R.id.webView)
//        linkRecyclerView = view.findViewById(R.id.linkRecyclerView)
//        linkRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//        adapter = LinkAdapter(links) { url ->
//            loadWebPage(url)
//            isShowingLinks = false
//        }
//
//        linkRecyclerView.adapter = adapter
//
//        // Initialize WebView settings
//        setupWebView()
//
//        return view
//    }
//
//    @SuppressLint("SetJavaScriptEnabled")
//    private fun setupWebView() {
//        webView.webViewClient = MyWebViewClient()
//        val webSettings = webView.settings
//        webSettings.javaScriptEnabled = true // Enable JavaScript if required
//    }
//
//    private fun loadWebPage(url: String) {
//        webView.loadUrl(url)
//        webView.visibility = View.VISIBLE
//        linkRecyclerView.visibility = View.GONE
//    }
//
//    // Handle back button press for WebView navigation
//    fun onBackPressed(): Boolean {
//        if (!isShowingLinks && webView.canGoBack()) {
//            webView.goBack()
//            return true
//        } else if (!isShowingLinks) {
//            isShowingLinks = true
//            webView.visibility = View.GONE
//            linkRecyclerView.visibility = View.VISIBLE
//            return true
//        }
//        return false
//    }
//
//    private inner class MyWebViewClient : WebViewClient() {
//        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//            view?.loadUrl(url ?: "")
//            return true
//        }
//    }
//}


//opens in new tab
//import com.example.roomdb.entities.Link
//
//class ResourcesFragment : Fragment() {
//
//    private lateinit var webView: WebView
//    private lateinit var linkRecyclerView: RecyclerView
//    private lateinit var adapter: LinkAdapter
//
//    private val links = listOf(
//        Link("Giant Panda", "https://en.wikipedia.org/wiki/Giant_panda"),
//        Link("Lion", "https://en.wikipedia.org/wiki/Lion"),
//        Link("Tiger", "https://en.wikipedia.org/wiki/Tiger")
//        // Add more links and their URLs as needed
//    )
//
//    private var isShowingLinks = true
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_resources, container, false)
//
//        webView = view.findViewById(R.id.webView)
//        linkRecyclerView = view.findViewById(R.id.linkRecyclerView)
//        linkRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//        adapter = LinkAdapter(links) { url ->
//            loadWebPage(url)
//            isShowingLinks = false
//        }
//
//        linkRecyclerView.adapter = adapter
//
//        return view
//    }
//
//    @SuppressLint("SetJavaScriptEnabled")
//    private fun setupWebView() {
//        webView.webViewClient = WebViewClient()
//        val webSettings = webView.settings
//        webSettings.javaScriptEnabled = true // Enable JavaScript if required
//    }
//
//    private fun loadWebPage(url: String) {
//        webView.loadUrl(url)
//        webView.visibility = View.VISIBLE
//        linkRecyclerView.visibility = View.GONE
//    }
//
//    // Handle back button press for WebView navigation
//    fun onBackPressed(): Boolean {
//        if (!isShowingLinks && webView.canGoBack()) {
//            webView.goBack()
//            return true
//        } else if (!isShowingLinks) {
//            isShowingLinks = true
//            webView.visibility = View.GONE
//            linkRecyclerView.visibility = View.VISIBLE
//            return true
//        }
//        return false
//    }
//}


//webview with links


//class ResourcesFragment : Fragment() {
//
//    private lateinit var webView: WebView
//    private lateinit var linkListView: ListView
//
//    private val links = listOf(
//        "Giant Panda" to "https://en.wikipedia.org/wiki/Giant_panda",
//        "Lion" to "https://en.wikipedia.org/wiki/Lion",
//        "Tiger" to "https://en.wikipedia.org/wiki/Tiger"
//        // Add more links and their URLs as needed
//    )
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_resources, container, false)
//
//        webView = view.findViewById(R.id.webView)
//        linkListView = view.findViewById(R.id.linkListView)
//
//        setupWebView()
//        setupLinkList()
//
//        // Load the first link by default
//        loadWebPage(links[0].second)
//
//        return view
//    }
//
//    @SuppressLint("SetJavaScriptEnabled")
//    private fun setupWebView() {
//        webView.webViewClient = WebViewClient()
//        val webSettings = webView.settings
//        webSettings.javaScriptEnabled = true // Enable JavaScript if required
//    }
//
//    private fun setupLinkList() {
//        val linkTitles = links.map { it.first }.toTypedArray()
//        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, linkTitles)
//        linkListView.adapter = adapter
//
//        linkListView.onItemClickListener =
//            AdapterView.OnItemClickListener { _, _, position, _ ->
//                val url = links[position].second
//                loadWebPage(url)
//            }
//    }
//
//    private fun loadWebPage(url: String) {
//        webView.loadUrl(url)
//    }
//
//    // Handle back button press for WebView navigation
//    fun onBackPressed(): Boolean {
//        if (webView.canGoBack()) {
//            webView.goBack()
//            return true
//        }
//        return false
//    }
//}


// webview
//class ResourcesFragment : Fragment() {
//
//    private lateinit var webView: WebView
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_resources, container, false)
//
//        webView = view.findViewById(R.id.webView)
//        setupWebView()
//        loadWebPage("https://en.wikipedia.org/wiki/Giant_panda") // Replace this URL with the desired webpage URL
//
//        return view
//    }
//
//    @SuppressLint("SetJavaScriptEnabled")
//    private fun setupWebView() {
//        webView.webViewClient = WebViewClient()
//        val webSettings = webView.settings
//        webSettings.javaScriptEnabled = true // Enable JavaScript if required
//    }
//
//    private fun loadWebPage(url: String) {
//        webView.loadUrl(url)
//    }
//
//    // Handle back button press for WebView navigation
//    fun onBackPressed(): Boolean {
//        if (webView.canGoBack()) {
//            webView.goBack()
//            return true
//        }
//        return false
//    }
//}
//webview

//class ResourcesFragment : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_resources, container, false)
//        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
//        val webView: WebView = view.findViewById(R.id.webView)
//
//        webView.settings.javaScriptEnabled = true // Enable JavaScript (if required)
//
//        val links = listOf(
//            "https://en.wikipedia.org/wiki/Giant_panda",
//            "https://www.example.com/link2",
//            "https://www.example.com/link3"
//            // Add more links as needed
//        )
//
//        val adapter = LinkAdapter(links, webView)
//
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//        return view
//    }
//
//    inner class LinkAdapter(
//        private val links: List<String>,
//        private val webView: WebView
//    ) : RecyclerView.Adapter<LinkAdapter.LinkViewHolder>() {
//
//        inner class LinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//            private val linkTextView: TextView = itemView.findViewById(R.id.linkTextView)
//
//            fun bind(link: String) {
//                linkTextView.text = link
//                itemView.setOnClickListener {
//                    loadWebPage(link)
//                }
//            }
//        }
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
//            val view = LayoutInflater.from(parent.context)
//                .inflate(R.layout.item_link, parent, false)
//            return LinkViewHolder(view)
//        }
//
//        override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
//            holder.bind(links[position])
//        }
//
//        override fun getItemCount(): Int {
//            return links.size
//        }
//    }
//
//    private fun loadWebPage(url: String) {
//        Log.d("kova", "click")
//
//        val webView: WebView = requireView().findViewById(R.id.webView)
//        webView.visibility = View.VISIBLE // Make the WebView visible
//        webView.loadUrl(url) // Load the URL in the WebView
//    }
//
//    companion object {
//        @JvmStatic
//        fun newInstance() = ResourcesFragment()
//    }
//}
