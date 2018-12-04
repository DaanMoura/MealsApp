package br.ufscar.mobile.meals.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import br.ufscar.mobile.meals.R
import br.ufscar.mobile.meals.cenarios.meal_list.MainFragment
import br.ufscar.mobile.meals.entidades.Meal
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import org.jetbrains.anko.find

class MyYoutubeFragment : Fragment(), YouTubePlayer.OnInitializedListener {



    companion object {
        private val YOUTUBE_KEY = "AIzaSyAKFd7bGni_56INO4ksL7mi8ewXaUtWZog"
        private val ARG_ID =  "arg_id"
        fun newInstance(videoId: String) =
            MyYoutubeFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_ID, videoId)
                }
            }
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        if(!wasRestored)
            player?.cueVideo(getVideoID())
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, result: YouTubeInitializationResult?) {
        if (result!!.isUserRecoverableError()) {
            result.getErrorDialog(this.getActivity(),1).show();
        } else {
            Toast.makeText(this.getActivity(),
                "YouTubePlayer.onInitializationFailure(): " + result.toString(),
                Toast.LENGTH_LONG).show();
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmentYoutubeView : View = inflater.inflate(R.layout.fragment_my_youtube, container, false)

        val mYoutubePlayerFragment = YouTubePlayerSupportFragment()
        mYoutubePlayerFragment.initialize(YOUTUBE_KEY, this)
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_youtube_player, mYoutubePlayerFragment)?.commit()

        // Inflate the layout for this fragment
        return fragmentYoutubeView
    }

    override fun onPause() {
        super.onPause()

    }

    fun getVideoID(): String {
        val videoId = arguments?.getSerializable(MyYoutubeFragment.ARG_ID) as String?
        if(videoId == null) {
            throw NullPointerException("video ID can not be null")
        }
        return videoId
    }


}
