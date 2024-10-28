package Viking;

import Doctrina.Canvas;
import Doctrina.Game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class VikingGame extends Game {
    private Player player;
    private GamePad gamePad;
    private World world;
    private Tree tree;

    private int soundCooldown;

    @Override
    protected void initialize() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        player.teleport(250, 250);
        world = new World();
        world.load();

        tree = new Tree();

        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    getClass().getClassLoader().getResourceAsStream("audios/music.wav")
            );
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }

        player.update();

        soundCooldown--;
        if (soundCooldown < 0) {
            soundCooldown = 0;
        }

        if (gamePad.isFirePressed() && soundCooldown == 0) {
            soundCooldown = 100;

            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                        getClass().getClassLoader().getResourceAsStream("audios/fire.wav")
                );
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void draw(Canvas canvas) {
        world.draw(canvas);

        // Tree Height - Switch Layer
        // 80 - 28 (max layer) = 52
        if (player.getY() < tree.getY() + 52) {
            player.draw(canvas);
            tree.draw(canvas);
        } else {
            tree.draw(canvas);
            player.draw(canvas);
        }
    }
}
