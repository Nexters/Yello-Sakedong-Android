package com.yello.nexters.yellosakedong.output

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yello.nexters.yellosakedong.R
import com.yello.nexters.yellosakedong.base.BaseActivity
import com.yello.nexters.yellosakedong.base.OnRecyclerItemClickListener
import com.yello.nexters.yellosakedong.network.NetworkObject
import com.yello.nexters.yellosakedong.network.ServiceModule
import com.yello.nexters.yellosakedong.output.adapter.OutputAdapter
import com.yello.nexters.yellosakedong.output.model.OutputItem
import com.yello.nexters.yellosakedong.utils.getYellowSakedongKey
import com.yello.nexters.yellosakedong.utils.log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_output.*
import kotlinx.android.synthetic.main.base_toolbar.*
import kotlinx.android.synthetic.main.layout_output_title.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class OutputActivity : BaseActivity() {

    override fun getToolbar(): Toolbar = toolbar

    private var outputAdapter: OutputAdapter? = null

    private lateinit var subscription: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_output)
        initToolBar()
        visibleBackButton(true)


        val i = intent
        val foodEmoji = i.getIntExtra("food_emoji", -1)
        val foodId = i.getStringExtra("data_id")
        val foodName = i.getStringExtra("food_name")
        val foodImageUrl = i.getStringExtra("food_image")

        log(foodId)
        log(foodName)
        log(foodImageUrl)
        log(foodEmoji)

        txt_food_name.text = foodName
        Glide.with(this).load(foodImageUrl)
                .apply(RequestOptions.circleCropTransform())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_add_circle_black_84dp))
                .into(image_output_main_food_image)

        if(foodEmoji!=-1) {
            Glide.with(this).load(foodEmoji)
                    .apply(RequestOptions.circleCropTransform())
                    .into(image_output_main_food_emoji)
        } else {
            image_output_main_food_emoji.visibility = View.INVISIBLE
        }

        outputAdapter = OutputAdapter(this, onRecyclerItemClickListener)

        recycler_view_output.setHasFixedSize(true)
        recycler_view_output.layoutManager = LinearLayoutManager(this)
        recycler_view_output.adapter = outputAdapter

        getItem(foodId)
    }

    private fun getItem(foodId: String) {


        subscription = ServiceModule.restAPI().comments(getYellowSakedongKey(this), foodId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            log(result)
                            addItem(result.comments)
                        },
                        { err ->
                            toast(err.message!!)
                            log(err)
                        }
                )
    }

    private fun addItem(result: List<NetworkObject.CommentDataModel>) {
        val list = ArrayList<OutputItem>()
        var index = 0
        var isOwner = false

        result.forEach {
            it ->
            run {
                isOwner = it.user_id == getYellowSakedongKey(this)
                list.add(OutputItem(index, it.user_id, it.likeCount, it.comment, isOwner, false))
            }
        }

        list.sortWith(compare)
        outputAdapter?.setItems(list)
        outputAdapter?.notifyDataSetChanged()
    }


    private fun dumpItem() {
        val list = ArrayList<OutputItem>()
        list.add(OutputItem(0, "사케", 312, "사케맛", false, true))
        list.add(OutputItem(1, "노랑", 123, "노랑맛", false, false))
        list.add(OutputItem(2, "모기", 10, "모기맛", false, true))
        list.add(OutputItem(3, "동", 3, "동동", true,false))
        list.add(OutputItem(4, "멸치", 1, "멸치맛", false, true))
        list.add(OutputItem(5, "믱", 1, "믱", false, true))
        list.add(OutputItem(6, "끵", 1, "끵", true, true))
        list.add(OutputItem(7, "노랑사케동", 1, "노랑사케동", false, false))
        list.add(OutputItem(8, "풋팟퐁커리", 1, "풋팡퐁커리", false, false))


        list.sortWith(compare)
        outputAdapter?.setItems(list)
        outputAdapter?.notifyDataSetChanged()
    }

    private val compare = Comparator<OutputItem> { o1, o2 ->
        if (o1?.isOwner == true && o2?.isOwner == false) {
            -1
        } else if (o1?.isOwner == false && o2?.isOwner == true) {
            1
        } else {
            return@Comparator if ( o1?.likeCount!! > o2?.likeCount!!) {
            -1
        } else {
            1
        }
        }
    }


    private val onRecyclerItemClickListener: OnRecyclerItemClickListener = object : OnRecyclerItemClickListener {
        override fun onItemClick(position: Int) {
            val outputItem = outputAdapter?.getItem(position)
            outputItem?.let {
                log(it.userName)
            }
        }
    }
}
