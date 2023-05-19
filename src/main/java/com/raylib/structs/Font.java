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

package com.occultusterra.raylib.structs;

import com.sun.jna.Structure;
import com.sun.jna.Pointer;

@Structure.FieldOrder({"baseSize", "glyphCount", "glyphPadding", "texture", "recs", "glyphs"})
public class Font extends Structure {
	
	public Font() { super(); }
	public Font(Pointer peer) { super(peer); read(); }
	
	public static class ByReference extends Font implements Structure.ByReference {
		public ByReference() { super(); }
		public ByReference(Pointer memory) { super(memory); }
	}

	public static class ByValue extends Font implements Structure.ByValue {
		public ByValue() { super(); }
		public ByValue(Pointer memory) { super(memory); }
		public Font.ByReference getReference() { return new Font.ByReference(this.getPointer()); }
	}
	
	public int baseSize;
	public int glyphCount;
	public int glyphPadding;
	public Texture.ByValue texture;
	// This are arrays that hopefully will not need direct access
	public Rectangle.ByReference recs;
	public GlyphInfo.ByReference glyphs;
}
