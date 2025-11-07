import javax.swing.JFrame;

/**
 * Clase principal del juego Ping Pong.
 * <p>
 * Esta clase crea la ventana principal y
 * lanza el hilo del juego.
 */
public class Main {

    /**
     * Método principal del programa.
     * Crea la ventana (JFrame) y el panel del juego (GamePanel),
     * y luego inicia el hilo del juego.
     *
     * @param args argumentos de línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ping Pong");
        GamePanel panel = new GamePanel();
        /**
         * Configura la ventana
         */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        // Inicia el bucle del juego
        panel.startGameThread();
    }
}
