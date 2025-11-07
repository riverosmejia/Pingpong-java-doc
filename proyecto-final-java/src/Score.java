import java.awt.*;

/**
 * La clase Score gestiona y dibuja el marcador del juego Pong.
 * Muestra las puntuaciones de ambos jugadores y la línea divisoria central.
 */
public class Score extends Rectangle {

    /** Ancho total del área de juego. */
    static int GAME_WIDTH;

    /** Alto total del área de juego. */
    static int GAME_HEIGHT;

    /** Puntuación del jugador 1. */
    int player1;

    /** Puntuación del jugador 2. */
    int player2;

    /**
     * Crea una instancia de Score y define el tamaño del área de juego.
     *
     * @param GAME_WIDTH  ancho del área de juego
     * @param GAME_HEIGHT alto del área de juego
     */
    Score(int GAME_WIDTH, int GAME_HEIGHT) {
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }

    /**
     * Dibuja el marcador en pantalla, incluyendo la línea central
     * y las puntuaciones de los jugadores.
     *
     * @param g el contexto gráfico donde se dibuja el marcador
     */
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));

        // Línea divisoria central
        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);

        // Puntuaciones de los jugadores
        g.drawString(
                String.valueOf(player1 / 10) + String.valueOf(player1 % 10),
                (GAME_WIDTH / 2) - 85,
                50);
        g.drawString(
                String.valueOf(player2 / 10) + String.valueOf(player2 % 10),
                (GAME_WIDTH / 2) + 20,
                50);
    }
}
