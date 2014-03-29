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
import org.unbiquitous.uImpala.util.observer.Event;
import org.unbiquitous.uImpala.util.observer.Observation;
import org.unbiquitous.uImpala.util.observer.Subject;

public class Menu extends GameScene {

  private Sprite sprite;
  private Screen screen;
  private Text text;
  private MouseSource screenMouse;
  
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
  }
  
  @Override
  protected void update() {
    
  }

  @Override
  protected void render() {
    sprite.render(0, 0, screen);
    text.render(screen, 400, 300);
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
    int x = e.getX(), y = e.getY();
    if (x >= 400 - text.getWidth()/2 &&
        x < 400 + text.getWidth()/2 &&
        y >= 300 - text.getHeight()/2 &&
        y < 300 + text.getHeight()/2) {
      GameComponents.get(Game.class).change(new Level());
    }
  }
}
