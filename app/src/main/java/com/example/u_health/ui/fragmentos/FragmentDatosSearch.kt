package com.example.u_health.ui.fragmentos

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.u_health.R
import com.example.u_health.databinding.FragmentDatosSearchBinding
import com.example.u_health.databinding.FragmentRecordatoriosBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentDatosSearch.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentDatosSearch : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentDatosSearchBinding? = null
    private val binding get() = _binding!!
    private var _bindingListView: FragmentRecordatoriosBinding? = null
    private val bindingListView get() = _bindingListView!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDatosSearchBinding.inflate(inflater, container, false)
        _bindingListView = FragmentRecordatoriosBinding.inflate(inflater,container,false)
        val view = binding.root
        funcionalidad()
        val toolbar: Toolbar = binding!!.tbRecordatorio
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setTitle(getString(R.string.strRecordatorio))
        toolbar.setTitleTextColor(Color.WHITE)
        return view
    }
    private fun funcionalidad(){
        val user = add()
        val userAdapter : ArrayAdapter<String> = ArrayAdapter(
            requireContext(), android.R.layout.simple_list_item_1,user
        )
        binding.userList.adapter = userAdapter
        binding.userList.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = binding.userList.getItemAtPosition(position)
            val intent = Intent(requireContext(), FrequencyData::class.java)
            intent.putExtra("selectedItem", selectedItem.toString())
            startActivity(intent)
        }
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.search.clearFocus()
                if(user.contains(query)){
                    userAdapter.filter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                userAdapter.filter.filter(newText)
                return false
            }

        })
    }
    private fun add() : Array<String> {
        val medicamentos: Array<String> = arrayOf(
            "Losartan 100mg",
            "Amlodipino 5mg",
            "Enalapril 20mg",
            "Valsartan 80mg",
            "Hidroclorotiazida 50mg",
            "Metoprolol 100mg",
            "Lisinopril 20mg",
            "Propranolol 100mg",
            "Candesartan 20mg",
            "Clonidina 75mg",
            "Nebivolol 10mg",
            "Irbesartan 30mg",
            "Carvedilol 25mg",
            "Nifedipino 30mg",
            "Furosemida 40mg",
            "Verapamilo 20mg",
            "Atorvastatina 20mg",
            "Telmisartan 40mg",
            "Bisoprolol 10mg",
            "Indapamida 5mg",
            "Labetalol 100mg",
            "Hidroclorotiazida/losartan 50mg/100mg",
            "Hidroclorotiazida/amiloride 50mg/10mg",
            "Valsartan/hidroclorotiazida 80mg/50mg",
            "Hidroclorotiazida/metoprolol 50mg/100mg",
            "Enalapril/hidroclorotiazida 20mg/50mg",
            "Olmesartan 20mg",
            "Ramipril 10mg",
            "Losartan/hidroclorotiazida 100mg/50mg",
            "Clortalidona 10mg",
            "Doxazosina 10mg",
            "Prazosina 10mg",
            "Quinapril 20mg",
            "Perindopril 10mg",
            "Atenolol 100mg",
            "Metildopa 250mg",
            "Bendroflumetiazida 5mg",
            "Spironolactona 25mg",
            "Hidroclorotiazida/triamtereno 50mg/25mg",
            "Hidroclorotiazida/valsartan 50mg/80mg",
            "Hidroclorotiazida/lisinopril 50mg/20mg",
            "Metoprolol tartrato 100mg",
            "Hidroclorotiazida/olmesartan 50mg/20mg",
            "Clonidina/hidroclorotiazida 75mg/50mg",
            "Nebivolol/valsartan 10mg/80mg",
            "Verapamilo/hidroclorotiazida 20mg/50mg",
            "Enalapril/felodipino 20mg/5mg",
            "Lisinopril/hidroclorotiazida 20mg/50mg",
            "Bisoprolol/hidroclorotiazida 10mg/50mg",
            "Amlodipino/valsartan 5mg"
        )
        return medicamentos
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentDatosSearch().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}