/*
 * This is a conversion from the https://github.com/raysan5/raylib/blob/master/examples/core/core_basic_window.c example program.
 */

import com.occultusterra.raylib.RayLibNative;

public class FirstWindow {
    public static void main( String[] args ) {
       	RayLibNative.INSTANCE.InitWindow(800, 450, "jRaylib [core] example - basic window");
    	RayLibNative.INSTANCE.SetTargetFPS(30);
    	
    	while (!RayLibNative.INSTANCE.WindowShouldClose()) {
    		RayLibNative.INSTANCE.BeginDrawing();
    		RayLibNative.INSTANCE.ClearBackground(RayLibNative.Colors.BLOOD);
    		RayLibNative.INSTANCE.DrawText("Congrats! You created your first window!", 190, 200, 20, RayLibNative.Colors.COFFEE);
            RayLibNative.INSTANCE.EndDrawing();
    	}
    	RayLibNative.INSTANCE.CloseWindow();
    }
}