package com.martian.architecture.navigation.ui.dsl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavType
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.martian.architecture.R
import com.martian.architecture.databinding.ActivityDslBinding
import com.martian.architecture.navigation.ui.dsl.data.nav_graph
import com.martian.architecture.navigation.ui.dsl.ui.DslDetailFragment
import com.martian.architecture.navigation.ui.dsl.ui.DslItemFragment

class DSLActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDslBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDslBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_dsl) as NavHostFragment
        navHostFragment.navController.apply {
            graph = createGraph(nav_graph.id,nav_graph.dest.home){
                fragment<DslItemFragment>(nav_graph.dest.home){
                    label = getString(R.string.title_dsl_item)
                    action(nav_graph.action.to_plant_detail){
                        destinationId = nav_graph.dest.plant_detail
                    }
                }

                fragment<DslDetailFragment>(nav_graph.dest.plant_detail){
                    label = getString(R.string.title_dsl_detail)
                   argument(nav_graph.args.plant_id){
                       type = NavType.StringType
                   }
                }
            }
        }
    }
}