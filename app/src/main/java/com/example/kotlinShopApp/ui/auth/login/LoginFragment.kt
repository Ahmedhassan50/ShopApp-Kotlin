package com.example.kotlinShopApp.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlinShopApp.R
import com.example.kotlinShopApp.databinding.FragmentLoginBinding
import com.example.kotlinShopApp.db.model.UserData
import com.example.kotlinShopApp.ui.auth.AuthViewModel
import com.example.kotlinShopApp.ui.home.HomeActivity
import com.example.kotlinShopApp.utils.ServiceStatus
import com.example.kotlinShopApp.utils.StatusInterface
import com.example.kotlinShopApp.utils.showErrorDialog
import com.google.gson.Gson

class LoginFragment : Fragment() ,StatusInterface{
    private  lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel:AuthViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        viewModel.addContext(this)
        binding.registerTv.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment2)
        }

        binding.login.setOnClickListener{
            val email=binding.email.text.toString()
            val password=binding.password.text.toString()
            val valid= checkInputValidation(email,password)
            if(valid){
                viewModel.login(email,password)

            }


        }
    }




  private  fun checkInputValidation(email:String,password:String):Boolean{
        var valid=true
        if(email.isEmpty()|| Patterns.EMAIL_ADDRESS.matcher(email).matches().not()){
            valid=false
            binding.emailLayout.error="Please Enter Valid Email.."
        }else{
            binding.emailLayout.isErrorEnabled=false
        }
        if(password.isEmpty()){
            valid=false
            binding.passwordLayout.error="Please Enter Your Password.."
        }else{
            binding.passwordLayout.isErrorEnabled=false
        }

       return valid

    }




  private  fun showProgress(){
        binding.login.visibility =View.GONE
        binding.progressBar.visibility =View.VISIBLE
    }

  private  fun closeProgress(){
        binding.login.visibility =View.VISIBLE
        binding.progressBar.visibility =View.GONE
    }

    override fun getStatus(status: ServiceStatus) {

            when(status){
                ServiceStatus.LOADING ->{
                    Log.i("Loading","loadinf")
                    showProgress()
                }
                ServiceStatus.DONE->{
                    Log.i("done","done")
                    closeProgress()
                    val sharedPref=requireActivity().getSharedPreferences("user", AppCompatActivity.MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    val userData:String= Gson().toJson(UserData.user)
                    editor.putString("userData",userData)
                    editor.apply()
                    val intent=Intent(requireActivity(),HomeActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                ServiceStatus.ERROR->{
                    Log.i("error","error")
                    closeProgress()
                    showErrorDialog(requireActivity(),  viewModel.errorMessage!!)

                }
            }
        }



}