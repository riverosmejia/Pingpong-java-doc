import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Panel principal del juego de Ping Pong.
 * <p>
 * Esta clase contiene toda la lógica del juego: creación de objetos,
 * renderizado,
 * control del bucle principal (thread) y detección de colisiones.
 * Extiende {@link JPanel} e implementa {@link Runnable} para permitir
 * la ejecución en un hilo separado.
 */
public class GamePanel extends JPanel implements Runnable {

    /** Ancho de la ventana del juego. */
    static final int GAME_WIDTH = 1000;

    /** Alto de la ventana del juego (calculado en proporción). */
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));

    /** Dimensiones del área de juego. */
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);

    /** Diámetro de la pelota. */
    static final int BALL_DIAMETER = 20;

    /** Ancho de las paletas. */
    static final int PADDLE_WIDTH = 25;

    /** Alto de las paletas. */
    static final int PADDLE_HEIGHT = 100;

    /** Hilo principal del juego. */
    Thread gameThread;

    /** Imagen usada para el doble buffer. */
    Image image;

    /** Contexto gráfico asociado al buffer. */
    Graphics graphics;

    /** Paleta del jugador 1. */
    Paddle paddle1;

    /** Paleta del jugador 2. */
    Paddle paddle2;

    /** Pelota del juego. */
    Ball ball;

    /** Marcador de puntuaciones. */
    Score score;

    /**
     * Constructor del panel del juego.
     * <p>
     * Inicializa los objetos del juego (paletas, pelota y marcador),
     * configura los listeners del teclado y arranca el hilo principal.
     */
    GamePanel() {
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Crea una nueva pelota en el centro de la pantalla.
     */
    public void newBall() {
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2),
                (GAME_HEIGHT / 2) - (BALL_DIAMETER / 2),
                BALL_DIAMETER, BALL_DIAMETER);
    }

    /**
     * Crea las dos paletas en sus posiciones iniciales.
     */
    public void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2),
                PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH,
                (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2),
                PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    /**
     * Dibuja el contenido en pantalla usando doble buffer.
     *
     * @param g contexto gráfico del componente
     */
    @Override
    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    /**
     * Dibuja los elementos del juego: fondo, paletas, pelota y marcador.
     *
     * @param g contexto gráfico donde dibujar
     */
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Actualiza la posición de los objetos móviles (paletas y pelota).
     */
    public void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    /**
     * Verifica y maneja las colisiones entre los objetos del juego.
     * <p>
     * Incluye colisiones con los bordes, las paletas y los límites
     * superior e inferior de la pantalla, además del manejo de puntuación.
     */
    public void checkCollision() {
        // Rebote vertical de la pelota
        if (ball.y <= 0)
            ball.setYDirection(-ball.yVelocity);
        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER)
            ball.setYDirection(-ball.yVelocity);

        // Rebote con la paleta izquierda
        if (ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            ball.yVelocity += ball.yVelocity > 0 ? 1 : -1;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        // Rebote con la paleta derecha
        if (ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            ball.yVelocity += ball.yVelocity > 0 ? 1 : -1;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        // Limitar movimiento de paletas
        if (paddle1.y <= 0)
            paddle1.y = 0;
        if (paddle1.y >= GAME_HEIGHT - PADDLE_HEIGHT)
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        if (paddle2.y <= 0)
            paddle2.y = 0;
        if (paddle2.y >= GAME_HEIGHT - PADDLE_HEIGHT)
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;

        // Anotar puntos y reiniciar posiciones
        if (ball.x <= 0) {
            score.player2++;
            newPaddles();
            newBall();
        }

        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            score.player1++;
            newPaddles();
            newBall();
        }
    }

    /**
     * Bucle principal del juego.
     * <p>
     * Controla el tiempo de actualización de frames (60 FPS),
     * ejecuta los movimientos y repinta la pantalla.
     */
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    /**
     * Inicia el hilo del juego.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Clase interna que gestiona los eventos del teclado.
     * <p>
     * Permite controlar las paletas de ambos jugadores mediante teclas.
     */
    public class AL extends KeyAdapter {

        /**
         * Detecta cuando una tecla es presionada.
         *
         * @param e evento del teclado
         */
        @Override
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        /**
         * Detecta cuando una tecla es liberada.
         *
         * @param e evento del teclado
         */
        @Override
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
