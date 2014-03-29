package org.unbiquitous.examples.UbiGamesAula1;

import org.unbiquitous.uImpala.engine.asset.Sprite;
import org.unbiquitous.uImpala.engine.asset.Text;
import org.unbiquitous.uImpala.engine.core.Game;
import org.unbiquitous.uImpala.engine.core.GameComponents;
import org.unbiquitous.uImpala.engine.core.GameScene;
import org.unbiquitous.uImpala.engine.io.MouseEvent;
import org.unbiquitous.uImpala.engine.io.MouseSource;
import org.unbiquitous.uImpala.engine.io.Screen;
import org.unbiquitous.uImpala.engine.io.ScreenManager;
import org.unbiquitous.uImpala.engine.time.DeltaTime;
import org.unbiquitous.uImpala.util.Corner;
import org.unbiquitous.uImpala.util.math.Rectangle;
import org.unbiquitous.uImpala.util.observer.Event;
import org.unbiquitous.uImpala.util.observer.Observation;
import org.unbiquitous.uImpala.util.observer.Subject;

public class Menu extends GameScene {

  private Sprite sprite;
  private Screen screen;
  private Text text;
  private MouseSource screenMouse;
  private float angle;
  
  public Menu() {
    screen = GameComponents.get(ScreenManager.class).create();
    screen.open("UbiGamesAula1", 800, 600, false, null);
    screen.initGL();
    GameComponents.put(Screen.class, screen);
    sprite = new Sprite(assets, "img/background.png");
    text = new Text(assets, "ttf/default.ttf", "Jogar");
    screenMouse = screen.getMouse();
    screenMouse.connect(MouseSource.EVENT_BUTTON_DOWN, new Observation(this, "buttonDown"));
    text.options(null, 60f, null, null);
    angle = 0f;
  }
  
  @Override
  protected void update() {
    angle += 180f*GameComponents.get(DeltaTime.class).getDT();
  }

  @Override
  protected void render() {
    sprite.render(screen, 0, 0, Corner.TOP_LEFT);
    text.render(screen, 400, 300, null, 1.0f, angle);
  }

  @Override
  protected void wakeup(Object... args) {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void destroy() {
    // TODO Auto-generated method stub
    
  }
  
  protected void buttonDown(Event event, Subject subject) {
    MouseEvent e = (MouseEvent)event;
    if (e.isInside(new Rectangle(400, 300, text.getWidth(), text.getHeight(), angle))) {
      GameComponents.get(Game.class).change(new Level());
    }
  }
}
