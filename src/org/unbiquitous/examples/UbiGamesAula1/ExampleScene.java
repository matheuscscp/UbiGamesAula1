package org.unbiquitous.examples.UbiGamesAula1;

import java.util.logging.Level;

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
import org.unbiquitous.uos.core.UOSLogging;

public class ExampleScene extends GameObjectTreeScene {
  public ExampleScene() {
    UOSLogging.setLevel(Level.ALL);
    add(new Fundo(assets));
    add(new Eatles(assets));
    add(new ComponentObject(assets));
  }
}

class Fundo extends GameObject {
  private Sprite background;
  private Screen screen;
  
  public Fundo(AssetManager assets) {
    background = assets.newSprite("img/background.png");
    screen = GameComponents.get(Screen.class);
  }

  @Override
  protected void update() {
    if (screen.isCloseRequested())
      GameComponents.get(Game.class).quit();
    
  }

  @Override
  protected void render(GameRenderers renderers) {
    renderers.put(0, new Runnable() {
      public void run() {
        background.render(screen, -300, -300, Corner.TOP_LEFT);
      }
    });
  }

  @Override
  protected void wakeup(Object... args) {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void destroy() {
    // TODO Auto-generated method stub
    
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

  @Override
  protected void render(GameRenderers renderers) {
    renderers.put(10, new Runnable() {
      public void run() {
        avatar.render(screen, position.x, position.y, null, 0.75f, angle, 3f, 3f);
      }
    });
  }

  @Override
  protected void wakeup(Object... args) {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void destroy() {
    // TODO Auto-generated method stub
    
  }
}

class ComponentObject extends ComponentGameObject {
  public ComponentObject(AssetManager assets) {
    addComponent(new Space2D(this));
    addComponent(new Renderer(this, assets));
    addComponent(new CharacterController(this));
  }
}

class Space2D extends GameObjectComponent {

  public Space2D(ComponentGameObject obj) {
    super(obj);
    object.write("x", 400.0f);
    object.write("y", 300.0f);
  }
  
  @Override
  protected void update() {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void wakeup(Object... args) {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void destroy() {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void handle(String field, Object value) {
    // TODO Auto-generated method stub
    
  }
  
}

class Renderer extends GameObjectComponent {

  Animation anim;

  public Renderer(ComponentGameObject obj, AssetManager assets) {
    super(obj);
    anim = assets.newAnimation("img/eatles_blink.png", 4, 12);
  }
  
  @Override
  protected void update() {
    render(5, new Runnable() {
      public void run() {
        anim.render(GameComponents.get(Screen.class), object.read("x", 0.0f), object.read("y", 0.0f));
      }
    });
  }

  @Override
  protected void wakeup(Object... args) {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void destroy() {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void handle(String field, Object value) {
    // TODO Auto-generated method stub
    
  }
  
}

class CharacterController extends GameObjectComponent {

  KeyboardSource keyboard;
  
  public CharacterController(ComponentGameObject object) {
    super(object);
    keyboard = GameComponents.get(Screen.class).getKeyboard();
  }

  @Override
  protected void update() {
    float x = object.read("x", 0.0f);
    float y = object.read("y", 0.0f);
    float speed = 150.0f;
    if (keyboard.getKey(Keyboard.KEY_W)) {
      y -= speed*GameComponents.get(DeltaTime.class).getDT();
    }
    if (keyboard.getKey(Keyboard.KEY_S)) {
      y += speed*GameComponents.get(DeltaTime.class).getDT();
    }
    if (keyboard.getKey(Keyboard.KEY_A)) {
      x -= speed*GameComponents.get(DeltaTime.class).getDT();
    }
    if (keyboard.getKey(Keyboard.KEY_D)) {
      x += speed*GameComponents.get(DeltaTime.class).getDT();
    }
    object.write("x", x);
    object.write("y", y);
  }

  @Override
  protected void wakeup(Object... args) {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void destroy() {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void handle(String field, Object value) {
    // TODO Auto-generated method stub
    
  }
  
}
