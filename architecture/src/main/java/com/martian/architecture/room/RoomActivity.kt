package com.martian.architecture.room

import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.martian.architecture.databinding.ActivityRoomBinding
import com.martian.architecture.room.data.Cat
import com.martian.architecture.room.data.Dog
import com.martian.architecture.room.data.Person
import com.martian.architecture.room.veiwmodel.RoomViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {
    private lateinit var viewModel: RoomViewModel
    private lateinit var binding: ActivityRoomBinding
    private var checkedId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(RoomViewModel::class.java)
        viewModel.initDataBase(this)
        initView()
    }

    private fun initView() {
        binding.tvContent.text = ""
        if (binding.rbPerson.isChecked) {
            checkedId = binding.rbPerson.id
        }
        binding.radioGroup.setOnCheckedChangeListener(this)
    }

    fun insert(view: View) {
        if (binding.etName.text.isNullOrEmpty()) {
            Toast.makeText(this, "please input name", Toast.LENGTH_SHORT).show()
            return
        }
        var age = 0
        if (!binding.etAge.text.isNullOrEmpty()) {
            age = Integer.valueOf(binding.etAge.text.toString())
        }
        lifecycleScope.launch(Dispatchers.IO) {
            when (checkedId) {
                binding.rbPerson.id -> {
                    viewModel.insert(Person(binding.etName.text.toString(), age))
                }
                binding.rbDog.id -> {
                    viewModel.insert(Dog(binding.etName.text.toString(), age))
                }
                binding.rbCat.id -> {
                    viewModel.insert(Cat(binding.etName.text.toString(), age))
                }
            }
        }
    }

    fun delete(view: View) {
        if (binding.etId.text.isNullOrEmpty()) {
            Toast.makeText(this, "please input id", Toast.LENGTH_SHORT).show()
            return
        }
        lifecycleScope.launch(Dispatchers.IO) {
            val id = Integer.valueOf(binding.etId.text.toString())
            when (checkedId) {
                binding.rbPerson.id -> {
                    viewModel.delete(Person(id))
                }
                binding.rbDog.id -> {
                    viewModel.delete(Dog(id))
                }
            }
        }
    }

    fun query(view: View) {
        lifecycleScope.launch(Dispatchers.IO) {
            println("launch: ${Thread.currentThread().name}")
            val id = binding.etId.text
            if (id.isNullOrEmpty()) {

                var list: List<Any>? = null
                when (checkedId) {
                    binding.rbPerson.id -> {
                        list = viewModel.queryAll(Person(0))
                    }
                    binding.rbDog.id -> {
                        list = viewModel.queryAll(Dog(0))
                    }
                    binding.rbCat.id -> {
                        list = viewModel.queryAll(Cat(0))
                    }
                }
                var data = StringBuffer()
                list?.forEach {
                    data.append(it.toString()).append("\n")
                }
                println("query: ${Thread.currentThread().name}")
                withContext(Dispatchers.Main) {
                    binding.tvContent.text = data.toString()
                }
            } else {
                var data: String? = ""
                var int = Integer.valueOf(id.toString())
                when (checkedId) {
                    binding.rbPerson.id -> {
                        data = viewModel.queryById(int, Person(int))?.toString()
                    }
                    binding.rbDog.id -> {
                        data = viewModel.queryById(int, Dog(int))?.toString()
                    }
                    binding.rbCat.id -> {
                        data = viewModel.queryById(int, Cat(int))?.toString()
                    }
                }
                withContext(Dispatchers.Main) {
                    binding.tvContent.text = data
                }
            }
        }
    }

    fun update(view: View) {
        val id = binding.etId.text
        if (!id.isNullOrEmpty()) {
            lifecycleScope.launch(Dispatchers.IO) {
                val name = if (binding.etName.text.isNullOrEmpty()) "" else binding.etName.text.toString()
                val age = if (binding.etAge.text.isNullOrEmpty()) 0 else Integer.valueOf(binding.etAge.text.toString())
                when (checkedId) {
                    binding.rbPerson.id -> {
                        val person = Person(name, age)
                        person.id = Integer.valueOf(id.toString())
                        viewModel.update(person)
                    }
                    binding.rbDog.id -> {
                        val dog = Dog(name, age)
                        dog.id = Integer.valueOf(id.toString())
                        viewModel.update(dog)
                    }
                }
            }
        } else {
            Toast.makeText(this, "please input id", Toast.LENGTH_SHORT).show()
        }
    }

    fun bind(view: View) {
        val personId = binding.etId.text
        val dogId = binding.etDogId.text
        val catId = binding.etCatId.text
        if (personId.isNullOrEmpty()) {
            Toast.makeText(this, "please input person id", Toast.LENGTH_SHORT).show()
            return
        }
        if (dogId.isNullOrEmpty() && catId.isNullOrEmpty()) {
            Toast.makeText(this, "please input other id", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch(Dispatchers.IO) {
            var otherId  = 0
            var any :Any = if(!dogId.isNullOrEmpty()) {
                otherId = Integer.valueOf(dogId.toString())
                Dog(otherId)
            }  else {
                otherId = Integer.valueOf(catId.toString())
                Cat(otherId)
            }
            var data = viewModel.queryById(otherId, any)?.toString()
            if (data.isNullOrEmpty()) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RoomActivity, "please input vaild other id", Toast.LENGTH_SHORT).show()
                }
                return@launch
            }
            viewModel.bind(Integer.valueOf(personId.toString()), otherId,any)
        }
    }

    fun unbind(view: View) {
        val id = binding.etId.text
        if (id.isNullOrEmpty()) {
            Toast.makeText(this, "please input person id", Toast.LENGTH_SHORT).show()
            return
        }
        var any :Any? = null
        when (checkedId) {
            binding.rbDog.id -> { any = Dog(0) }
            binding.rbCat.id -> { any = Cat(0) }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.unbind(Integer.valueOf(id.toString()),any)
        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        this.checkedId = checkedId;
        when (checkedId) {
            binding.rbPerson.id -> {
                binding.llButton3.visibility = View.VISIBLE
            }
            binding.rbDog.id, binding.rbCat.id -> {
                binding.llButton3.visibility = View.GONE
            }
        }
    }
}