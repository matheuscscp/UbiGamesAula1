package org.unbiquitous.examples.UbiGamesAula1;

import java.util.ArrayList;

import org.unbiquitous.uImpala.engine.core.Game;
import org.unbiquitous.uImpala.engine.core.GameSettings;
import org.unbiquitous.uImpala.engine.io.MouseManager;
import org.unbiquitous.uImpala.engine.io.ScreenManager;

public class Main {
  @SuppressWarnings("serial")
  public static void main(String[] args) {
    Game.run(new GameSettings () {{
      put("first_scene", Menu.class);
      put("input_managers", new ArrayList<Class<?>>() {{
        add(MouseManager.class);
      }});
      put("output_managers", new ArrayList<Class<?>>() {{
        add(ScreenManager.class);
      }});
    }});
  }
}
