package com.louisstudio.imagedialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.github.chrisbanes.photoview.OnPhotoTapListener
import com.github.chrisbanes.photoview.PhotoView
import java.util.*

class ShowImagesDialog(
    private val mContext: Context,
    private val mImgUrls: List<String>,
    private val pos: Int
) : Dialog(mContext, R.style.transparentBgDialog) {
    private var mView: View? = null
    private var mViewPager: ShowImagesViewPager? = null
    private var mIndexText: TextView? = null
    private var mTitles: MutableList<String>? = null
    private var mViews: MutableList<View>? = null
    private var mAdapter: ShowImagesAdapter? = null
    private fun initView() {
        mView = View.inflate(mContext, R.layout.dialog_images_brower, null)
        mViewPager = mView!!.findViewById<View>(R.id.vp_images) as ShowImagesViewPager
        mIndexText = mView!!.findViewById<View>(R.id.tv_image_index) as TextView
        mTitles = ArrayList()
        mViews = ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(mView!!)
        val window = window
        val wl = window!!.attributes
        wl.x = 0
        wl.y = 0
        val manager: WindowManager = window.windowManager
        val outMetrics = DisplayMetrics()
        manager.defaultDisplay.getMetrics(outMetrics)
        val width = outMetrics.widthPixels
        val height = outMetrics.heightPixels
        wl.height = height
        wl.width = width
        wl.gravity = Gravity.CENTER
        window.attributes = wl
    }

    private fun initData() { //点击图片监听
//        PhotoViewAttacher.OnPhotoTapListener listener = new PhotoViewAttacher.OnPhotoTapListener() {
//            @Override
//            public void onPhotoTap(View view, float x, float y) {
//                dismiss();
//            }
//        };
        for (i in mImgUrls.indices) {
            val photoView = PhotoView(mContext)
            val layoutParams =
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            photoView.layoutParams = layoutParams
            photoView.setOnPhotoTapListener { view, x, y -> dismiss() }
            //点击图片外围（无图片处）监听
            /**
             * photoView.setOnViewTapListener(new OnViewTapListener() {
             * @Override
             * public void onViewTap(View view, float x, float y){
             * dismiss();
             * }
             * });
             */
            Glide.with(mContext)
                .load(mImgUrls[i])
                .into(object : SimpleTarget<Drawable?>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable?>?
                    ) {
                        photoView.setImageDrawable(resource)
                    }
                })
            mViews!!.add(photoView)
            mTitles!!.add(i.toString() + "")
        }
        mAdapter = mViews?.let { ShowImagesAdapter(it, mTitles) }
        mViewPager!!.adapter = mAdapter
        mIndexText!!.text = "1/${mImgUrls.size}"
        mViewPager!!.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                val t = "${position+1}/${mImgUrls.size}"
                mIndexText!!.text = t
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        mViewPager!!.currentItem = pos
    }

    init {
        initView()
        initData()
    }
}
