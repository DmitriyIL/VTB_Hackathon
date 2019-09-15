package xyz.tusion.vtb_hackathon.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.frag_profile.*
import kotlinx.android.synthetic.main.item_room.*
import xyz.tusion.vtb_hackathon.CreateRoomDialog
import xyz.tusion.vtb_hackathon.R
import xyz.tusion.vtb_hackathon.SearchDialog
import xyz.tusion.vtb_hackathon.model.back.CheckDivisionRoom

class ProfileFragment : Fragment() {
    private lateinit var roomListAdapter: RoomListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        roomListAdapter = RoomListAdapter()
        frag_profile_rv.adapter = roomListAdapter

        val list = MutableList(20) { CheckDivisionRoom("Ресторан палуба", "Вы", 7493256) }
        roomListAdapter.updateItems(list)

        initListeners()
    }

    private fun initListeners() {
        frag_profile_fab_new_room.setOnClickListener {
            showTheDialog(CreateRoomDialog())
        }
        frag_check_details_qr.setOnClickListener {
            showTheDialog(SearchDialog())
        }
    }

    protected fun showTheDialog(f : DialogFragment) {
        val ft = fragmentManager!!.beginTransaction()
        ft.add(f, "fragment_dialog")
        ft.commit()
    }


}

class RoomListAdapter(
    var items: MutableList<CheckDivisionRoom> = mutableListOf()
) : RecyclerView.Adapter<RoomListAdapter.RoomViewHolder>() {

    fun updateItems(newItems: List<CheckDivisionRoom>) {
        items.apply {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        return RoomViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_room,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class RoomViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: CheckDivisionRoom) {
            item_room_tv_id.text = containerView.context.getString(R.string.room_item_id).format(item.id)
            item_room_tv_name.text = item.name
            item_room_tv_owner.text = containerView.context.getString(R.string.room_item_owner).format(item.owner)
        }
    }
}