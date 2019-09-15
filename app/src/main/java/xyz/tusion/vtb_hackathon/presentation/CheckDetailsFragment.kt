package xyz.tusion.vtb_hackathon.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.comp_loading.*
import kotlinx.android.synthetic.main.frag_check_details.*
import kotlinx.android.synthetic.main.item_commodity.*
import kotlinx.android.synthetic.main.item_commodity.item_commodity_tv_name
import kotlinx.android.synthetic.main.item_commodity.item_commodity_tv_total_price
import kotlinx.android.synthetic.main.one_good.*
import xyz.tusion.vtb_hackathon.R
import xyz.tusion.vtb_hackathon.extensions.getBoldSumRegularCurrencySpannableStringBuilder
import xyz.tusion.vtb_hackathon.extensions.toCurrencyString
import xyz.tusion.vtb_hackathon.extensions.toGone
import xyz.tusion.vtb_hackathon.extensions.toVisible
import xyz.tusion.vtb_hackathon.model.fts.Commodity
import xyz.tusion.vtb_hackathon.model.parceQrReceipt
import xyz.tusion.vtb_hackathon.presentation.ScanQrFragment.Companion.SCAN_QR_CONTENT_CODE
import xyz.tusion.vtb_hackathon.repositories.CheckRepository

class CheckDetailsFragment : Fragment() {
    private lateinit var commodityListAdapter: CommodityListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_check_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        commodityListAdapter = CommodityListAdapter()
        frag_check_details_rv.adapter = commodityListAdapter
        comp_loading_root.toVisible()

        val qrReceipt = parceQrReceipt(arguments?.getString(SCAN_QR_CONTENT_CODE)!!)
        CheckRepository.getCommodities(qrReceipt)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ commodityList ->
                commodityListAdapter.updateItems(commodityList)
                comp_loading_root.toGone()
            }, {
                comp_loading_root.toGone()
            })
    }

    override fun onDestroyView() {
        frag_check_details_rv.adapter = null
        super.onDestroyView()
    }
}

class CommodityListAdapter(
    var items: MutableList<Commodity> = mutableListOf()
) : RecyclerView.Adapter<CommodityListAdapter.CommodityViewHolder>() {

    fun updateItems(newItems: List<Commodity>) {
        items.apply {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommodityViewHolder {
        return CommodityViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_commodity,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CommodityViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class CommodityViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: Commodity) {
            item_commodity_tv_name.text = item.name

            one_good_total_count.text =
                containerView.context.getString(R.string.commodity_items_count)
                .format(item.quantity)

            item_commodity_tv_total_price.text = (item.sum!!.toDouble() / 100)
                .toBigDecimal()
                .toCurrencyString()
                .getBoldSumRegularCurrencySpannableStringBuilder(
                    containerView.context.getString(R.string.currency_rubles),
                    containerView.context,
                    sumColorResId = R.color.col_text_black
                )
        }
    }
}