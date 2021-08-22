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
            fill(0.33f,100f,100f)
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
            fill(0f,100f,100f)
            // 壁判定
            if(BALL_POS.x <= BALL_R / 2f || BALL_POS.x >= width.toFloat() - (BALL_R / 2f)) BALL_VELOCITY.x *= -1
            if(BALL_POS.y <= BALL_R / 2f) BALL_VELOCITY.y *= -1
            if(BALL_POS.y >= height.toFloat() - (BALL_R / 2f)) {
                BALL_VELOCITY.y *= -1
            }
            ellipseMode(CENTER)
            ellipse(BALL_POS.x,BALL_POS.y,BALL_R,BALL_R)
            BALL_POS.add(BALL_VELOCITY)
            popStyle()
        }
    }

    inner class Block{
        private val BLOCK_POS = PVector(0f,0f)
        val COLOR = random(1f)
        val BLOCK_EXI = true
        fun settingPos(x:Float,y:Float){
            BLOCK_POS.x = x
            BLOCK_POS.y = y
        }

        fun draw(){
            if(BLOCK_EXI) {
                pushStyle()
                fill(COLOR, 40f, 100f)
                rect(BLOCK_POS.x, BLOCK_POS.y, 50f, 20f)
                popStyle()
            }
        }
    }

    val PLAYER = Player()
    val BALL = Ball()
    val BLOCKS = Array(5){Array(10){Block()} }

    override fun settings() {
        size(500,700)
    }

    override fun setup() {
        frameRate(30f)
        colorMode(HSB,1f,100f,100f)
        PLAYER.PLAYER_POS.y = height.toFloat() - 100f
    }


    override fun draw() {
        background(0)
        stroke(0f,0f,100f)
        strokeWeight(3f)

        BALL.draw()
        PLAYER.draw()

        if(Math.abs(PLAYER.PLAYER_POS.y - BALL.BALL_POS.y) <= (BALL.BALL_R / 2f) + 5f
            && Math.abs((PLAYER.PLAYER_POS.x + 25f) - (BALL.BALL_POS.x + BALL.BALL_R)) <= 50
        ){
            BALL.BALL_VELOCITY.y = -1 * Math.abs(BALL.BALL_VELOCITY.y)
        }
        for(i in 0 until BLOCKS.size){
            for(j in 0 until BLOCKS[0].size){
                BLOCKS[i][j].settingPos(50f * j,20f * i + 10f)
                BLOCKS[i][j].draw()
            }
        }

    }

    fun run(){
        return main(Main::class.java.simpleName)
    }
}

fun main() : Unit = Main().run()