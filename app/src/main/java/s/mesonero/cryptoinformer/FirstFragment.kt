package s.mesonero.cryptoinformer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import s.mesonero.cryptoinformer.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    val cryptoInfoViewModel:CryptoInfoViewModel by viewModels ()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectFlow()

        binding.buttonFirst.setOnClickListener {

            cryptoInfoViewModel.getCriptoInfo()
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.buttonSecond.setOnClickListener {
            cryptoInfoViewModel.getVError()
        }
    }

    private fun collectFlow() {
        // Start a coroutine in the lifecycle scope
        lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Trigger the flow and start listening for values.
                // Note that this happens when lifecycle is STARTED and stops
                // collecting when the lifecycle is STOPPED
                cryptoInfoViewModel.uiState.collect { uiState ->
                    // New value received
                    when (uiState) {
                        is CryState.Success -> showUiData(uiState.cryptoDataUi)
                        is CryState.Error -> showError(uiState.appError)
                        is CryState.Loading -> showLoading()
                    }
                }
            }
        }
    }

    private fun showLoading() {
        Log.e("depuro", "show loading")
    }

    private fun showError(error: CryAppError) {
        Log.e("depuro", "show error "+error)

    }

    private fun showUiData(it: CryptoDataUi) {
        Log.e("depuro", "show ui data "+it)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
}