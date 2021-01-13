package com.tessmerandre.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.tessmerandre.app.R
import com.tessmerandre.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private val viewModel: MainViewModel by lazy {
        defaultViewModelProviderFactory.create(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity
        }
        setupBindings()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_form, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.save) {
            viewModel.save()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun setupBindings() {
        viewModel.uiState.observe(this, Observer {
            if (it.failed.consume() == true) {
                Snackbar
                    .make(viewBinding.root, R.string.error_creating_message, Snackbar.LENGTH_LONG)
                    .show()
            }

            if (it.succeed.consume() == true) {
                Snackbar
                    .make(viewBinding.root, R.string.msg_message_successfully_created, Snackbar.LENGTH_LONG)
                    .show()
            }
        })

        viewModel.form.canProceed.observe(this, Observer {
            Snackbar
                .make(viewBinding.root, it.toString(), Snackbar.LENGTH_LONG)
                .show()
        })
    }

}
