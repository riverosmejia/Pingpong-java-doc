import java.awt.*;
import java.awt.event.*;

/**
 * Representa una paleta (paddle) del juego de Ping Pong.
 * <p>
 * Cada paleta puede moverse verticalmente y responde a las teclas asignadas
 * según el jugador (1 o 2). Extiende {@link Rectangle} para reutilizar
 * las propiedades geométricas y facilitar la detección de colisiones.
 */
public class Paddle extends Rectangle {

    /** Identificador del jugador (1 o 2). */
    int id;

    /** Velocidad vertical de la paleta. */
    int yVelocity;

    /** Velocidad base del movimiento. */
    int speed = 10;

    /**
     * Crea una nueva paleta en la posición indicada.
     *
     * @param x             posición inicial en el eje X
     * @param y             posición inicial en el eje Y
     * @param PADDLE_WIDTH  ancho de la paleta
     * @param PADDLE_HEIGHT alto de la paleta
     * @param id            identificador del jugador (1 para izquierda, 2 para
     *                      derecha)
     */
    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }

    /**
     * Detecta las teclas presionadas y ajusta la dirección de la paleta.
     * <p>
     * Jugador 1 usa las teclas W (arriba) y S (abajo).
     * Jugador 2 usa las teclas ↑ (arriba) y ↓ (abajo).
     *
     * @param e evento de teclado capturado
     */
    public void keyPressed(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W)
                    setYDirection(-speed);
                if (e.getKeyCode() == KeyEvent.VK_S)
                    setYDirection(speed);
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP)
                    setYDirection(-speed);
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                    setYDirection(speed);
                break;
        }
    }

    /**
     * Detecta cuando una tecla es liberada y detiene el movimiento vertical.
     *
     * @param e evento de teclado capturado
     */
    public void keyReleased(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W)
                    setYDirection(0);
                if (e.getKeyCode() == KeyEvent.VK_S)
                    setYDirection(0);
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP)
                    setYDirection(0);
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                    setYDirection(0);
                break;
        }
    }

    /**
     * Establece la dirección vertical actual de la paleta.
     *
     * @param yDirection velocidad vertical (positiva hacia abajo, negativa hacia
     *                   arriba)
     */
    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    /**
     * Actualiza la posición vertical de la paleta según su velocidad actual.
     */
    public void move() {
        y = y + yVelocity;
    }

    /**
     * Dibuja la paleta en pantalla.
     * <p>
     * La paleta del jugador 1 es azul y la del jugador 2 es roja.
     *
     * @param g contexto gráfico donde se renderiza la paleta
     */
    public void draw(Graphics g) {
        if (id == 1)
            g.setColor(Color.blue);
        else
            g.setColor(Color.red);
        g.fillRect(x, y, width, height);
    }
}
