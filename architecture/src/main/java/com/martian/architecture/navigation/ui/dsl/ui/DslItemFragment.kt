package com.martian.architecture.navigation.ui.dsl.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.martian.architecture.R
import com.martian.architecture.navigation.ui.dsl.data.nav_graph
import com.martian.architecture.navigation.ui.dsl.ui.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class DslItemFragment : Fragment(), MyItemRecyclerViewAdapter.OnItemClickListener {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dsl_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                val myAdapter = MyItemRecyclerViewAdapter(PlaceholderContent.ITEMS);
                adapter = myAdapter
//                myAdapter.setOnItemClickListener(this@DslItemFragment)

//                var mCallBack = object :OnItemClickListener{
//                    override fun onClickListener(item: PlaceholderContent.PlaceholderItem) {
//                    }
//                }

//                myAdapter.setOnItemClickListener(object :OnItemClickListener{
//                    override fun onClickListener(item: PlaceholderContent.PlaceholderItem) {
//                        val args = bundleOf(nav_graph.args.plant_id to item.content)
//                        findNavController().navigate(nav_graph.action.to_plant_detail,args)
//                    }
//                })
                myAdapter.setOnSmartItemClickListener {
                    Toast.makeText(requireContext(), "from smart listener", Toast.LENGTH_LONG).show()
                    val args = bundleOf(nav_graph.args.plant_id to it.content)
                    findNavController().navigate(nav_graph.action.to_plant_detail, args)
                }
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            DslItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

    override fun onClickListener(item: PlaceholderContent.PlaceholderItem) {
        val args = bundleOf(nav_graph.args.plant_id to item.content)
        findNavController().navigate(nav_graph.action.to_plant_detail, args)

    }
}