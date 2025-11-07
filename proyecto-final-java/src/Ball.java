import java.awt.*;
import java.util.*;

/**
 * Representa la pelota del juego de Ping Pong.
 * <p>
 * La clase {@code Ball} define la posición, dirección, velocidad y dibujo
 * de la pelota. Extiende {@link Rectangle} para aprovechar las propiedades
 * geométricas (x, y, ancho, alto) y facilitar la detección de colisiones.
 */
public class Ball extends Rectangle {

    /** Generador aleatorio para determinar dirección inicial. */
    Random random;

    /** Velocidad horizontal de la pelota. */
    int xVelocity;

    /** Velocidad vertical de la pelota. */
    int yVelocity;

    /** Velocidad inicial de la pelota. */
    int initialSpeed = 2;

    /**
     * Crea una nueva pelota con posición y tamaño iniciales.
     * <p>
     * La dirección inicial (en los ejes X e Y) se elige aleatoriamente.
     *
     * @param x      posición inicial en el eje X
     * @param y      posición inicial en el eje Y
     * @param width  ancho de la pelota
     * @param height alto de la pelota
     */
    Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();

        int randomXDirection = random.nextInt(2);
        if (randomXDirection == 0)
            randomXDirection--;
        setXDirection(randomXDirection * initialSpeed);

        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0)
            randomYDirection--;
        setYDirection(randomYDirection * initialSpeed);
    }

    /**
     * Define la dirección y velocidad horizontal de la pelota.
     *
     * @param randomXDirection valor que determina la dirección (positivo o
     *                         negativo)
     */
    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }

    /**
     * Define la dirección y velocidad vertical de la pelota.
     *
     * @param randomYDirection valor que determina la dirección (positivo o
     *                         negativo)
     */
    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }

    /**
     * Actualiza la posición de la pelota según sus velocidades actuales.
     */
    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    /**
     * Dibuja la pelota en la pantalla.
     *
     * @param g contexto gráfico donde se renderiza la pelota
     */
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, height, width);
    }
}
