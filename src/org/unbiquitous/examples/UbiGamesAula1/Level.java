package org.unbiquitous.examples.UbiGamesAula1;

import org.unbiquitous.uImpala.engine.asset.Animation;
import org.unbiquitous.uImpala.engine.asset.AssetManager;
import org.unbiquitous.uImpala.engine.asset.Sprite;
import org.unbiquitous.uImpala.engine.core.ContainerScene;
import org.unbiquitous.uImpala.engine.core.Game;
import org.unbiquitous.uImpala.engine.core.GameComponents;
import org.unbiquitous.uImpala.engine.core.GameObject;
import org.unbiquitous.uImpala.engine.core.GameRenderers;
import org.unbiquitous.uImpala.engine.io.Screen;
import org.unbiquitous.uImpala.engine.time.DeltaTime;

public class Level extends ContainerScene {
  public Level() {
    add(new Fundo(assets));
    add(new Eatles(assets));
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
    avatar = new Animation(assets, "img/eatles_blink.png", 4, 12);
    screen = GameComponents.get(Screen.class);
    position = new Point(400, 300);
    dt = GameComponents.get(DeltaTime.class);
    angle = 0f;
  }
  
  protected void update() {
    if (screen.isCloseRequested())
      GameComponents.get(Game.class).quit();
    
    Point target = new Point(screen.getMouse().getX(), screen.getMouse().getY());
    
    Point speed = new Point(target.x - position.x, target.y - position.y);
    
    float speedGain = 5f;
    
    position.x += speedGain*speed.x*dt.getDT();
    position.y += speedGain*speed.y*dt.getDT();
    
    angle += 50f*dt.getDT();
  }

  @Override
  protected void render(GameRenderers renderers) {
    avatar.render(screen, position.x, position.y, angle, 3f, 3f, 0.75f);
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

class Fundo extends GameObject {
  private Sprite background;
  private Screen screen;
  
  public Fundo(AssetManager assets) {
    background = new Sprite(assets, "img/background.png");
    screen = GameComponents.get(Screen.class);
  }

  @Override
  protected void update() {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void render(GameRenderers renderers) {
    background.render(-300, -300, screen);
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