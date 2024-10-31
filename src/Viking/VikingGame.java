package Viking;

import Doctrina.Canvas;
import Doctrina.Game;
import Doctrina.RenderingEngine;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class VikingGame extends Game {
    private Player player;
    private GamePad gamePad;
    private World world;
    private Tree tree;
    private Tree tree2;

    private int soundCooldown;

    @Override
    protected void initialize() {
        GameConfig.setDebugEnabled(false);

        gamePad = new GamePad();
        player = new Player(gamePad);
        player.teleport(250, 250);
        world = new World();
        world.load();

        tree = new Tree(300, 300);
        tree2 = new Tree(600, 150);

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

        RenderingEngine.getInstance().getScreen().fullscreen();
        //RenderingEngine.getInstance().getScreen().hideCursor();
    }

    @Override
    protected void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }

        player.update();
        if (player.getY() < tree.getY() + 52) {
            tree.blockadeFromTop();
        } else {
            tree.blockadeFromBottom();
        }

        soundCooldown--;
        if (soundCooldown < 0) {
            soundCooldown = 0;
        }

        if (gamePad.isFirePressed() && soundCooldown == 0) {
//            GameConfig.setDebugEnabled(true);
            soundCooldown = 100;
            SoundEffect.MURLOC.play();
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

        // TODO: Refactor to common place
//        if (player.getY() < tree2.getY() + 52) {
//            player.draw(canvas);
//            tree2.draw(canvas);
//        } else {
//            tree2.draw(canvas);
//            player.draw(canvas);
//        }
    }
}
