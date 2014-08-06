package org.unbiquitous.examples.UbiGamesAula1;

import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.unbiquitous.uImpala.engine.asset.Animation;
import org.unbiquitous.uImpala.engine.asset.AssetManager;
import org.unbiquitous.uImpala.engine.asset.Sprite;
import org.unbiquitous.uImpala.engine.core.ComponentGameObject;
import org.unbiquitous.uImpala.engine.core.Game;
import org.unbiquitous.uImpala.engine.core.GameComponents;
import org.unbiquitous.uImpala.engine.core.GameObject;
import org.unbiquitous.uImpala.engine.core.GameObjectComponent;
import org.unbiquitous.uImpala.engine.core.GameObjectTreeScene;
import org.unbiquitous.uImpala.engine.core.GameRenderers;
import org.unbiquitous.uImpala.engine.io.KeyboardSource;
import org.unbiquitous.uImpala.engine.io.Screen;
import org.unbiquitous.uImpala.engine.time.DeltaTime;
import org.unbiquitous.uImpala.util.Corner;

public class ExampleScene extends GameObjectTreeScene {
  public ExampleScene() {
    add(new Fundo(getAssets()));
    add(new Eatles(getAssets()));
    add(new ComponentGameObject(
        new Space2D(),
        new Renderer(getAssets()),
        new CharacterController()
    ));
  }
}

class Fundo extends GameObject {
  private Sprite background;
  private Screen screen;
  
  public Fundo(AssetManager assets) {
    background = assets.newSprite("img/background.png");
    screen = GameComponents.get(Screen.class);
  }
  
  protected void update() {
    if (screen.isCloseRequested()) {
      GameComponents.get(Game.class).quit();
    }
  }
  
  protected void render(GameRenderers renderers) {
    renderers.put(0, new Runnable() {
      public void run() {
        background.render(screen, -300, -300, Corner.TOP_LEFT);
      }
    });
  }
}

class Point {
  float x, y;
  public Point(float x, float y) {
    super();
    this.x = x;
    this.y = y;
  }
}

class Eatles extends GameObject {
  private Animation avatar;
  private Screen screen;
  private Point position;
  private DeltaTime dt;
  private float angle;
  
  public Eatles(AssetManager assets) {
    avatar = assets.newAnimation("img/eatles_blink.png", 4, 12);
    screen = GameComponents.get(Screen.class);
    position = new Point(400, 300);
    dt = GameComponents.get(DeltaTime.class);
    angle = 0f;
  }
  
  protected void update() {
    Point target = new Point(screen.getMouse().getX(), screen.getMouse().getY());
    
    Point speed = new Point(target.x - position.x, target.y - position.y);
    
    float speedGain = 5f;
    
    position.x += speedGain*speed.x*dt.getDT();
    position.y += speedGain*speed.y*dt.getDT();
    
    angle += 50f*dt.getDT();
  }
  
  protected void render(GameRenderers renderers) {
    renderers.put(10, new Runnable() {
      public void run() {
        avatar.render(screen, position.x, position.y, null, 0.75f, angle, 3f, 3f);
      }
    });
  }
}

class Space2D extends GameObjectComponent {
  protected String family() {
    return "spatial";
  }
  
  protected void init() {
    object.write("pos", new Point(400, 300));
  }
  
  protected void update() {
    
  }
}

class Renderer extends GameObjectComponent {
  Animation anim;
  
  public Renderer(AssetManager assets) {
    anim = assets.newAnimation("img/eatles_blink.png", 4, 12);
  }
  
  protected String family() {
    return "renderer";
  }
  
  protected List<String> depends() {
    return Arrays.asList("spatial");
  }
  
  protected void init() {
    
  }
  
  protected void update() {
    final Point pos = object.read("pos", new Point(0, 0));
    render(5, new Runnable() {
      public void run() {
        anim.render(GameComponents.get(Screen.class), pos.x, pos.y);
      }
    });
  }
}

class CharacterController extends GameObjectComponent {
  KeyboardSource keyboard;
  
  public CharacterController() {
    keyboard = GameComponents.get(Screen.class).getKeyboard();
  }
  
  protected String family() {
    return "controller";
  }
  
  protected List<String> depends() {
    return Arrays.asList("spatial");
  }
  
  protected void init() {
    
  }
  
  protected void update() {
    final Point pos = object.read("pos", new Point(0, 0));
    float speed = 150.0f;
    if (keyboard.getKey(Keyboard.KEY_W)) {
      pos.y -= speed*GameComponents.get(DeltaTime.class).getDT();
    }
    if (keyboard.getKey(Keyboard.KEY_S)) {
      pos.y += speed*GameComponents.get(DeltaTime.class).getDT();
    }
    if (keyboard.getKey(Keyboard.KEY_A)) {
      pos.x -= speed*GameComponents.get(DeltaTime.class).getDT();
    }
    if (keyboard.getKey(Keyboard.KEY_D)) {
      pos.x += speed*GameComponents.get(DeltaTime.class).getDT();
    }
    object.write("pos", pos);
  }
}
