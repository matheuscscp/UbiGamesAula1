package org.unbiquitous.examples.UbiGamesAula1;

import java.util.Arrays;

import org.unbiquitous.uImpala.engine.core.Game;
import org.unbiquitous.uImpala.engine.core.GameSettings;
import org.unbiquitous.uImpala.engine.io.MouseManager;
import org.unbiquitous.uImpala.engine.io.ScreenManager;

public class Main {
  @SuppressWarnings({ "serial", "unchecked" })
  public static void main(String[] args) {
    Game.run(new GameSettings () {{
      put("first_scene", Menu.class);
      put("input_managers", Arrays.asList(
        MouseManager.class
      ));
      put("output_managers", Arrays.asList(
          ScreenManager.class
        ));
    }});
  }
}
