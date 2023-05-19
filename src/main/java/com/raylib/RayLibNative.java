/*
* Copyright (c) 2021 William Welna (wwelna@occultusterra.com)
*
* This software is provided "as-is", without any express or implied warranty. In no event 
* will the authors be held liable for any damages arising from the use of this software.
*
* Permission is granted to anyone to use this software for any purpose, including commercial 
* applications, and to alter it and redistribute it freely, subject to the following restrictions:
*
*  1. The origin of this software must not be misrepresented; you must not claim that you 
*  wrote the original software. If you use this software in a product, an acknowledgment 
*  in the product documentation would be appreciated but is not required.
*
*  2. Altered source versions must be plainly marked as such, and must not be misrepresented
*  as being the original software.
*
*  3. This notice may not be removed or altered from any source distribution. 
*/

package com.occultusterra.raylib;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Callback;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.PointerByReference;

import com.occultusterra.raylib.structs.*;
import com.occultusterra.raylib.structs.Color.ByValue;

public interface RayLibNative extends Library {
	RayLibNative INSTANCE = (RayLibNative)Native.load("libraylib.so.4.0.0", RayLibNative.class);
	
	/* Misc Defs */
	
	//public static final String RAYLIB_VERSION = (String)"4.0";
	//public static final float PI = (float)3.14159265358979323846f;
	//public static final float DEG2RAD = (float)(3.14159265358979323846f / 180.0f);
	//public static final float RAD2DEG = (float)(180.0f / 3.14159265358979323846f);
	
	/* MODULE CORE */
	
	void InitWindow(int width, int height, String title);
	boolean WindowShouldClose();
	void CloseWindow();
	boolean IsWindowReady();
	boolean IsWindowFullscreen();
	boolean IsWindowHidden();
	boolean IsWindowMinimized();
	boolean IsWindowMaximized();
	boolean IsWindowFocused();
	boolean IsWindowResized();
	boolean IsWindowState(int flag);
	void SetWindowState(int flags);
	void ClearWindowState(int flags);
	void ToggleFullscreen();
	void MaximizeWindow();
	void MinimizeWindow();
	void RestoreWindow();
	void SetWindowIcon(Image.ByValue image);
	void SetWindowTitle(String title);
	void SetWindowPosition(int x, int y);
	void SetWindowMonitor(int monitor);
	void SetWindowMinSize(int width, int height);
	void SetWindowSize(int width, int height);
	Pointer GetWindowHandle();
	int GetScreenWidth();
	int GetScreenHeight();
	int GetMonitorCount();
	int GetCurrentMonitor();
	Vector2.ByValue GetMonitorPosition(int monitor);
	int GetMonitorWidth(int monitor);
	int GetMonitorHeight(int monitor);
	int GetMonitorPhysicalWidth(int monitor);
	int GetMonitorPhysicalHeight(int monitor);
	int GetMonitorRefreshRate(int monitor);
	Vector2.ByValue GetWindowPosition();
	Vector2.ByValue GetWindowScaleDPI();
	String GetMonitorName(int monitor);
	void SetClipboardText(String text);
	String GetClipboardText();
	
	void ShowCursor();
	void HideCursor();
	boolean IsCursorHidden();
	void EnableCursor();
	void DisableCursor();
	boolean IsCursorOnScreen();

	void ClearBackground(Color.ByValue color);
	void BeginDrawing();
	void EndDrawing();
	void BeginMode2D(Camera2D.ByValue camera);
	void EndMode2D();
	void BeginMode3D(Camera3D.ByValue camera);
	void EndMode3D();
	void BeginTextureMode(RenderTexture.ByValue target);
	void EndTextureMode();
	void BeginShaderMode(Shader.ByValue shader);
	void EndShaderMode();
	void BeginBlendMode(int mode);
	void EndBlendMode();
	void BeginScissorMode(int x, int y, int width, int height);
	void EndScissorMode();
	void BeginVrStereoMode(VrStereoConfig.ByValue config);
	void EndVrStereoMode();
	
	VrStereoConfig.ByValue LoadVrStereoConfig(VrDeviceInfo.ByValue device);
	void UnloadVrStereoConfig(VrStereoConfig.ByValue config);

	Shader.ByValue LoadShader(String vsFileName, String fsFileName);
	Shader.ByValue LoadShaderFromMemory(String vsCode, String fsCode);
	int GetShaderLocation(Shader.ByValue shader, String uniformName);
	int GetShaderLocationAttrib(Shader.ByValue shader, String attribName);
	void SetShaderValue(Shader.ByValue shader, int locIndex, Pointer value, int uniformType);
	void SetShaderValueV(Shader.ByValue shader, int locIndex, Pointer value, int uniformType, int count);
	void SetShaderValueMatrix(Shader.ByValue shader, int locIndex, Matrix.ByValue mat);
	void SetShaderValueTexture(Shader.ByValue shader, int locIndex, Texture.ByValue texture);
	void UnloadShader(Shader.ByValue shader);

	Ray.ByValue GetMouseRay(Vector2.ByValue mousePosition, Camera2D.ByValue camera);
	Matrix.ByReference GetCameraMatrix(Camera2D.ByValue camera);
	Matrix.ByReference GetCameraMatrix2D(Camera2D.ByValue camera);
	Vector2.ByValue GetWorldToScreen(Vector3.ByValue position, Camera2D.ByValue camera);
	Vector2.ByValue GetWorldToScreenEx(Vector3.ByValue position, Camera2D.ByValue camera, int width, int height);
	Vector2.ByValue GetWorldToScreen2D(Vector2.ByValue position, Camera2D.ByValue camera);
	Vector2.ByValue GetScreenToWorld2D(Vector2.ByValue position, Camera2D.ByValue camera);

	void SetTargetFPS(int fps);
	int GetFPS();
	float GetFrameTime();
	double GetTime();

	int GetRandomValue(int min, int max);
	void SetRandomSeed(int seed);
	void TakeScreenshot(String fileName);
	void SetConfigFlags(int flags);

	void TraceLog(int logLevel, String text, Pointer va_list);
	void SetTraceLogLevel(int logLevel);
	Pointer MemAlloc(int size);
	Pointer MemRealloc(Pointer ptr, int size);
	void MemFree(Pointer ptr);

	interface TraceLogCallback extends Callback {
		void handler(int logLevel, String text, Pointer va_list);
	}
	
	interface LoadFileDataCallback extends Callback {
		String handler(String fileName, IntByReference bytesRead);
	}
	
	interface SaveFileDataCallback extends Callback {
		boolean handler(String fileName, Pointer data, IntByReference bytesToWrite);
	}
	
	interface LoadFileTextCallback extends Callback {
		String handler(String fileName);
	}
	
	interface SaveFileTextCallback extends Callback {
		boolean handler(String fileName, String text);
	}
	
	void SetTraceLogCallback(TraceLogCallback callback);
	void SetLoadFileDataCallback(LoadFileDataCallback callback);
	void SetSaveFileDataCallback(SaveFileDataCallback callback);
	void SetLoadFileTextCallback(LoadFileTextCallback callback);
	void SetSaveFileTextCallback(SaveFileTextCallback callback);

	/* We don't need this as high level file functions are in Java */
	/* 
	String LoadFileData(String fileName, IntByReference bytesRead);
	void UnloadFileData(String data);
	boolean SaveFileData(String fileName, Pointer data, int bytesToWrite);
	String LoadFileText(String fileName);
	void UnloadFileText(String text);
	boolean SaveFileText(String fileName, String text);
	boolean FileExists(String fileName);
	boolean DirectoryExists(String dirPath);
	boolean IsFileExtension(String fileName, String ext);
	String GetFileExtension(String fileName);
	String GetFileName(String filePath);
	String GetFileNameWithoutExt(String filePath);
	String GetDirectoryPath(String filePath);
	String GetPrevDirectoryPath(String dirPath);
	String GetWorkingDirectory();
	PointerByReference GetDirectoryFiles(String dirPath, IntByReference count);
	void ClearDirectoryFiles();
	boolean ChangeDirectory(String dir);
	boolean IsFileDropped();
	PointerByReference GetDroppedFiles(IntByReference count);
	void ClearDroppedFiles();
	long GetFileModTime(String fileName); */

	// This uses realloc, so use MemAlloc, and write to/from Pointer
	Pointer CompressData(Pointer data, int dataLength, IntByReference compDataLength);
	Pointer DecompressData(Pointer compData, int compDataLength, IntByReference dataLength);
	Pointer EncodeDataBase64(Pointer data, int dataLength, IntByReference outputLength);
	Pointer DecodeDataBase64(Pointer data, IntByReference outputLength);

	boolean SaveStorageValue(int position, int value);
	int LoadStorageValue(int position);

	void OpenURL(String url);

	boolean IsKeyPressed(int key);
	boolean IsKeyDown(int key);
	boolean IsKeyReleased(int key);
	boolean IsKeyUp(int key);
	void SetExitKey(int key);
	int GetKeyPressed();
	int GetCharPressed();

	boolean IsGamepadAvailable(int gamepad);
	String GetGamepadName(int gamepad);
	boolean IsGamepadButtonPressed(int gamepad, int button);
	boolean IsGamepadButtonDown(int gamepad, int button);
	boolean IsGamepadButtonReleased(int gamepad, int button);
	boolean IsGamepadButtonUp(int gamepad, int button);
	int GetGamepadButtonPressed();
	int GetGamepadAxisCount(int gamepad);
	float GetGamepadAxisMovement(int gamepad, int axis);
	int SetGamepadMappings(String mappings);

	boolean IsMouseButtonPressed(int button);
	boolean IsMouseButtonDown(int button);
	boolean IsMouseButtonReleased(int button);
	boolean IsMouseButtonUp(int button);
	int GetMouseX();
	int GetMouseY();
	Vector2.ByValue GetMousePosition();
	Vector2.ByValue GetMouseDelta();
	void SetMousePosition(int x, int y);
	void SetMouseOffset(int offsetX, int offsetY);
	void SetMouseScale(float scaleX, float scaleY);
	float GetMouseWheelMove();
	void SetMouseCursor(int cursor);

	int GetTouchX();
	int GetTouchY();
	Vector2.ByValue GetTouchPosition(int index);
	int GetTouchPointId(int index);
	int GetTouchPointCount();

	void SetGesturesEnabled(int flags);
	boolean IsGestureDetected(int gesture);
	int GetGestureDetected();
	float GetGestureHoldDuration();
	Vector2.ByValue GetGestureDragVector();
	float GetGestureDragAngle();
	Vector2.ByValue GetGesturePinchVector();
	float GetGesturePinchAngle();

	void SetCameraMode(Camera3D.ByValue camera, int mode);
	void UpdateCamera(Camera3D.ByReference camera);

	void SetCameraPanControl(int keyPan);
	void SetCameraAltControl(int keyAlt);
	void SetCameraSmoothZoomControl(int keySmoothZoom);
	void SetCameraMoveControls(int keyFront, int keyBack, int keyRight, int keyLeft, int keyUp, int keyDown);

	/* MODULE SHAPES */
	
	void SetShapesTexture(Texture.ByValue texture, Rectangle.ByReference source);
	
	void DrawPixel(int posX, int posY, Color.ByValue color);
	void DrawPixelV(Vector2.ByValue position, Color.ByValue color);
	void DrawLine(int startPosX, int startPosY, int endPosX, int endPosY, Color.ByValue color);
	void DrawLineV(Vector2.ByValue startPos, Vector2.ByValue endPos, Color.ByValue color);
	void DrawLineEx(Vector2.ByValue startPos, Vector2.ByValue endPos, float thick, Color.ByValue color);
	void DrawLineBezier(Vector2.ByValue startPos, Vector2.ByValue endPos, float thick, Color.ByValue color);
	void DrawLineBezierQuad(Vector2.ByValue startPos, Vector2.ByValue endPos, Vector2.ByValue controlPos, float thick, Color.ByValue color);
	void DrawLineBezierCubic(Vector2.ByValue startPos, Vector2.ByValue endPos, Vector2.ByValue startControlPos, Vector2.ByValue endControlPos, float thick, Color.ByValue color);
	void DrawLineStrip(Vector2.ByReference[] points, int pointsCount, Color.ByValue color);
	void DrawCircle(int centerX, int centerY, float radius, Color.ByValue color);
	void DrawCircleSector(Vector2.ByValue center, float radius, float startAngle, float endAngle, int segments, Color.ByValue color);
	void DrawCircleSectorLines(Vector2.ByValue center, float radius, float startAngle, float endAngle, int segments, Color.ByValue color);
	void DrawCircleGradient(int centerX, int centerY, float radius, Color.ByValue color1, Color.ByValue color2);
	void DrawCircleV(Vector2.ByValue center, float radius, Color.ByValue color);
	void DrawCircleLines(int centerX, int centerY, float radius, Color.ByValue color);
	void DrawEllipse(int centerX, int centerY, float radiusH, float radiusV, Color.ByValue color);	
	void DrawEllipseLines(int centerX, int centerY, float radiusH, float radiusV, Color.ByValue color);
	void DrawRing(Vector2.ByValue center, float innerRadius, float outerRadius, float startAngle, float endAngle, int segments, Color.ByValue color);
	void DrawRingLines(Vector2.ByValue center, float innerRadius, float outerRadius, float startAngle, float endAngle, int segments, Color.ByValue color);
	void DrawRectangle(int posX, int posY, int width, int height, Color.ByValue color);
	void DrawRectangleV(Vector2.ByValue position, Vector2.ByValue size, Color.ByValue color);
	void DrawRectangleRec(Rectangle.ByValue rec, Color.ByValue color);
	void DrawRectanglePro(Rectangle.ByValue rec, Vector2.ByValue origin, float rotation, Color.ByValue color);
	void DrawRectangleGradientV(int posX, int posY, int width, int height, Color.ByValue color1, Color.ByValue color2);
	void DrawRectangleGradientH(int posX, int posY, int width, int height, Color.ByValue color1, Color.ByValue color2);
	void DrawRectangleGradientEx(Rectangle.ByValue rec, Color.ByValue col1, Color.ByValue col2, Color.ByValue col3, Color.ByValue col4);
	void DrawRectangleLines(int posX, int posY, int width, int height, Color.ByValue color);
	void DrawRectangleLinesEx(Rectangle.ByValue rec, int lineThick, Color.ByValue color);
	void DrawRectangleRounded(Rectangle.ByValue rec, float roundness, int segments, Color.ByValue color);
	void DrawRectangleRoundedLines(Rectangle.ByValue rec, float roundness, int segments, int lineThick, Color.ByValue color);
	void DrawTriangle(Vector2.ByValue v1, Vector2.ByValue v2, Vector2.ByValue v3, Color.ByValue color);
	void DrawTriangleLines(Vector2.ByValue v1, Vector2.ByValue v2, Vector2.ByValue v3, Color.ByValue color);
	void DrawTriangleFan(Vector2.ByReference[] points, int pointsCount, Color.ByValue color);
	void DrawTriangleStrip(Vector2.ByReference[] points, int pointsCount, Color.ByValue color);
	void DrawPoly(Vector2.ByValue center, int sides, float radius, float rotation, Color.ByValue color);
	void DrawPolyLines(Vector2.ByValue center, int sides, float radius, float rotation, Color.ByValue color);
	void DrawPolyLinesEx(Vector2.ByValue center, int sides, float radius, float rotation, float lineThick, Color.ByValue color);

	boolean CheckCollisionRecs(Rectangle.ByValue rec1, Rectangle.ByValue rec2);
	boolean CheckCollisionCircles(Vector2.ByValue center1, float radius1, Vector2.ByValue center2, float radius2);
	boolean CheckCollisionCircleRec(Vector2.ByValue center, float radius, Rectangle.ByValue rec);
	boolean CheckCollisionPointRec(Vector2.ByValue point, Rectangle.ByValue rec);
	boolean CheckCollisionPointCircle(Vector2.ByValue point, Vector2.ByValue center, float radius);
	boolean CheckCollisionPointTriangle(Vector2.ByValue point, Vector2.ByValue p1, Vector2.ByValue p2, Vector2.ByValue p3);
	boolean CheckCollisionLines(Vector2.ByValue startPos1, Vector2.ByValue endPos1, Vector2.ByValue startPos2, Vector2.ByValue endPos2, Vector2.ByReference collisionPoint);
	boolean CheckCollisionPointLine(Vector2.ByValue point, Vector2.ByValue p1, Vector2.ByValue p2, int threshold);
	Rectangle.ByValue GetCollisionRec(Rectangle.ByValue rec1, Rectangle.ByValue rec2);
	
	/* MODULE TEXTURES */

	Image.ByValue LoadImage(String fileName);
	Image.ByValue LoadImageRaw(String fileName, int width, int height, int format, int headerSize);
	Image.ByValue LoadImageAnim(String fileName, IntByReference frames);
	Image.ByValue LoadImageFromMemory(String fileType, byte[] fileData, int dataSize);
	Image.ByValue LoadImageFromTexture(Texture.ByValue texture);
	Image.ByValue LoadImageFromScreen();
	void UnloadImage(Image.ByValue image);
	boolean ExportImage(Image.ByValue image, String fileName);
	boolean ExportImageAsCode(Image.ByValue image, String fileName);

	Image.ByValue GenImageColor(int width, int height, Color.ByValue color);
	Image.ByValue GenImageGradientV(int width, int height, Color.ByValue top, Color.ByValue bottom);
	Image.ByValue GenImageGradientH(int width, int height, Color.ByValue left, Color.ByValue right);
	Image.ByValue GenImageGradientRadial(int width, int height, float density, Color.ByValue inner, Color.ByValue outer);
	Image.ByValue GenImageChecked(int width, int height, int checksX, int checksY, Color.ByValue col1, Color.ByValue col2);
	Image.ByValue GenImageWhiteNoise(int width, int height, float factor);
	Image.ByValue GenImageCellular(int width, int height, int tileSize);

	Image.ByValue ImageCopy(Image.ByValue image);
	Image.ByValue ImageFromImage(Image.ByValue image, Rectangle.ByValue rec);
	Image.ByValue ImageText(String text, int fontSize, Color.ByValue color);
	Image.ByValue ImageTextEx(Font.ByValue font, String text, float fontSize, float spacing, Color.ByValue tint);
	void ImageFormat(Image.ByReference image, int newFormat);
	void ImageToPOT(Image.ByReference image, Color.ByValue fill);
	void ImageCrop(Image.ByReference image, Rectangle.ByValue crop);
	void ImageAlphaCrop(Image.ByReference image, float threshold);
	void ImageAlphaClear(Image.ByReference image, Color.ByValue color, float threshold);
	void ImageAlphaMask(Image.ByReference image, Image.ByValue alphaMask);
	void ImageAlphaPremultiply(Image.ByReference image);
	void ImageResize(Image.ByReference image, int newWidth, int newHeight);
	void ImageResizeNN(Image.ByReference image, int newWidth,int newHeight);
	void ImageResizeCanvas(Image.ByReference image, int newWidth, int newHeight, int offsetX, int offsetY, Color fill);
	void ImageMipmaps(Image.ByReference image);
	void ImageDither(Image.ByReference image, int rBpp, int gBpp, int bBpp, int aBpp);
	void ImageFlipVertical(Image.ByReference image);
	void ImageFlipHorizontal(Image.ByReference image);
	void ImageRotateCW(Image.ByReference image);
	void ImageRotateCCW(Image.ByReference image);
	void ImageColorTint(Image.ByReference image, Color.ByValue color);
	void ImageColorInvert(Image.ByReference image);
	void ImageColorGrayscale(Image.ByReference image);
	void ImageColorContrast(Image.ByReference image, float contrast);
	void ImageColorBrightness(Image.ByReference image, int brightness);
	void ImageColorReplace(Image.ByReference image, Color.ByValue color, Color.ByValue replace);
	Color.ByReference[] LoadImageColors(Image.ByValue image);
	Color.ByReference[] LoadImagePalette(Image.ByValue image, int maxPaletteSize, IntByReference colorsCount);
	void UnloadImageColors(Color.ByReference[] colors);
	void UnloadImagePalette(Color.ByReference[] colors);
	Rectangle.ByValue GetImageAlphaBorder(Image.ByValue image, float threshold);
	Color.ByValue GetImageColor(Image.ByValue image, int x, int y);

	void ImageClearBackground(Image.ByReference dst, Color.ByValue color);
	void ImageDrawPixel(Image.ByReference dst, int posX, int posY, Color.ByValue color);
	void ImageDrawPixelV(Image.ByReference dst, Vector2.ByValue position, Color.ByValue color);
	void ImageDrawLine(Image.ByReference dst, int startPosX, int startPosY, int endPosX, int endPosY, Color.ByValue color);
	void ImageDrawLineV(Image.ByReference dst, Vector2.ByValue start, Vector2.ByValue end, Color.ByValue color);
	void ImageDrawCircle(Image.ByReference dst, int centerX, int centerY, int radius, Color.ByValue color);
	void ImageDrawCircleV(Image.ByReference dst, Vector2.ByValue center, int radius, Color.ByValue color);
	void ImageDrawRectangle(Image.ByReference dst, int posX, int posY, int width, int height, Color.ByValue color);
	void ImageDrawRectangleV(Image.ByReference dst, Vector2.ByValue position, Vector2.ByValue size, Color.ByValue color);
	void ImageDrawRectangleRec(Image.ByReference dst, Rectangle.ByValue rec, Color.ByValue color);
	void ImageDrawRectangleLines(Image.ByReference dst, Rectangle.ByValue rec, int thick, Color.ByValue color);
	void ImageDraw(Image.ByReference dst, Image.ByValue src, Rectangle.ByValue srcRec, Rectangle.ByValue dstRec, Color.ByValue tint);
	void ImageDrawText(Image.ByReference dst, String text, int posX, int posY, int fontSize, Color.ByValue color);
	void ImageDrawTextEx(Image.ByReference dst, Font.ByValue font, String text, Vector2.ByValue position, float fontSize, float spacing, Color.ByValue tint);
	Texture.ByValue LoadTexture(String fileName);
	Texture.ByValue LoadTextureFromImage(Image.ByValue image);
	Texture.ByValue LoadTextureCubemap(Image.ByValue image, int layout);
	RenderTexture.ByValue LoadRenderTexture(int width, int height);
	void UnloadTexture(Texture.ByValue texture);
	void UnloadRenderTexture(RenderTexture.ByValue target);
	void UpdateTexture(Texture.ByValue texture, Pointer pixels);
	void UpdateTextureRec(Texture.ByValue texture, Rectangle.ByValue rec, Pointer pixels);

	void GenTextureMipmaps(Texture.ByReference texture);
	void SetTextureFilter(Texture.ByValue texture, int filter);
	void SetTextureWrap(Texture.ByValue texture, int wrap);

	void DrawTexture(Texture.ByValue texture, int posX, int posY, Color.ByValue tint);
	void DrawTextureV(Texture.ByValue texture, Vector2.ByValue position, Color.ByValue tint);
	void DrawTextureEx(Texture.ByValue texture, Vector2.ByValue position, float rotation, float scale, Color.ByValue tint);
	void DrawTextureRec(Texture.ByValue texture, Rectangle.ByValue source, Vector2.ByValue position, Color.ByValue tint);
	void DrawTextureQuad(Texture.ByValue texture, Vector2.ByValue tiling, Vector2.ByValue offset, Rectangle.ByValue quad, Color.ByValue tint);
	void DrawTextureTiled(Texture.ByValue texture, Rectangle.ByValue source, Rectangle.ByValue dest, Vector2.ByValue origin, float rotation, float scale, Color.ByValue tint);
	void DrawTexturePro(Texture.ByValue texture, Rectangle.ByValue source, Rectangle.ByValue dest, Vector2.ByValue origin, float rotation, Color.ByValue tint);
	void DrawTextureNPatch(Texture.ByValue texture, NPatchInfo.ByValue nPatchInfo, Rectangle.ByValue dest, Vector2.ByValue origin, float rotation, Color.ByValue tint);
	void DrawTexturePoly(Texture.ByValue texture, Vector2.ByValue center, Vector2.ByReference[] points, Vector2.ByReference[] texcoords, int pointsCount, Color.ByValue tint);		

	Color.ByValue Fade(Color.ByValue color, float alpha);
	int ColorToInt(Color.ByValue color);
	Vector4.ByValue ColorNormalize(Color.ByValue color);
	Color.ByValue ColorFromNormalized(Vector4.ByValue normalized);
	Vector3.ByValue ColorToHSV(Color.ByValue color);
	Color.ByValue ColorFromHSV(float hue, float saturation, float value);
	Color.ByValue ColorAlpha(Color.ByValue color, float alpha);
	Color.ByValue ColorAlphaBlend(Color.ByValue dst, Color.ByValue src, Color.ByValue tint);
	Color.ByValue GetColor(int hexValue);
	Color.ByValue GetPixelColor(Pointer srcPtr, int format);
	void SetPixelColor(Pointer dstPtr, Color.ByValue color, int format);
	int GetPixelDataSize(int width, int height, int format);
	
	/* MODULE TEXT */
	
	Font.ByValue GetFontDefault();
	Font.ByValue LoadFont(String fileName);
	Font.ByValue LoadFontEx(String fileName, int fontSize, IntByReference fontChars, int glyphCount);
	Font.ByValue LoadFontFromImage(Image.ByValue image, Color.ByValue key, int firstChar);
	Font.ByValue LoadFontFromMemory(String fileType, String fileData, int dataSize, int fontSize, IntByReference fontChars, int glyphCount);
	GlyphInfo.ByReference LoadFontData(String fileData, int dataSize, int fontSize, IntByReference fontChars, int glyphCount, int type);
	Image.ByValue GenImageFontAtlas(GlyphInfo.ByReference chars[], Rectangle.ByReference[] recs, int glyphCount, int fontSize, int padding, int packMethod);
	void UnloadFontData(GlyphInfo.ByReference chars[], int glyphCount);
	void UnloadFont(Font.ByValue font);

	void DrawFPS(int posX, int posY);
	void DrawText(String text, int posX, int posY, int fontSize, Color.ByValue color);
	void DrawTextEx(Font.ByValue font, String text, Vector2.ByValue position, float fontSize, float spacing, Color.ByValue tint);
	void DrawTextPro(Font.ByValue font, String text, Vector2.ByValue position, Vector2.ByValue origin, float rotation, float fontSize, float spacing, Color.ByValue tint);
	void DrawTextCodepoint(Font.ByValue font, int codepoint, Vector2.ByValue position, float fontSize, Color.ByValue tint);

	int MeasureText(String text, int fontSize);
	Vector2.ByValue MeasureTextEx(Font.ByValue font, String text, float fontSize, float spacing);
	int GetGlyphIndex(Font.ByValue font, int codepoint);
	GlyphInfo.ByValue GetGlyphInfo(Font.ByValue font, int codepoint);
	Rectangle.ByValue GetGlyphAtlasRec(Font.ByValue font, int codepoint);

	IntByReference LoadCodepoints(String text, IntByReference count);
	void UnloadCodepoints(IntByReference codepoints);
	int GetCodepointCount(String text);
	int GetCodepoint(String text, IntByReference bytesProcessed);
	String CodepointToUTF8(int codepoint, IntByReference byteSize);
	String TextCodepointsToUTF8(IntByReference codepoints, int length);
	
	/* This is already builtin to Java anyways... */
	/* 
	int TextCopy(String dst, String src);
	boolean TextIsEqual(String text1, String text2);
	int TextLength(String text);
	String TextFormat(String text, Object ... va_args); // May or may not work
	String TextSubtext(String text, int position, int length);
	String TextReplace(String text, String replace, String by);
	String TextInsert(String text, String insert, int position);
	String TextJoin(Pointer[] /* char **textList * / textList, int count, String delimiter);
	Pointer[] /* char ** * /TextSplit(String text, char delimiter, IntByReference count);
	void TextAppend(String text, String append, IntByReference position);
	int TextFindIndex(String text, String find);
	String TextToUpper(String text);
	String TextToLower(String text);
	String TextToPascal(String text);
	int TextToInteger(String text); 
	*/
	
	/* MODULE MODELS */
	
	void DrawLine3D(Vector3 startPos, Vector3 endPos, Color color);
	void DrawPoint3D(Vector3 position, Color color);
	void DrawCircle3D(Vector3 center, float radius, Vector3 rotationAxis, float rotationAngle, Color color);
	void DrawTriangle3D(Vector3 v1, Vector3 v2, Vector3 v3, Color color);
	void DrawTriangleStrip3D(Vector3.ByReference points, int pointsCount, Color color);
	void DrawCube(Vector3 position, float width, float height, float length, Color color);
	void DrawCubeV(Vector3.ByValue position, Vector3.ByValue size, Color.ByValue color);
	void DrawCubeWires(Vector3.ByValue position, float width, float height, float length, Color.ByValue color);
	void DrawCubeWiresV(Vector3.ByValue position, Vector3.ByValue size, Color.ByValue color);
	void DrawCubeTexture(Texture texture, Vector3 position, float width, float height, float length, Color color);
	void DrawCubeTextureRec(Texture texture, Rectangle source, Vector3 position, float width, float height, float length, Color color);
	void DrawSphere(Vector3 centerPos, float radius, Color color);
	void DrawSphereEx(Vector3 centerPos, float radius, int rings, int slices, Color color);
	void DrawSphereWires(Vector3 centerPos, float radius, int rings, int slices, Color color);
	void DrawCylinder(Vector3 position, float radiusTop, float radiusBottom, float height, int slices, Color color);
	void DrawCylinderEx(Vector3 startPos, Vector3 endPos, float startRadius, float endRadius, int sides, Color color);
	void DrawCylinderWires(Vector3 position, float radiusTop, float radiusBottom, float height, int slices, Color color);
	void DrawCylinderWiresEx(Vector3 startPos, Vector3 endPos, float startRadius, float endRadius, int sides, Color color);
	void DrawPlane(Vector3 centerPos, Vector2 size, Color color);
	void DrawRay(Ray ray, Color color);
	void DrawGrid(int slices, float spacing);

	Model LoadModel(String fileName);
	Model LoadModelFromMesh(Mesh mesh);	
	void UnloadModel(Model model);
	void UnloadModelKeepMeshes(Model model);
	BoundingBox GetModelBoundingBox(Model model);

	void DrawModel(Model model, Vector3 position, float scale, Color tint);
	void DrawModelEx(Model model, Vector3 position, Vector3 rotationAxis, float rotationAngle, Vector3 scale, Color tint);
	void DrawModelWires(Model model, Vector3 position, float scale, Color tint);
	void DrawModelWiresEx(Model model, Vector3 position, Vector3 rotationAxis, float rotationAngle, Vector3 scale, Color tint);
	void DrawBoundingBox(BoundingBox box, Color color);
	void DrawBillboard(Camera2D camera, Texture texture, Vector3 position, float size, Color tint);
	void DrawBillboardRec(Camera2D camera, Texture texture, Rectangle source, Vector3 position, Vector2 size, Color tint);
	void DrawBillboardPro(Camera2D camera, Texture texture, Rectangle source, Vector3 position, Vector3 up, Vector2 size, Vector2 origin, float rotation, Color tint);

	void UploadMesh(Mesh.ByReference mesh, boolean dynamic);
	void UpdateMeshBuffer(Mesh mesh, int index, Pointer data, int dataSize, int offset);
	void UnloadMesh(Mesh mesh);
	void DrawMesh(Mesh mesh, Material material, Matrix transform);
	void DrawMeshInstanced(Mesh mesh, Material material, Matrix.ByReference transforms, int instances);
	boolean ExportMesh(Mesh mesh, String fileName);
	BoundingBox GetMeshBoundingBox(Mesh mesh);
	void GenMeshTangents(Mesh.ByReference mesh);
	void GenMeshBinormals(Mesh.ByReference mesh);

	Mesh.ByValue GenMeshPoly(int sides, float radius);
	Mesh.ByValue GenMeshPlane(float width, float length, int resX, int resZ);
	Mesh.ByValue GenMeshCube(float width, float height, float length);
	Mesh.ByValue GenMeshSphere(float radius, int rings, int slices);
	Mesh.ByValue GenMeshHemiSphere(float radius, int rings, int slices);
	Mesh.ByValue GenMeshCylinder(float radius, float height, int slices);
	Mesh.ByValue GenMeshCone(float radius, float height, int slices);
	Mesh.ByValue GenMeshTorus(float radius, float size, int radSeg, int sides);
	Mesh.ByValue GenMeshKnot(float radius, float size, int radSeg, int sides);
	Mesh.ByValue GenMeshHeightmap(Image heightmap, Vector3 size);
	Mesh.ByValue GenMeshCubicmap(Image cubicmap, Vector3 cubeSize);

	Material.ByReference LoadMaterials(String fileName, IntByReference materialCount);
	Material LoadMaterialDefault();
	void UnloadMaterial(Material material);
	void SetMaterialTexture(Material.ByReference material, int mapType, Texture texture);
	void SetModelMeshMaterial(Model.ByReference model, int meshId, int materialId);
	
	ModelAnimation.ByReference LoadModelAnimations(String fileName, IntByReference animCount);
	void UpdateModelAnimation(Model model, ModelAnimation anim, int frame);
	void UnloadModelAnimation(ModelAnimation anim);
	void UnloadModelAnimations(ModelAnimation.ByReference animations, int count);
	boolean IsModelAnimationValid(Model model, ModelAnimation anim);

	boolean CheckCollisionSpheres(Vector3 center1, float radius1, Vector3 center2, float radius2);
	boolean CheckCollisionBoxes(BoundingBox box1, BoundingBox box2);
	boolean CheckCollisionBoxSphere(BoundingBox box, Vector3 center, float radius);
	RayCollision GetRayCollisionSphere(Ray ray, Vector3 center, float radius);
	RayCollision GetRayCollisionBox(Ray ray, BoundingBox box);
	RayCollision GetRayCollisionModel(Ray ray, Model model);
	RayCollision GetRayCollisionMesh(Ray ray, Mesh mesh, Matrix transform);
	RayCollision GetRayCollisionTriangle(Ray ray, Vector3 p1, Vector3 p2, Vector3 p3);
	RayCollision GetRayCollisionQuad(Ray ray, Vector3 p1, Vector3 p2, Vector3 p3, Vector3 p4);
	
	/* MODULE AUDIO */
	
	void InitAudioDevice();
	void CloseAudioDevice();
	boolean IsAudioDeviceReady();
	void SetMasterVolume(float volume);

	Wave LoadWave(String fileName);
	Wave LoadWaveFromMemory(String fileType, String fileData, int dataSize);
	Sound LoadSound(String fileName);
	Sound LoadSoundFromWave(Wave wave);
	void UpdateSound(Sound sound, Pointer data, int samplesCount);
	void UnloadWave(Wave wave);
	void UnloadSound(Sound sound);
	boolean ExportWave(Wave wave, String fileName);
	boolean ExportWaveAsCode(Wave wave, String fileName);
	
	void PlaySound(Sound sound);
	void StopSound(Sound sound);
	void PauseSound(Sound sound);
	void ResumeSound(Sound sound);
	void PlaySoundMulti(Sound sound);
	void StopSoundMulti();
	int GetSoundsPlaying();
	boolean IsSoundPlaying(Sound sound);
	void SetSoundVolume(Sound sound, float volume);
	void SetSoundPitch(Sound sound, float pitch);
	void WaveFormat(Wave.ByReference wave, int sampleRate, int sampleSize, int channels);
	Wave WaveCopy(Wave wave);
	void WaveCrop(Wave.ByReference wave, int initSample, int finalSample);
	FloatByReference LoadWaveSamples(Wave wave);
	void UnloadWaveSamples(FloatByReference samples);

	Music LoadMusicStream(String fileName);
	Music LoadMusicStreamFromMemory(String fileType, String data, int dataSize);
	void UnloadMusicStream(Music music);
	void PlayMusicStream(Music music);
	boolean IsMusicStreamPlaying(Music music);
	void UpdateMusicStream(Music music);
	void StopMusicStream(Music music);
	void PauseMusicStream(Music music);
	void ResumeMusicStream(Music music);
	void SeekMusicStream(Music music, float position);
	void SetMusicVolume(Music music, float volume);
	void SetMusicPitch(Music music, float pitch);
	float GetMusicTimeLength(Music music);
	float GetMusicTimePlayed(Music music);

	AudioStream InitAudioStream(int sampleRate, int sampleSize, int channels);
	void UpdateAudioStream(AudioStream stream, Pointer data, int samplesCount);
	void CloseAudioStream(AudioStream stream);
	boolean IsAudioStreamProcessed(AudioStream stream);
	void PlayAudioStream(AudioStream stream);
	void PauseAudioStream(AudioStream stream);
	void ResumeAudioStream(AudioStream stream);
	boolean IsAudioStreamPlaying(AudioStream stream);
	void StopAudioStream(AudioStream stream);
	void SetAudioStreamVolume(AudioStream stream, float volume);
	void SetAudioStreamPitch(AudioStream stream, float pitch);
	void SetAudioStreamBufferSizeDefault(int size);
	
	/* COLORS */
	
	public static interface Colors {
		
		/* Mine */
		public static final Color.ByValue BLOOD = new Color.ByValue(136, 8, 8);
		public static final Color.ByValue COFFEE = new Color.ByValue(0xC0, 0xFF, 0xEE);
		
		/* Raylib Defaults */
		public static final Color.ByValue LIGHTGRAY =  new Color.ByValue(200, 200, 200);
		public static final Color.ByValue GRAY =       new Color.ByValue(130, 130, 130);
		public static final Color.ByValue DARKGRAY =   new Color.ByValue(80, 80, 80);
		public static final Color.ByValue YELLOW =     new Color.ByValue(253, 249, 0);
		public static final Color.ByValue GOLD =       new Color.ByValue(255, 203, 0);
		public static final Color.ByValue ORANGE =     new Color.ByValue(255, 161, 0);
		public static final Color.ByValue PINK =       new Color.ByValue(255, 109, 194);
		public static final Color.ByValue RED =        new Color.ByValue(230, 41, 55);
		public static final Color.ByValue MAROON =     new Color.ByValue(190, 33, 55);
		public static final Color.ByValue GREEN =      new Color.ByValue(0, 228, 48);
		public static final Color.ByValue LIME =       new Color.ByValue(0, 158, 47);
		public static final Color.ByValue DARKGREEN =  new Color.ByValue(0, 117, 44);
		public static final Color.ByValue SKYBLUE =    new Color.ByValue(102, 191, 255);
		public static final Color.ByValue BLUE =       new Color.ByValue(0, 121, 241);
		public static final Color.ByValue DARKBLUE =   new Color.ByValue(0, 82, 172);
		public static final Color.ByValue PURPLE =     new Color.ByValue(200, 122, 255);
		public static final Color.ByValue VIOLET =     new Color.ByValue(135, 60, 190);
		public static final Color.ByValue DARKPURPLE = new Color.ByValue(112, 31, 126);
		public static final Color.ByValue BEIGE =      new Color.ByValue(211, 176, 131);
		public static final Color.ByValue BROWN =      new Color.ByValue(127, 106, 79);
		public static final Color.ByValue DARKBROWN =  new Color.ByValue(76, 63, 47);

		public static final Color.ByValue WHITE =      new Color.ByValue(255, 255, 255);
		public static final Color.ByValue BLACK =      new Color.ByValue(0, 0, 0);
		public static final Color.ByValue BLANK =      new Color.ByValue(0, 0, 0, 0);
		public static final Color.ByValue MAGENTA =    new Color.ByValue(255, 0, 255);
		public static final Color.ByValue RAYWHITE =   new Color.ByValue(245, 245, 245);
	}
	
	/* ENUMS */
	
	public static interface ConfigFlags {
		public static final int FLAG_VSYNC_HINT = 0x00000040;
		public static final int FLAG_FULLSCREEN_MODE = 0x00000002;
		public static final int FLAG_WINDOW_RESIZABLE = 0x00000004;
		public static final int FLAG_WINDOW_UNDECORATED = 0x00000008;
		public static final int FLAG_WINDOW_HIDDEN = 0x00000080;
		public static final int FLAG_WINDOW_MINIMIZED = 0x00000200;
		public static final int FLAG_WINDOW_MAXIMIZED = 0x00000400;
		public static final int FLAG_WINDOW_UNFOCUSED = 0x00000800;
		public static final int FLAG_WINDOW_TOPMOST = 0x00001000;
		public static final int FLAG_WINDOW_ALWAYS_RUN = 0x00000100;
		public static final int FLAG_WINDOW_TRANSPARENT = 0x00000010;
		public static final int FLAG_WINDOW_HIGHDPI = 0x00002000;
		public static final int FLAG_MSAA_4X_HINT = 0x00000020;
		public static final int FLAG_INTERLACED_HINT = 0x00010000;
	};
	
	public static interface TraceLogLevel {
		public static final int LOG_ALL = 0;
		public static final int LOG_TRACE = 1;
		public static final int LOG_DEBUG = 2;
		public static final int LOG_INFO = 3;
		public static final int LOG_WARNING = 4;
		public static final int LOG_ERROR = 5;
		public static final int LOG_FATAL = 6;
		public static final int LOG_NONE = 7;
	};
	
	public static interface KeyboardKey {
		public static final int KEY_NULL = 0;
		public static final int KEY_APOSTROPHE = 39;
		public static final int KEY_COMMA = 44;
		public static final int KEY_MINUS = 45;
		public static final int KEY_PERIOD = 46;
		public static final int KEY_SLASH = 47;
		public static final int KEY_ZERO = 48;
		public static final int KEY_ONE = 49;
		public static final int KEY_TWO = 50;
		public static final int KEY_THREE = 51;
		public static final int KEY_FOUR = 52;
		public static final int KEY_FIVE = 53;
		public static final int KEY_SIX = 54;
		public static final int KEY_SEVEN = 55;
		public static final int KEY_EIGHT = 56;
		public static final int KEY_NINE = 57;
		public static final int KEY_SEMICOLON = 59;
		public static final int KEY_EQUAL = 61;
		public static final int KEY_A = 65;
		public static final int KEY_B = 66;
		public static final int KEY_C = 67;
		public static final int KEY_D = 68;
		public static final int KEY_E = 69;
		public static final int KEY_F = 70;
		public static final int KEY_G = 71;
		public static final int KEY_H = 72;
		public static final int KEY_I = 73;
		public static final int KEY_J = 74;
		public static final int KEY_K = 75;
		public static final int KEY_L = 76;
		public static final int KEY_M = 77;
		public static final int KEY_N = 78;
		public static final int KEY_O = 79;
		public static final int KEY_P = 80;
		public static final int KEY_Q = 81;
		public static final int KEY_R = 82;
		public static final int KEY_S = 83;
		public static final int KEY_T = 84;
		public static final int KEY_U = 85;
		public static final int KEY_V = 86;
		public static final int KEY_W = 87;
		public static final int KEY_X = 88;
		public static final int KEY_Y = 89;
		public static final int KEY_Z = 90;
		public static final int KEY_LEFT_BRACKET = 91;
		public static final int KEY_BACKSLASH = 92;
		public static final int KEY_RIGHT_BRACKET = 93;
		public static final int KEY_GRAVE = 96;
		public static final int KEY_SPACE = 32;
		public static final int KEY_ESCAPE = 256;
		public static final int KEY_ENTER = 257;
		public static final int KEY_TAB = 258;
		public static final int KEY_BACKSPACE = 259;
		public static final int KEY_INSERT = 260;
		public static final int KEY_DELETE = 261;
		public static final int KEY_RIGHT = 262;
		public static final int KEY_LEFT = 263;
		public static final int KEY_DOWN = 264;
		public static final int KEY_UP = 265;
		public static final int KEY_PAGE_UP = 266;
		public static final int KEY_PAGE_DOWN = 267;
		public static final int KEY_HOME = 268;
		public static final int KEY_END = 269;
		public static final int KEY_CAPS_LOCK = 280;
		public static final int KEY_SCROLL_LOCK = 281;
		public static final int KEY_NUM_LOCK = 282;
		public static final int KEY_PRINT_SCREEN = 283;
		public static final int KEY_PAUSE = 284;
		public static final int KEY_F1 = 290;
		public static final int KEY_F2 = 291;
		public static final int KEY_F3 = 292;
		public static final int KEY_F4 = 293;
		public static final int KEY_F5 = 294;
		public static final int KEY_F6 = 295;
		public static final int KEY_F7 = 296;
		public static final int KEY_F8 = 297;
		public static final int KEY_F9 = 298;
		public static final int KEY_F10 = 299;
		public static final int KEY_F11 = 300;
		public static final int KEY_F12 = 301;
		public static final int KEY_LEFT_SHIFT = 340;
		public static final int KEY_LEFT_CONTROL = 341;
		public static final int KEY_LEFT_ALT = 342;
		public static final int KEY_LEFT_SUPER = 343;
		public static final int KEY_RIGHT_SHIFT = 344;
		public static final int KEY_RIGHT_CONTROL = 345;
		public static final int KEY_RIGHT_ALT = 346;
		public static final int KEY_RIGHT_SUPER = 347;
		public static final int KEY_KB_MENU = 348;
		public static final int KEY_KP_0 = 320;
		public static final int KEY_KP_1 = 321;
		public static final int KEY_KP_2 = 322;
		public static final int KEY_KP_3 = 323;
		public static final int KEY_KP_4 = 324;
		public static final int KEY_KP_5 = 325;
		public static final int KEY_KP_6 = 326;
		public static final int KEY_KP_7 = 327;
		public static final int KEY_KP_8 = 328;
		public static final int KEY_KP_9 = 329;
		public static final int KEY_KP_DECIMAL = 330;
		public static final int KEY_KP_DIVIDE = 331;
		public static final int KEY_KP_MULTIPLY = 332;
		public static final int KEY_KP_SUBTRACT = 333;
		public static final int KEY_KP_ADD = 334;
		public static final int KEY_KP_ENTER = 335;
		public static final int KEY_KP_EQUAL = 336;
		public static final int KEY_BACK = 4;
		public static final int KEY_MENU = 82;
		public static final int KEY_VOLUME_UP = 24;
		public static final int KEY_VOLUME_DOWN = 25;
	};
	
	public static interface MouseButton {
		public static final int MOUSE_BUTTON_LEFT = 0;
		public static final int MOUSE_BUTTON_RIGHT = 1;
		public static final int MOUSE_BUTTON_MIDDLE = 2;
		public static final int MOUSE_BUTTON_SIDE = 3;
		public static final int MOUSE_BUTTON_EXTRA = 4;
		public static final int MOUSE_BUTTON_FORWARD = 5;
		public static final int MOUSE_BUTTON_BACK = 6;
	};
	
	public static interface MouseCursor {
		public static final int MOUSE_CURSOR_DEFAULT = 0;
		public static final int MOUSE_CURSOR_ARROW = 1;
		public static final int MOUSE_CURSOR_IBEAM = 2;
		public static final int MOUSE_CURSOR_CROSSHAIR = 3;
		public static final int MOUSE_CURSOR_POINTING_HAND = 4;
		public static final int MOUSE_CURSOR_RESIZE_EW = 5;
		public static final int MOUSE_CURSOR_RESIZE_NS = 6;
		public static final int MOUSE_CURSOR_RESIZE_NWSE = 7;
		public static final int MOUSE_CURSOR_RESIZE_NESW = 8;
		public static final int MOUSE_CURSOR_RESIZE_ALL = 9;
		public static final int MOUSE_CURSOR_NOT_ALLOWED = 10;
	};
	
	public static interface GamepadButton {
		public static final int GAMEPAD_BUTTON_UNKNOWN = 0;
		public static final int GAMEPAD_BUTTON_LEFT_FACE_UP = 1;
		public static final int GAMEPAD_BUTTON_LEFT_FACE_RIGHT = 2;
		public static final int GAMEPAD_BUTTON_LEFT_FACE_DOWN = 3;
		public static final int GAMEPAD_BUTTON_LEFT_FACE_LEFT = 4;
		public static final int GAMEPAD_BUTTON_RIGHT_FACE_UP = 5;
		public static final int GAMEPAD_BUTTON_RIGHT_FACE_RIGHT = 6;
		public static final int GAMEPAD_BUTTON_RIGHT_FACE_DOWN = 7;
		public static final int GAMEPAD_BUTTON_RIGHT_FACE_LEFT = 8;
		public static final int GAMEPAD_BUTTON_LEFT_TRIGGER_1 = 9;
		public static final int GAMEPAD_BUTTON_LEFT_TRIGGER_2 = 10;
		public static final int GAMEPAD_BUTTON_RIGHT_TRIGGER_1 = 11;
		public static final int GAMEPAD_BUTTON_RIGHT_TRIGGER_2 = 12;
		public static final int GAMEPAD_BUTTON_MIDDLE_LEFT = 13;
		public static final int GAMEPAD_BUTTON_MIDDLE = 14;
		public static final int GAMEPAD_BUTTON_MIDDLE_RIGHT = 15;
		public static final int GAMEPAD_BUTTON_LEFT_THUMB = 16;
		public static final int GAMEPAD_BUTTON_RIGHT_THUMB = 17;
	};
	
	public static interface GamepadAxis {
		public static final int GAMEPAD_AXIS_LEFT_X = 0;
		public static final int GAMEPAD_AXIS_LEFT_Y = 1;
		public static final int GAMEPAD_AXIS_RIGHT_X = 2;
		public static final int GAMEPAD_AXIS_RIGHT_Y = 3;
		public static final int GAMEPAD_AXIS_LEFT_TRIGGER = 4;
		public static final int GAMEPAD_AXIS_RIGHT_TRIGGER = 5;
	};
	
	public static interface MaterialMapIndex {
		public static final int MATERIAL_MAP_ALBEDO = 0;
		public static final int MATERIAL_MAP_METALNESS = 1;
		public static final int MATERIAL_MAP_NORMAL = 2;
		public static final int MATERIAL_MAP_ROUGHNESS = 3;
		public static final int MATERIAL_MAP_OCCLUSION = 4;
		public static final int MATERIAL_MAP_EMISSION = 5;
		public static final int MATERIAL_MAP_HEIGHT = 6;
		public static final int MATERIAL_MAP_CUBEMAP = 7;
		public static final int MATERIAL_MAP_IRRADIANCE = 8;
		public static final int MATERIAL_MAP_PREFILTER = 9;
		public static final int MATERIAL_MAP_BRDF = 10;
	};
	
	public static interface ShaderLocationIndex {
		public static final int SHADER_LOC_VERTEX_POSITION = 0;
		public static final int SHADER_LOC_VERTEX_TEXCOORD01 = 1;
		public static final int SHADER_LOC_VERTEX_TEXCOORD02 = 2;
		public static final int SHADER_LOC_VERTEX_NORMAL = 3;
		public static final int SHADER_LOC_VERTEX_TANGENT = 4;
		public static final int SHADER_LOC_VERTEX_COLOR = 5;
		public static final int SHADER_LOC_MATRIX_MVP = 6;
		public static final int SHADER_LOC_MATRIX_VIEW = 7;
		public static final int SHADER_LOC_MATRIX_PROJECTION = 8;
		public static final int SHADER_LOC_MATRIX_MODEL = 9;
		public static final int SHADER_LOC_MATRIX_NORMAL = 10;
		public static final int SHADER_LOC_VECTOR_VIEW = 11;
		public static final int SHADER_LOC_COLOR_DIFFUSE = 12;
		public static final int SHADER_LOC_COLOR_SPECULAR = 13;
		public static final int SHADER_LOC_COLOR_AMBIENT = 14;
		public static final int SHADER_LOC_MAP_ALBEDO = 15;
		public static final int SHADER_LOC_MAP_METALNESS = 16;
		public static final int SHADER_LOC_MAP_NORMAL = 17;
		public static final int SHADER_LOC_MAP_ROUGHNESS = 18;
		public static final int SHADER_LOC_MAP_OCCLUSION = 19;
		public static final int SHADER_LOC_MAP_EMISSION = 20;
		public static final int SHADER_LOC_MAP_HEIGHT = 21;
		public static final int SHADER_LOC_MAP_CUBEMAP = 22;
		public static final int SHADER_LOC_MAP_IRRADIANCE = 23;
		public static final int SHADER_LOC_MAP_PREFILTER = 24;
		public static final int SHADER_LOC_MAP_BRDF = 25;
	};
	
	public static interface ShaderUniformDataType {
		public static final int SHADER_UNIFORM_FLOAT = 0;
		public static final int SHADER_UNIFORM_VEC2 = 1;
		public static final int SHADER_UNIFORM_VEC3 = 2;
		public static final int SHADER_UNIFORM_VEC4 = 3;
		public static final int SHADER_UNIFORM_INT = 4;
		public static final int SHADER_UNIFORM_IVEC2 = 5;
		public static final int SHADER_UNIFORM_IVEC3 = 6;
		public static final int SHADER_UNIFORM_IVEC4 = 7;
		public static final int SHADER_UNIFORM_SAMPLER2D = 8;
	};
	
	public static interface ShaderAttributeDataType {
		public static final int SHADER_ATTRIB_FLOAT = 0;
		public static final int SHADER_ATTRIB_VEC2 = 1;
		public static final int SHADER_ATTRIB_VEC3 = 2;
		public static final int SHADER_ATTRIB_VEC4 = 3;
	};
	
	public static interface PixelFormat {
		public static final int PIXELFORMAT_UNCOMPRESSED_GRAYSCALE = 1;
		public static final int PIXELFORMAT_UNCOMPRESSED_GRAY_ALPHA = 2;
		public static final int PIXELFORMAT_UNCOMPRESSED_R5G6B5 = 3;
		public static final int PIXELFORMAT_UNCOMPRESSED_R8G8B8 = 4;
		public static final int PIXELFORMAT_UNCOMPRESSED_R5G5B5A1 = 5;
		public static final int PIXELFORMAT_UNCOMPRESSED_R4G4B4A4 = 6;
		public static final int PIXELFORMAT_UNCOMPRESSED_R8G8B8A8 = 7;
		public static final int PIXELFORMAT_UNCOMPRESSED_R32 = 8;
		public static final int PIXELFORMAT_UNCOMPRESSED_R32G32B32 = 9;
		public static final int PIXELFORMAT_UNCOMPRESSED_R32G32B32A32 = 10;
		public static final int PIXELFORMAT_COMPRESSED_DXT1_RGB = 11;
		public static final int PIXELFORMAT_COMPRESSED_DXT1_RGBA = 12;
		public static final int PIXELFORMAT_COMPRESSED_DXT3_RGBA = 13;
		public static final int PIXELFORMAT_COMPRESSED_DXT5_RGBA = 14;
		public static final int PIXELFORMAT_COMPRESSED_ETC1_RGB = 15;
		public static final int PIXELFORMAT_COMPRESSED_ETC2_RGB = 16;
		public static final int PIXELFORMAT_COMPRESSED_ETC2_EAC_RGBA = 17;
		public static final int PIXELFORMAT_COMPRESSED_PVRT_RGB = 18;
		public static final int PIXELFORMAT_COMPRESSED_PVRT_RGBA = 19;
		public static final int PIXELFORMAT_COMPRESSED_ASTC_4x4_RGBA = 20;
		public static final int PIXELFORMAT_COMPRESSED_ASTC_8x8_RGBA = 21;
	};
	
	public static interface TextureFilter {
		public static final int TEXTURE_FILTER_POINT = 0;
		public static final int TEXTURE_FILTER_BILINEAR = 1;
		public static final int TEXTURE_FILTER_TRILINEAR = 2;
		public static final int TEXTURE_FILTER_ANISOTROPIC_4X = 3;
		public static final int TEXTURE_FILTER_ANISOTROPIC_8X = 4;
		public static final int TEXTURE_FILTER_ANISOTROPIC_16X = 5;
	};
	
	public static interface TextureWrap {
		public static final int TEXTURE_WRAP_REPEAT = 0;
		public static final int TEXTURE_WRAP_CLAMP = 1;
		public static final int TEXTURE_WRAP_MIRROR_REPEAT = 2;
		public static final int TEXTURE_WRAP_MIRROR_CLAMP = 3;
	};
	
	public static interface CubemapLayout {
		public static final int CUBEMAP_LAYOUT_AUTO_DETECT = 0;
		public static final int CUBEMAP_LAYOUT_LINE_VERTICAL = 1;
		public static final int CUBEMAP_LAYOUT_LINE_HORIZONTAL = 2;
		public static final int CUBEMAP_LAYOUT_CROSS_THREE_BY_FOUR = 3;
		public static final int CUBEMAP_LAYOUT_CROSS_FOUR_BY_THREE = 4;
		public static final int CUBEMAP_LAYOUT_PANORAMA = 5;
	};
	
	public static interface FontType {
		public static final int FONT_DEFAULT = 0;
		public static final int FONT_BITMAP = 1;
		public static final int FONT_SDF = 2;
	};
	
	public static interface BlendMode {
		public static final int BLEND_ALPHA = 0;
		public static final int BLEND_ADDITIVE = 1;
		public static final int BLEND_MULTIPLIED = 2;
		public static final int BLEND_ADD_COLORS = 3;
		public static final int BLEND_SUBTRACT_COLORS = 4;
		public static final int BLEND_CUSTOM = 5;
	};
	
	public static interface Gesture {
		public static final int GESTURE_NONE = 0;
		public static final int GESTURE_TAP = 1;
		public static final int GESTURE_DOUBLETAP = 2;
		public static final int GESTURE_HOLD = 4;
		public static final int GESTURE_DRAG = 8;
		public static final int GESTURE_SWIPE_RIGHT = 16;
		public static final int GESTURE_SWIPE_LEFT = 32;
		public static final int GESTURE_SWIPE_UP = 64;
		public static final int GESTURE_SWIPE_DOWN = 128;
		public static final int GESTURE_PINCH_IN = 256;
		public static final int GESTURE_PINCH_OUT = 512;
	};
	
	public static interface CameraMode {
		public static final int CAMERA_CUSTOM = 0;
		public static final int CAMERA_FREE = 1;
		public static final int CAMERA_ORBITAL = 2;
		public static final int CAMERA_FIRST_PERSON = 3;
		public static final int CAMERA_THIRD_PERSON = 4;
	};
	
	public static interface CameraProjection {
		public static final int CAMERA_PERSPECTIVE = 0;
		public static final int CAMERA_ORTHOGRAPHIC = 1;
	};
	
	public static interface NPatchLayout {
		public static final int NPATCH_NINE_PATCH = 0;
		public static final int NPATCH_THREE_PATCH_VERTICAL = 1;
		public static final int NPATCH_THREE_PATCH_HORIZONTAL = 2;
	};
}
