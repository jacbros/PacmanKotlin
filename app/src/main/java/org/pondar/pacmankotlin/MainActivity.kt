package org.pondar.pacmankotlin

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import org.pondar.pacmankotlin.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), OnClickListener {

    var counter : Int = 0

    //reference to the game class.
    private lateinit var game: Game
    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Game state controls
        binding.startButton.setOnClickListener(this)
        binding.stopButton.setOnClickListener(this)
        binding.resetButton.setOnClickListener(this)


        //makes sure it always runs in portrait mode - will cost a warning
        //but this is want we want!
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        Log.d("onCreate","Oncreate called")

        game = Game(this,binding.pointsView)

        //intialize the game view clas and game class
        game.setGameView(binding.gameView)
        binding.gameView.setGame(game)
        game.reset()

        binding.moveRight.setOnClickListener {
            game.movePacman(0)
        }
        binding.moveLeft.setOnClickListener {
            game.movePacman(1)
        }
        binding.moveUp.setOnClickListener {
            game.movePacman(2)
        }
        binding.moveDown.setOnClickListener {
            game.movePacman(3)
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.startButton) {
            game.running = true
        } else if (v.id == R.id.stopButton) {
            game.running = false
        } else if (v.id == R.id.resetButton) {
            counter = 0
            game.reset()
            game.running = false
        }
    }
}
