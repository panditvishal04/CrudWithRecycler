package com.vishal.crudwithrecycler

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.vishal.crudwithrecycler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ItemClick {

    lateinit var binding: ActivityMainBinding
    lateinit var roomDbClass: RoomDb
    lateinit var studentRecycler: RecyclerData
    var studentArray = arrayListOf<EntityData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        roomDbClass = RoomDb.createDatabase(this)
        studentRecycler = RecyclerData(studentArray, this)
        binding.rvData.layoutManager = LinearLayoutManager(this)
        binding.rvData.adapter = studentRecycler
        getStudentData()
        binding.fab.setOnClickListener {

            class insertStudentInfo : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg params: Void?): Void? {
                    var EntityData = EntityData(name = "  Vishal", sClass = "B.TECH")
                    roomDbClass.studentDao().insertIntoStudent(EntityData)
                    return null
                }

                override fun onPostExecute(result: Void?) {
                    super.onPostExecute(result)
                    Toast.makeText(
                        this@MainActivity,
                        "Data inserted successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    getStudentData()
                }
            }

            insertStudentInfo().execute()
        }


    }

    fun getStudentData() {
        studentArray.clear()
        studentRecycler.notifyDataSetChanged()
        class getStudent : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                studentArray.addAll(roomDbClass.studentDao().getStudentList())
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                studentRecycler.notifyDataSetChanged()
            }
        }
        getStudent().execute()
    }

    override fun deleteItem(position: Int) {


        class deteleStudent : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                roomDbClass.studentDao().deleteStudent(studentArray[position])
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                getStudentData()
            }
        }

        deteleStudent().execute()

    }

    override fun updateItem(position: Int) {

        var entityData = EntityData(id = position, name = "Vishal p", sClass = "B.TECH")

        class updateStudent : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg p0: Void?): Void? {

                roomDbClass.studentDao().updateStudent(entityData)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                getStudentData()

            }

        }
        updateStudent().execute()

    }
}