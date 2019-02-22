package com.yello.nexters.yellosakedong.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.telephony.TelephonyManager
import android.view.View
import com.igalata.bubblepicker.BubblePickerListener
import com.igalata.bubblepicker.adapter.BubblePickerAdapter
import com.igalata.bubblepicker.model.BubbleGradient
import com.igalata.bubblepicker.model.PickerItem
import com.tbruyelle.rxpermissions2.RxPermissions
import com.yello.nexters.yellosakedong.R
import com.yello.nexters.yellosakedong.base.BaseActivity
import com.yello.nexters.yellosakedong.network.ServiceAPI
import com.yello.nexters.yellosakedong.network.ServiceModule
import com.yello.nexters.yellosakedong.utils.SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD
import com.yello.nexters.yellosakedong.utils.dot_porgress_bar.SpotsDialog
import com.yello.nexters.yellosakedong.utils.getYellowSakedongKey
import com.yello.nexters.yellosakedong.utils.log
import com.yello.nexters.yellosakedong.utils.setYellowSakedongKey
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.base_toolbar.*
import javax.inject.Inject


class MainActivity : BaseActivity() {
    override fun getToolbar(): Toolbar = toolbar

    @Inject
    lateinit var serviceAPI: ServiceAPI

    private lateinit var subscription: Disposable

    var rxPermissions: RxPermissions? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission()
        } else {
            checkUserToken()
        }
    }

    private fun checkPermission() {
        rxPermissions = RxPermissions(this)
        rxPermissions?.let {
            it.request(Manifest.permission.READ_PHONE_STATE)
                    .subscribe { granted ->
                        run {
                            if (granted) {
                                checkUserToken()
                            } else {
                                toast("권한을 다시 한 번 확인 해 주세요.")
                                finish()
                            }
                        }
                    }
        }
    }

    private fun checkUserToken() {
        val key = getYellowSakedongKey(this)
        if (key == "") {
            signUp()
        }
        initToolBar()
        movingImage()
        event()
    }

    private fun signUp() {
        log("signUp")

        subscription = ServiceModule.restAPI().signUp(getDeviceId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> setYellowSakedongKey(this, result.userId) },
                        { err ->
                            toast(err.message!!)
                            log(err)
                        }
                )
    }

    @SuppressLint("MissingPermission")
    private fun getDeviceId(): String {
        val deviceID = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        log(deviceID.deviceId)
        return deviceID.deviceId
    }

    fun dialog() {
        val dialog = SpotsDialog.Builder().setMessage("test").setContext(this).build()
        dialog.show()
    }

    private fun movingImage() {
        val titles = resources.getStringArray(R.array.countries)
        val colors = resources.obtainTypedArray(R.array.colors)
        val images = resources.obtainTypedArray(R.array.images)

        picker_main.adapter = object : BubblePickerAdapter {

            override val totalCount = titles.size

            override fun getItem(position: Int): PickerItem {
                return PickerItem().apply {

                    title = titles[position]
                    gradient = BubbleGradient(colors.getColor((position * 2) % 8, 0),
                            colors.getColor((position * 2) % 8 + 1, 0), BubbleGradient.VERTICAL)
                    textColor = ContextCompat.getColor(this@MainActivity, android.R.color.white)
                    backgroundImage = ContextCompat.getDrawable(this@MainActivity, images.getResourceId(position, 0))
                }
            }
        }

        picker_main.centerImmediately = true

        picker_main.listener = object : BubblePickerListener {
            override fun onBubbleSelected(item: PickerItem) {
                page404()
                log(item.title)
            }

            override fun onBubbleDeselected(item: PickerItem) {

            }
        }
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.image_emoticon -> {
//                val it = Intent(this, OutputActivity::class.java)
//                startActivity(it)
                dialog()
            }
        }
    }

    private fun event() {

        edit_main_word.viewTreeObserver.addOnGlobalLayoutListener {
            if (isKeyboardShown(edit_main_word.rootView)) {
                layout_emoticon.visibility = View.VISIBLE
                layout_emoticon.animate().translationY(-50f).duration = 1000
                picker_main.visibility = View.GONE
                layout_moving_image.visibility = View.GONE
            } else {
                layout_emoticon.visibility = View.GONE
                layout_emoticon.translationY = 100f
                picker_main.visibility = View.VISIBLE
                layout_moving_image.visibility = View.VISIBLE
            }
        }

        txt_toolbar_add_taste.setOnClickListener { addPage() }
    }

    private fun isKeyboardShown(rootView: View): Boolean {

        val r = Rect()
        rootView.getWindowVisibleDisplayFrame(r)
        val dm = rootView.resources.displayMetrics
        val heightDiff = rootView.bottom - r.bottom

        return heightDiff > SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD * dm.density
    }

    override fun onResume() {
        super.onResume()
        picker_main.onResume()
    }

    override fun onPause() {
        super.onPause()
        picker_main.onPause()
    }
}
