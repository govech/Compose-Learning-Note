package com.example.happybirthday.game.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.happybirthday.game.MAX_NO_OF_WORDS
import com.example.happybirthday.game.SCORE_INCREASE
import com.example.happybirthday.game.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel 类用于管理游戏界面的UI数据。
 * 它负责初始化UI状态、选择随机单词并进行洗牌操作、重置游戏等功能。
 */
class GameViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()  //asStateFlow() 会使此可变状态流成为只读状态流。

    private lateinit var currentWord: String
    private var usedWords: MutableSet<String> = mutableSetOf()

    var userGuess by mutableStateOf("")
        private set

    init {
        resetGame()
    }


    /**
     * 从预定义的单词列表中选取一个未使用的随机单词，并将其加入已用单词集合。
     * 然后将这个单词的字符打乱顺序生成一个新的字符串。
     *
     * @return 打乱顺序后的字符串
     */
    private fun pickRandomWordAndShuffle(): String {
        currentWord = allWords.random()
        if (usedWords.contains(currentWord)) {
            return pickRandomWordAndShuffle()
        } else {
            usedWords.add(currentWord)
            return shuffleCurrentWord(currentWord)
        }
    }

    fun updateUserGuess(guessedWord: String) {
        userGuess = guessedWord
    }


    fun skipWord() {
        updateGameState(_uiState.value.score)
        // Reset user guess
        updateUserGuess("")
    }

    fun checkUserGuess() {
        if (userGuess.equals(currentWord, true)) {
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
        } else {
            _uiState.update { currentStaue ->
                currentStaue.copy(
                    isGuessedWordWrong = true,
                )
            }
        }
        updateUserGuess("")
    }

    private fun updateGameState(updatedScore: Int) {
        if (usedWords.size == MAX_NO_OF_WORDS) {//游戏结束
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    score = updatedScore,
                    isGameOver = true
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    currentScrambledWord = pickRandomWordAndShuffle(),
                    currentWordCount = currentState.currentWordCount.inc(),
                    score = updatedScore
                )
            }
        }
    }


    /**
     * 将给定单词的字符顺序随机打乱。
     * 打乱后的结果不能与原单词相同。
     *
     * @param word 需要打乱的单词
     * @return 字符顺序被打乱后的字符串
     */
    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()
        tempWord.shuffle()
        while (String(tempWord).equals(word)) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    /**
     * 重置游戏。
     * 清空已使用单词集合，并设置初始的UI状态。
     */
    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }
}