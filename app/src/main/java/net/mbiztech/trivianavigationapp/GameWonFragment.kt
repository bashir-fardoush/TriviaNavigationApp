/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.mbiztech.trivianavigationapp

import android.content.Intent
import android.content.pm.PackageManager
import android.drm.DrmStore
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController


class GameWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding : net.mbiztech.trivianavigationapp.databinding.FragmentGameWonBinding =
            DataBindingUtil.inflate<net.mbiztech.trivianavigationapp.databinding.FragmentGameWonBinding>(inflater,R.layout.fragment_game_won,container,false)


        binding.nextMatchButton.setOnClickListener(){ view: View ->
            view.findNavController().navigate(R.id.action_gameWonFragment_to_gameFragment)


        }

         var args  = GameWonFragmentArgs.fromBundle(requireArguments())
        Toast.makeText(activity,"Num Question: ${args.numQuestions}, num correct: ${args.numCorrect } ",Toast.LENGTH_LONG).show()

        setHasOptionsMenu(true)
        return  binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.winner_menu,menu)

        if(getShareIntent().resolveActivity(activity!!.packageManager) == null){

            menu?.findItem(R.id.share)?.setVisible(false)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            R.id.share -> shareSuccess()
        }

       return super.onOptionsItemSelected(item)


    }

    private fun getShareIntent() : Intent {

        val args = GameWonFragmentArgs.fromBundle(requireArguments())

        return ShareCompat.IntentBuilder.from(activity)
            .setText("text/plain")
            .setText(getString(R.string.share_success_text,args.numCorrect,args.numQuestions))
            .intent

    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }
}
