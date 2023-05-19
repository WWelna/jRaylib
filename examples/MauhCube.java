import com.occultusterra.raylib.RayLibNative;
import com.occultusterra.raylib.structs.Camera3D;
import com.occultusterra.raylib.structs.Vector3;

public class MauhCube {
    public static void main( String[] args ) throws Exception {
       	RayLibNative.INSTANCE.InitWindow(800, 450, "jRayLib - Mauh Cube!");
    	RayLibNative.INSTANCE.SetTargetFPS(60);
    	
    	Camera3D.ByValue camera = new Camera3D.ByValue(new Vector3.ByValue(20, 20, 20), new Vector3.ByValue(0,0,0), new Vector3.ByValue(0,1,0), 45, 0);
    	RayLibNative.INSTANCE.SetCameraMode(camera, RayLibNative.CameraMode.CAMERA_FREE);
    	
    	Vector3.ByValue cubeloc = new Vector3.ByValue(2,4,2);
    	Vector3.ByValue cubesize = new Vector3.ByValue(4,4,4);
    	
    	while (!RayLibNative.INSTANCE.WindowShouldClose()) {
    		RayLibNative.INSTANCE.UpdateCamera(camera.getReference());
    		
    		RayLibNative.INSTANCE.BeginDrawing();
    		RayLibNative.INSTANCE.ClearBackground(RayLibNative.Colors.RAYWHITE);
    		
    		RayLibNative.INSTANCE.BeginMode3D(camera);
    		RayLibNative.INSTANCE.DrawCubeV(cubeloc, cubesize, RayLibNative.Colors.BLOOD);
    		RayLibNative.INSTANCE.DrawCubeWiresV(cubeloc, cubesize, RayLibNative.Colors.GOLD);
    		RayLibNative.INSTANCE.DrawGrid(25, 1);
    		RayLibNative.INSTANCE.EndMode3D();
    		
    		RayLibNative.INSTANCE.DrawFPS(15, 15);
    		RayLibNative.INSTANCE.EndDrawing();
    	}
    	RayLibNative.INSTANCE.CloseWindow();
    }
}
