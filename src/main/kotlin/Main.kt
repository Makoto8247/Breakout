// ブロック崩し
import processing.core.*

class Main : PApplet(){
    inner class Player{
        val PLAYER_POS = PVector(0f,0f)
        val PLAYER_W = 50f
        init{
            PLAYER_POS.y = height.toFloat() - 100f
        }
        fun draw(){
            pushStyle()
            PLAYER_POS.x = mouseX.toFloat()
            // 壁判定
            if(PLAYER_POS.x >= width.toFloat() - (PLAYER_W / 2)) PLAYER_POS.x = width.toFloat() - (PLAYER_W / 2)
            if(PLAYER_POS.x <= PLAYER_W / 2) PLAYER_POS.x = PLAYER_W / 2
            fill(50f,200f,50f)
            rectMode(CENTER)
            rect(PLAYER_POS.x,PLAYER_POS.y,50f,10f)
            popStyle()
        }
    }

    inner class Ball{
        val BALL_POS = PVector(0f,0f)
        val BALL_VELOCITY = PVector(4f,4f)
        val BALL_R = 20f
        init {
            // 初期位置
            BALL_POS.x = width.toFloat() / 2f
            BALL_POS.y = height.toFloat() / 2f
        }
        fun draw(){
            pushStyle()
            fill(200f,50f,50f)
            // 壁判定
            if(BALL_POS.x <= BALL_R / 2f || BALL_POS.x >= width.toFloat() - (BALL_R / 2f)) BALL_VELOCITY.x *= -1
            if(BALL_POS.y <= BALL_R / 2f || BALL_POS.y >= height.toFloat() - (BALL_R / 2f)) BALL_VELOCITY.y *= -1
            ellipseMode(CENTER)
            ellipse(BALL_POS.x,BALL_POS.y,BALL_R,BALL_R)
            BALL_POS.add(BALL_VELOCITY)
            popStyle()
        }
    }

    val PLAYER = Player()
    val BALL = Ball()

    override fun settings() {
        size(500,700)
    }

    override fun setup() {
        frameRate(30f)
        PLAYER.PLAYER_POS.y = height.toFloat() - 100f
    }


    override fun draw() {
        background(0)
        stroke(255f,255f,255f)
        strokeWeight(3f)

        /* Ball 制御 */
        BALL.draw()

        /* Player 制御 */
        PLAYER.draw()

    }

    fun run(){
        return main(Main::class.java.simpleName)
    }
}

fun main() : Unit = Main().run()